import play.Application;
import play.GlobalSettings;
import play.api.libs.mailer.MailerClient;
import play.libs.Akka;
import scala.concurrent.duration.Duration;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

public class Global extends GlobalSettings {

    @Override
    public void onStart(Application app) {
        System.out.println("Application Started");
        Akka.system().scheduler().schedule(
                Duration.create(0, TimeUnit.MILLISECONDS),
                Duration.create(1440, TimeUnit.MINUTES),
                new Runnable() {
                    public void run() {
                        System.out.println("Scheduler Started");
                        Client client =new Client(Client.);
                        client.mailClient();
                    }
                },
                Akka.system().dispatcher()
        );
    }
}


