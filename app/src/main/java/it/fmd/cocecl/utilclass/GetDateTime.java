package it.fmd.cocecl.utilclass;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class GetDateTime {

    final Calendar cal = Calendar.getInstance(TimeZone.getDefault());
    final SimpleDateFormat timesdf = new SimpleDateFormat("HH:mm:ss", Locale.GERMAN);
    final SimpleDateFormat datesdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.GERMAN);

    final SimpleDateFormat daysdf = new SimpleDateFormat("E", Locale.GERMAN);

    Date date = new Date();

    String currenttime = (timesdf.format(cal.getTime()));
    String currentdate = (datesdf.format(cal.getTime()));
    String currentdayshrt = (daysdf.format(date));

    public String getcurrentTime() {
        return currenttime;
    }

    public String getcurrentDate() {
        return currentdate;
    }

    public String getcurrentDay() {
        return currentdayshrt;
    }
}
