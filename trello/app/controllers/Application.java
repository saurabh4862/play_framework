package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import com.avaje.ebean.SqlUpdate;
import com.fasterxml.jackson.databind.JsonNode;
import jdk.nashorn.internal.ir.ObjectNode;
import models.Users;
import models.models.Trello;
import org.mindrot.jbcrypt.BCrypt;
import play.*;
import play.api.libs.ws.ssl.SystemConfiguration;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import play.api.libs.json.*;
import scala.util.parsing.json.JSON;
import scala.util.parsing.json.JSONArray;
import scala.util.parsing.json.JSONObject;
import views.html.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application extends Controller {

    public Result index() {
        String user = session("connected");
        if(user != null) {
            return ok(trello.render());
        } else {
            return ok(index.render("Your new application is ready."));
        }
    }

    public Result signup()
    {
        return ok(signup.render("signup"));
    }
//    public Result Trello()
//    {
//        return ok(Trello.render());
//    }

    public Result addUsers()
    {
        Users user;
        user= Form.form(Users.class).bindFromRequest().get();
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        user.save();
        return redirect(routes.Application.index());
    }
    public Result findUsers()
    {
        DynamicForm requestData = Form.form().bindFromRequest();
        String username = requestData.get("userName");
        String emaild = requestData.get("emailId");
        String password =requestData.get("password");
        Model.Finder<String,Users> finder = new Model.Finder<String, Users>(String.class,Users.class);
        Users users = finder.where().eq("user_name",username).eq("email_id",emaild).findUnique();
        String acPass="";
        try {
            acPass = users.getPassword();
        } catch (NullPointerException npe) {
            // It's fine if findUser throws a NPE
        }

        if (BCrypt.checkpw(password, acPass)) {
            session("connected", users.getEmailId());
            return redirect(routes.Application.index());
        }
        else
            return ok("Wrong Credientials");

    }
    public Result logOut()
    {
        session().remove("connected");
        session().clear();
        return redirect(routes.Application.index());
    }
    public Result addTask()
    {
        JsonNode json = request().body().asJson();
        System.out.println("Working : " + json);
        String user = session("connected");
        Trello trello= new Trello();
        trello.setEmailId(user);
        trello.setTask(json.findPath("task").textValue());
        trello.setTitle(json.findPath("title").textValue());
        trello.save();
        return redirect(routes.Application.index());
    }
    public Result addList()
   {
       JsonNode json = request().body().asJson();
       String user = session("connected");
       System.out.println(json);
       Trello trello= new Trello();
       trello.setEmailId(user);
       trello.setTask("");
       trello.setTitle(json.findPath("list").textValue());
       trello.save();
       return redirect(routes.Application.index());
   }
    public Result getTrello()
    {
        String emailId = session("connected");
        System.out.println(emailId);
        Model.Finder<String,Trello> finder = new Model.Finder<String, Trello>(String.class,Trello.class);
        List<Trello> listTrello = finder.where().eq("email_id",emailId).orderBy("id").findList();
        class Work{
            public String title;
            public List<String> task=new ArrayList<String>();
        }
        List<Work> listWork= new ArrayList<Work>();
        int i=0;
        System.out.println(listTrello.size());
        while(i<listTrello.size()){
            boolean s=false;
            for (int k=0;k<listWork.size();k++) {
                if (listWork.get(k).title.equals(listTrello.get(i).getTitle())) {
                    s=true;
                    break;
                }
            }
            if (s == false) {
                System.out.println(listTrello.get(i).getTitle());
                Work work = new Work();
                work.title = listTrello.get(i).getTitle();
                for (int j = 0; j < listTrello.size(); j++) {
                    if (listTrello.get(j).getTitle().equals(work.title)) {
                        work.task.add(listTrello.get(j).getTask());
                    }
                }
                listWork.add(work);
                i= i+1;
            }
            else
                i=i+1;
        }
        return ok(Json.toJson(listWork));
    }
    public Result delete(){
        JsonNode json = request().body().asJson();
        System.out.println(json);
        String user = session("connected");
        String titl=json.findPath("title").textValue();
        String tas=json.findPath("task").textValue();
        Model.Finder<String,Trello> finder = new Model.Finder<String, Trello>(String.class,Trello.class);
        Trello trello = finder.where().eq("email_id",user).eq("title", titl).eq("task",tas).findUnique();
        trello.delete();

        return redirect(routes.Application.index());
    }
    public Result deleteList(){
        JsonNode json = request().body().asJson();
        System.out.println(json);
        String user = session("connected");
        String titl=json.findPath("title").textValue();
        Model.Finder<String,Trello> finder = new Model.Finder<String, Trello>(String.class,Trello.class);
        List<Trello> listTrello = finder.where().eq("email_id", user).eq("title", titl).findList();
        for (int i=0;i<listTrello.size();i++) {
            listTrello.get(i).delete();
        }
        return redirect(routes.Application.index());
    }
}