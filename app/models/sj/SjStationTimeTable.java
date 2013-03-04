package models.sj;

import java.util.List;

/**
 * Created
 * Date: 2/10/13
 * Time: 1:22 PM
 */
public class SjStationTimeTable extends SjStation {

    private List<SjTrain> departures;
    private List<SjTrain> arrivals;

    public List<SjTrain> getArrivals() {
        return arrivals;
    }

    public void setArrivals(List<SjTrain> arrivals) {
        this.arrivals = arrivals;
    }

    public List<SjTrain> getDepartures() {
        return departures;
    }

    public void setDepartures(List<SjTrain> departures) {
        this.departures = departures;
    }
}
