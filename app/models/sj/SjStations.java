package models.sj;

import java.util.List;

/**
 * Created
 * Date: 2/10/13
 * Time: 1:00 PM
 */
public class SjStations {

    public List<SjStation> getStations() {
        return stations;
    }

    public void setStations(List<SjStation> stations) {
        this.stations = stations;
    }

    private List<SjStation> stations;
}
