package it.fmd.cocecl.utilclass;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import it.fmd.cocecl.R;

/*
 * TV 192-198 upper
 * TV 193-199 lower
 */

/**
 * PTCA duty goes from 08:00 till 08:00 the next day
 */

public class DialogPTCAInfo {

    public Activity activity;
    protected String akhphone = "+43 1 40400 - 7660";
    TextView daytv1;
    TextView daytv2;

    TextView khtv1;
    TextView khtv2;

    TextView khsttv1;
    TextView khsttv2;

    TextView phnbr1;
    TextView phnbr2;

    String currenttime;
    private String time8 = "08:00:00";
    private String time16 = "16:00:00";

    public DialogPTCAInfo(Activity _activity) {

        this.activity = _activity;
    }

    public boolean checkTime() {

        final Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.GERMAN);
        currenttime = (sdf.format(cal.getTime()));

        try {
            Date time1 = new SimpleDateFormat("HH:mm:ss").parse(time8);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(time1);

            Date time2 = new SimpleDateFormat("HH:mm:ss").parse(time16);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(time2);
            calendar2.add(Calendar.DATE, 1);

            Date timenow = new SimpleDateFormat("HH:mm:ss").parse(currenttime);
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(timenow);
            calendar3.add(Calendar.DATE, 1);

            Date x = calendar3.getTime();
            if (x.after(calendar1.getTime()) && x.before(calendar2.getTime())) {
                //check whether the current time is between 08:00:00 and 16:00:00
                System.out.println(true);
                return true;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean before8() {

        try {

            Date time1 = new SimpleDateFormat("HH:mm:ss").parse(time8);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(time1);

            Date timenow = new SimpleDateFormat("HH:mm:ss").parse(currenttime);
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(timenow);
            calendar3.add(Calendar.DATE, 1);

            Date bv8 = calendar3.getTime();
            if (bv8.before(calendar1.getTime())) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void getcurrentDay() {

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case Calendar.SUNDAY:
                if (before8()) {
                    sat();
                }
                sun();
                break;

            case Calendar.MONDAY:
                if (before8()) {
                    sun();
                }
                mon();

                if (checkTime()) {
                    daytv2.setText("8-16 Uhr");
                    khtv2.setText("AKH");
                    khsttv2.setText("6D");
                    phnbr2.setText(akhphone);
                }
                break;

            case Calendar.TUESDAY:
                if (before8()) {
                    mon();
                }
                tue();

                if (checkTime()) {
                    daytv2.setText("8-16 Uhr");
                    khtv2.setText("AKH");
                    khsttv2.setText("6D");
                    phnbr2.setText(akhphone);
                }
                break;

            case Calendar.WEDNESDAY:
                if (before8()) {
                    tue();
                }
                wed();

                if (checkTime()) {
                    daytv2.setText("8-16 Uhr");
                    khtv2.setText("AKH");
                    khsttv2.setText("6D");
                    phnbr2.setText(akhphone);
                }
                break;

            case Calendar.THURSDAY:
                if (before8()) {
                    wed();
                }
                thu();

                if (checkTime()) {
                    daytv2.setText("8-16 Uhr");
                    khtv2.setText("AKH");
                    khsttv2.setText("6D");
                    phnbr2.setText(akhphone);
                }
                break;

            case Calendar.FRIDAY:
                if (before8()) {
                    thu();
                }
                fry();

                if (checkTime()) {
                    daytv2.setText("8-16 Uhr");
                    khtv2.setText("AKH");
                    khsttv2.setText("6D");
                    phnbr2.setText(akhphone);
                }
                break;

            case Calendar.SATURDAY:
                if (before8()) {
                    fry();
                }
                sat();
                break;
        }
    }

    public void openPTCAPlan() {

        LinearLayout ptcaplan = (LinearLayout) activity.getLayoutInflater().inflate(R.layout.ptca_plan_layout, null);

        daytv1 = (TextView) ptcaplan.findViewById(R.id.textView192);
        daytv2 = (TextView) ptcaplan.findViewById(R.id.textView193);

        khtv1 = (TextView) ptcaplan.findViewById(R.id.textView194);
        khtv2 = (TextView) ptcaplan.findViewById(R.id.textView195);

        khsttv1 = (TextView) ptcaplan.findViewById(R.id.textView196);
        khsttv2 = (TextView) ptcaplan.findViewById(R.id.textView197);

        phnbr1 = (TextView) ptcaplan.findViewById(R.id.textView198);
        phnbr2 = (TextView) ptcaplan.findViewById(R.id.textView199);


        checkTime();
        getcurrentDay();


        AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(activity);
        dlgBuilder.setTitle("PTCA Bereitschaftsplan");
        dlgBuilder.setCancelable(true);

        dlgBuilder.setView(ptcaplan);

        dlgBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                }

        );

        AlertDialog alert = dlgBuilder.create();
        alert.show();
    }

    private void sun() {
        daytv1.setText("SO");
        khtv1.setText("AKH");
        khsttv1.setText("6D");
        phnbr1.setText(akhphone);
    }

    private void mon() {
        daytv1.setText("MO");
        khtv1.setText("RUD");
        khsttv1.setText("12A");
        phnbr1.setText("+43 1 71165 - 92290");
    }

    private void tue() {
        daytv1.setText("DI");
        khtv1.setText("SMZO");
        khsttv1.setText("Pav.3, 3.Stock");
        phnbr1.setText("+43 1 28802 - 743199");
    }

    private void wed() {
        daytv1.setText("MI");
        khtv1.setText("KHH");
        khsttv1.setText("Pav.8, 4.Stock");
        phnbr1.setText("+43 664 8445271");
    }

    private void thu() {
        daytv1.setText("DO");
        khtv1.setText("WIL");
        khsttv1.setText("Pav.29, C Süd");
        phnbr1.setText("+43 1 49150 - 2306");
    }

    private void fry() {
        daytv1.setText("FR");
        khtv1.setText("HAN");
        khsttv1.setText("Pav.2, 2.Stock");
        phnbr1.setText("+43 1 91021 - 85250");
    }

    private void sat() {
        daytv1.setText("SO");
        khtv1.setText("AKH");
        khsttv1.setText("6D");
        phnbr1.setText(akhphone);
    }
}
