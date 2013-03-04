package models;

import play.Logger;
import play.db.ebean.Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Service for querying train data saved in h2-database
 */
public class TrainService {

    private String name;
    private long dayAvarage;
    private long monthAvarage;

    public TrainService(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDayAvarage() {
        return countAvarage(getAvarageLatenessesBetween(getDatesBetweenMidnightAndNow(), this.name));
    }

    public long getMonthAvarage() {
        return countAvarage(getAvarageLatenessesBetween(getDatesBetween30DaysAgondNow(), this.name));
    }

    private static List<AvarageLateness> getAvarageLatenessesBetween(Date[] between, String serviceProviderName) {
        return new Model.Finder<String, AvarageLateness>(String.class, AvarageLateness.class).
                where().between("date", between[0], between[1]).eq("serviceProviderName", serviceProviderName).findList();
    }

    private static long countAvarage(List<AvarageLateness> vrResult) {
        long result = 0;
        if (vrResult.size()>0) {
            for (AvarageLateness lateness : vrResult) {
                result += lateness.lateOnAvarage;
            }
            result = result / vrResult.size();
        }
        return result;
    }

    private static Date[] getDatesBetween30DaysAgondNow() {
        DateFormat df = new SimpleDateFormat(AvarageLateness.DATE_FORMAT_PATTERN);
        Calendar cal = Calendar.getInstance();
        Date now = cal.getTime();
        cal.add(Calendar.DAY_OF_MONTH, -30);
        Date thirtyDaysAgo = cal.getTime();

        Logger.debug("Finding items from database between dates: " + df.format(thirtyDaysAgo) + " - " + df.format(now));

        return new Date[]{thirtyDaysAgo, now};
    }

    private static Date[] getDatesBetweenMidnightAndNow() {
        DateFormat df = new SimpleDateFormat(AvarageLateness.DATE_FORMAT_PATTERN);

        Calendar cal = Calendar.getInstance();
        Date now = cal.getTime();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        Date fromMidnight = cal.getTime();

        Logger.debug("Finding items from database between dates: " + df.format(fromMidnight) + " - " + df.format(now));
        return new Date[]{fromMidnight, now};
    }
}
