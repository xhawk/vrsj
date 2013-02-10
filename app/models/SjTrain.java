package models;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created
 * Date: 2/10/13
 * Time: 1:31 PM
 */
public class SjTrain {

    private Integer trainNumber;
    private String date;
    private SjTime time;
    private Map track;
    private String fromStationName;
    private String toStationName;
    private List stationNames;
    private Boolean isPassed;
    private Boolean cancelled;

    public Integer getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(Integer trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public SjTime getTime() {
        return time;
    }

    public void setTime(SjTime time) {
        this.time = time;
    }

    public Map getTrack() {
        return track;
    }

    public void setTrack(Map track) {
        this.track = track;
    }

    public String getFromStationName() {
        return fromStationName;
    }

    public void setFromStationName(String fromStationName) {
        this.fromStationName = fromStationName;
    }

    public String getToStationName() {
        return toStationName;
    }

    public void setToStationName(String toStationName) {
        this.toStationName = toStationName;
    }

    public List getStationNames() {
        return stationNames;
    }

    public void setStationNames(List stationNames) {
        this.stationNames = stationNames;
    }

    public Boolean getIsPassed() {
        return isPassed;
    }

    public void setIsPassed(Boolean passed) {
        isPassed = passed;
    }

    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }
}
