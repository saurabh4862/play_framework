import models.models.Trello;
import play.db.ebean.Model;
import play.libs.mailer.MailerClient;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import play.libs.mailer.Email;
import play.libs.mailer.MailerClient;
/**
 * Created by zemoso07 on 18/8/15.
 */
public class Client {

    public MailerClient mc;

    @Inject
    public Client(MailerClient mc) {
        this.mc = mc;
    }
    public void mailClient(){


        String pattern = "dd/MM/yyyy";
                        String dateInString = new SimpleDateFormat(pattern).format(new Date());
                        System.out.println(dateInString );
        Model.Finder<String, Trello> finder = new Model.Finder<String, Trello>(String.class, Trello.class);
                        List<Trello> listTrello1 = finder.where().eq("date", dateInString).findList();
                        for (int k = 0; k < listTrello1.size(); k++) {
                            Email email = new Email();
                            email.setSubject("Due date");
                            email.setFrom("saurabh<saurabh@zemosolabs.com>");
                            email.addTo(listTrello1.get(k).getEmailId());
                            email.setBodyText("ur due date arrvied");
                            mc.send(email);
                        }



    }
}
