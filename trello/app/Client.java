import controllers.Application;
import models.models.Trello;
import play.api.libs.mailer.Email;
import play.api.libs.mailer.MailerClient;
import play.api.libs.mailer.MailerModule;

import javax.inject.Inject;

import play.db.ebean.Model;
import play.libs.ws.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class Client {

        private MailerClient mc;

        @Inject
        public Client(MailerClient mc) {
            this.mc = mc;
        }

        public void mail() {
            String pattern = "dd/MM/yyyy";
            String dateInString = new SimpleDateFormat(pattern).format(new Date());
            System.out.println(dateInString);
            play.db.ebean.Model.Finder<String, Trello> finder = new play.db.ebean.Model.Finder<String, Trello>(String.class, Trello.class);
            List<Trello> listTrello1 = finder.where().eq("date", dateInString).findList();
            for (int k = 0; k < listTrello1.size(); k++) {
                play.libs.mailer.Email email = new play.libs.mailer.Email();
                email.setSubject("Due date");
                email.setFrom("saurabh<saurabh@zemosolabs.com>");
                email.addTo(listTrello1.get(k).getEmailId());
                email.setBodyText("ur due date arrvied of task " + listTrello1.get(k).getTask());
                mc.send(email);
            }

        }
}
