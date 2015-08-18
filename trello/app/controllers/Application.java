package controllers;

import akka.actor.Actor;
import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import models.Users;
import models.Verify;
import models.models.Trello;
import org.mindrot.jbcrypt.BCrypt;
import play.libs.mailer.Email;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.libs.mailer.MailerClient;
import play.mvc.*;
import scala.concurrent.duration.Duration;
import views.html.*;

import javax.inject.Inject;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Application extends Controller {
    private final MailerClient mc;

    @Inject
    public Application(MailerClient mc) {
        this.mc = mc;
    }

    public Result index() {
        String user = session("connected");
        if (user != null) {
            return ok(trello.render());
        } else {
            return ok(index.render("Your new application is ready."));
        }
    }

    public Result signup() {
        return ok(signup.render("signup"));
    }

    public Result verify1() {
        Verify v = Form.form(Verify.class).bindFromRequest().get();
        if (v.getCode() == Integer.parseInt(session("random"))) {
            Users user = new Users();
            user.setUserName(session("name"));
            user.setEmailId(session("emailId"));
            user.setPassword(session("pwd"));
            user.save();
            session().remove("name");
            session().remove("emailId");
            session().remove("pwd");
            session().remove("random");
            session().clear();
            return redirect(routes.Application.index());
        } else {
            return redirect(routes.Application.verify1());
        }
    }

    public Result verify() {
        return ok(verify.render());
    }

    public Result addUsers() {
        Users user = Form.form(Users.class).bindFromRequest().get();
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        Model.Finder<String, Users> finder = new Model.Finder<String, Users>(String.class, Users.class);
        Users users = finder.where().eq("email_id", user.getEmailId()).findUnique();
        if (users==null){
        Random rand = new Random();
        int r1 = rand.nextInt(100000) + 1;
        Email email = new Email();
        email.setSubject("verification Code");
        email.setFrom("saurabh<saurabh@zemosolabs.com>");
        email.addTo(user.getEmailId());
        email.setBodyText("your verification code is " + r1);
        mc.send(email);
        session("name", user.getUserName());
        session("emailId", user.getEmailId());
        session("pwd", user.getPassword());
        session("random", String.valueOf(r1));

        return ok(verify.render());}
        else{
            return ok("Email ID already exist!");
        }

    }

    public Result findUsers() {
        DynamicForm requestData = Form.form().bindFromRequest();
        String username = requestData.get("userName");
        String emaild = requestData.get("emailId");
        String password = requestData.get("password");
        Model.Finder<String, Users> finder = new Model.Finder<String, Users>(String.class, Users.class);
        Users users = finder.where().eq("user_name", username).eq("email_id", emaild).findUnique();
        String acPass = "";
        try {
            acPass = users.getPassword();
        } catch (NullPointerException npe) {
            // It's fine if findUser throws a NPE
        }

        if (BCrypt.checkpw(password, acPass)) {
            session("connected", users.getEmailId());
            return redirect(routes.Application.index());
        } else
            return ok("Wrong Credientials");

    }

    public Result logOut() {
        session().remove("connected");
        session().clear();
        return redirect(routes.Application.index());
    }

    public Result addTask() {
        JsonNode json = request().body().asJson();
        System.out.println("Working : " + json);
        String user = session("connected");
        Trello trello = new Trello();
        trello.setEmailId(user);
        trello.setTask(json.findPath("task").textValue());
        trello.setTitle(json.findPath("title").textValue());
        trello.setDate(json.findPath("date").textValue());
        trello.save();
        return redirect(routes.Application.index());
    }

    public Result addList() {
        JsonNode json = request().body().asJson();
        String user = session("connected");
        System.out.println(json);
        Trello trello = new Trello();
        trello.setEmailId(user);
        trello.setTask("");
        trello.setDate("");
        trello.setTitle(json.findPath("list").textValue());
        trello.save();
        return redirect(routes.Application.index());
    }

    public Result getTrello() {
        String emailId = session("connected");
        System.out.println(emailId);
        Model.Finder<String, Trello> finder = new Model.Finder<String, Trello>(String.class, Trello.class);
        List<Trello> listTrello = finder.where().eq("email_id", emailId).orderBy("id").findList();
        class Work {
            public String title;
            public List<String> task = new ArrayList<String>();
            public List<String> date = new ArrayList<String>();
        }
        List<Work> listWork = new ArrayList<Work>();
        int i = 0;
        System.out.println(listTrello.size());
        while (i < listTrello.size()) {
            boolean s = false;
            for (int k = 0; k < listWork.size(); k++) {
                if (listWork.get(k).title.equals(listTrello.get(i).getTitle())) {
                    s = true;
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
                        work.date.add(listTrello.get(j).getDate());
                    }
                }
                listWork.add(work);
                i = i + 1;
            } else
                i = i + 1;
        }
        return ok(Json.toJson(listWork));
    }

    public Result delete() {
        JsonNode json = request().body().asJson();
        System.out.println(json);
        String user = session("connected");
        String titl = json.findPath("title").textValue();
        String tas = json.findPath("task").textValue();
        Model.Finder<String, Trello> finder = new Model.Finder<String, Trello>(String.class, Trello.class);
        Trello trello = finder.where().eq("email_id", user).eq("title", titl).eq("task", tas).findUnique();
        trello.delete();

        return redirect(routes.Application.index());
    }

    public Result deleteList() {
        JsonNode json = request().body().asJson();
        System.out.println(json);
        String user = session("connected");
        String titl = json.findPath("title").textValue();
        Model.Finder<String, Trello> finder = new Model.Finder<String, Trello>(String.class, Trello.class);
        List<Trello> listTrello = finder.where().eq("email_id", user).eq("title", titl).findList();
        for (int i = 0; i < listTrello.size(); i++) {
            listTrello.get(i).delete();
        }
        return redirect(routes.Application.index());
    }
}