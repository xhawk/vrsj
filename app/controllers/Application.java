package controllers;

import models.AvarageLateness;
import models.TrainService;
import play.db.ebean.Model;
import play.mvc.*;

import views.html.*;

import java.util.*;

import static play.libs.Json.toJson;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public static Result getSj() {
        List<AvarageLateness> result = new Model.Finder(String.class, AvarageLateness.class).where().eq("serviceProviderName", "SJ").findList();
        return ok(toJson(result));
    }

    /**
     *
     * @return
     */
    public static Result getVrsj() {
        List<TrainService> result = new ArrayList<TrainService>();
        result.add(new TrainService("VR"));
        result.add(new TrainService("SJ"));

        return ok(toJson(result));
    }
}
