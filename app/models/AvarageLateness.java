package models;

import play.data.format.Formats;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created
 * Date: 2/10/13
 * Time: 10:25 AM
 */
@Entity
public class AvarageLateness extends Model {

    public static final String DATE_FORMAT_PATTERN = "dd/MM/yyyy hh:mm";

    @Id
    public String id;

    @Formats.DateTime(pattern= DATE_FORMAT_PATTERN)
    public Date date;

    public long lateOnAvarage;

    public String serviceProviderName;
}
