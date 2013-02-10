package models;

import play.Logger;

import java.util.Date;

/**
 * Created
 * Date: 2/10/13
 * Time: 1:39 PM
 */
public class SjTime {

    private Date scheduledTime;

    private Date newTime;

    /**
     * Late is plus, early is minus
     * @return
     */
    public Long getLateness() {
        if (newTime != null) {
            long lateness = (newTime.getTime() - scheduledTime.getTime()) / 1000;
            Logger.debug("SjTime : lateness " + lateness + " = " + (newTime.getTime() - scheduledTime.getTime()));
            return lateness;
        }
        return 0L;
    }

    public Date getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(Date scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public Date getNewTime() {
        return newTime;
    }

    public void setNewTime(Date newTime) {
        this.newTime = newTime;
    }
}
