package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import models.Users;
import org.mindrot.jbcrypt.BCrypt;
import play.*;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public Result signup()
    {
        return ok(signup.render("Signup"));
    }

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
        String firstname = requestData.get("firstName");
        String lastname = requestData.get("lastName");
        String password =requestData.get("password");
        Model.Finder<String,Users> finder = new Model.Finder<String, Users>(String.class,Users.class);
        Users users = finder.where().eq("first_name",firstname).eq("last_name",lastname).findUnique();
        String acPass="";
        try {
            acPass = users.getPassword();
        } catch (NullPointerException npe) {
            // It's fine if findUser throws a NPE
        }

        if (BCrypt.checkpw(password, acPass))
            return ok(" Hello " + firstname + " " + lastname+" "+password);
        else
            return ok("Error ");

    }

}
