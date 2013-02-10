import akka.actor.ActorRef;
import akka.actor.Props;
import akka.util.Duration;
import jobs.SjTrainDataSaver;
import jobs.VrTrainDataSaver;
import play.*;
import play.libs.Akka;

import java.util.concurrent.TimeUnit;

public class Global extends GlobalSettings {

    @Override
    public void onStart(Application app) {
        Logger.info("Application has started");
        ActorRef vrTrainDataSaver = Akka.system().actorOf(new Props(VrTrainDataSaver.class));
        ActorRef sjTrainDataSaver = Akka.system().actorOf(new Props(SjTrainDataSaver.class));

        Akka.system().scheduler().schedule(
                Duration.create(0L, TimeUnit.MILLISECONDS), //Initial delay 0 milliseconds
                Duration.create(10L, TimeUnit.MINUTES),     //Frequency 10 minutes
                vrTrainDataSaver,
                "vr"
        );
        Akka.system().scheduler().schedule(
                Duration.create(0L, TimeUnit.MILLISECONDS), //Initial delay 0 milliseconds
                Duration.create(1L, TimeUnit.MINUTES),     //Frequency 10 minutes
                sjTrainDataSaver,
                "sj"
        );
    }

}