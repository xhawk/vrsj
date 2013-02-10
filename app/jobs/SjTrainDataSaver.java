package jobs;

import akka.actor.UntypedActor;
import models.*;
import org.codehaus.jackson.map.ObjectMapper;

import play.Logger;
import play.libs.F;
import play.libs.WS;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created
 * Date: 2/10/13
 * Time: 12:02 PM
 */
public class SjTrainDataSaver extends UntypedActor {

    private static final String SJ_STATIONS_URL = "http://93.190.192.27/api/stations.json";
    private static final String SJ_STATION_TIMETABLE_URL = "http://93.190.192.27/api/stationTimeTable/%s.json";

    @Override
    public void onReceive(Object message) throws Exception {
        Logger.debug("Saving new SJ train lateness info...");

        // TODO: register device

        SjStations stationInfo = createStationInfo();
        List<SjTrain> arrivals = getArrivals(stationInfo);
        long lateInAvarage = 0;

        for (SjTrain arrival : arrivals) {
            Long lateness = arrival.getTime().getLateness();
            Logger.debug("single lateness " + lateness);
            lateInAvarage += lateness;
        }
        lateInAvarage = lateInAvarage / arrivals.size();
        Logger.debug("Saving value " + lateInAvarage);

        AvarageLateness lateness = new AvarageLateness();
        lateness.date = new Date();
        lateness.lateOnAvarage = lateInAvarage;
        lateness.serviceProviderName = "SJ";
        lateness.save();
    }

    private List<SjTrain> getArrivals(SjStations stationInfo) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm"));

        List<SjTrain> arrivals = new ArrayList<SjTrain>();
        for (SjStation station : stationInfo.getStations()) {
            F.Promise<WS.Response> promise = createPromise(String.format(SJ_STATION_TIMETABLE_URL, station.getId()));
            WS.Response res = promise.get();

            SjStationTimeTable timeTable = mapper.readValue(res.asJson(), SjStationTimeTable.class);
            arrivals = timeTable.getArrivals();
        }
        return arrivals;
    }

    private SjStations createStationInfo() throws IOException {
        F.Promise<WS.Response> promise = createPromise(SJ_STATIONS_URL);

        WS.Response res = promise.get();

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(res.asJson(), SjStations.class);
    }

    private F.Promise<WS.Response> createPromise(String sjStationsUrl) {
        return new WS.WSRequestHolder(sjStationsUrl)
                    .setHeader("Appkey", "E252FEDA2EAA72F0CEBF5E9E432C66BB")
                    .setHeader("Devicetype", "iphone")
                    .setHeader("Version", "3")
                    .get();
    }
}
