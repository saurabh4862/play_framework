import com.google.inject.Guice;
import play.Application;
import play.GlobalSettings;
import play.Play;
import play.api.inject.Injector;
import play.api.libs.mailer.MailerClient;
import play.libs.Akka;
import play.libs.mailer.Email;
import scala.concurrent.duration.Duration;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

public class Global extends GlobalSettings {

    @Inject
    MailerClient mailerClient;



    @Override
    public void onStart(Application app) {


        MailerClient mc = Play.application().injector().instanceOf(MailerClient.class);
        /*Email email = new Email();
        email.setSubject("Due date");
        email.setFrom("saurabh<saurabh@zemosolabs.com>");
        email.addTo("saurabh@zemosolabs.com");
        email.setBodyText("ur due date arrvied of task ");
        mc.send(email);*/


        System.out.println("Application Started");
       Akka.system().scheduler().schedule(
               Duration.create(0, TimeUnit.MILLISECONDS),
               Duration.create(1440, TimeUnit.MINUTES),
               new Runnable() {
                    public void run() {
                        System.out.println("Scheduler Started");
                        Client client = new Client(mc);
                        client.mail();
                    }
                },
                Akka.system().dispatcher()
        );
    }
}


