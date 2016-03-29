package it.fmd.cocecl.dataStorage;

import android.app.Application;

/**
 * Incident Information
 * Tasktype: Auftrag, Standortwechsel, Transfer, Einsatz
 * Emergency: +Sondersignal
 * Priority: (optional)
 * BoAddress: Berufungsort
 * BoGrund: Berufungsgrund, Einsatzstichwort
 * BoInfo: Zusatzinformation, Freitext
 * Caller: Berufer, Name - Tel
 * AoAddress: Abgabeort
 * TNummer: Transportnummer
 */

public class IncidentData {

    private static IncidentData mInstance = null;

    // HEADER
    //Auftrag, Abtransport, Standortwechsel, Transfer
    public String tasktype;
    //Emergency
    public Boolean emergency;
    //PriorityTask
    public String priority;

    // BODY
    //Adress
    public String boaddress;
    public String bogrund;
    public String boinfo;
    public String caller;

    // FOOTER
    //AO
    public String aoaddress;
    public String tnumber;

    //STATUS
    public String incistatus;

    /*
        public IncidentData(String tasktype, String emergency, String priority, String boaddress, String bogrund, String boinfo, String caller, String aoaddress, String tnumber) {

            this.tasktype = tasktype;
            this.emergency = emergency;
            this.priority = priority;
            this.boaddress = boaddress;
            this.bogrund = bogrund;
            this.boinfo = boinfo;
            this.caller = caller;
            this.aoaddress = aoaddress;
            this.tnumber = tnumber;
        }
    */

    public IncidentData() {

        this.tasktype = tasktype;
        this.emergency = emergency;
        this.priority = priority;
        this.boaddress = boaddress;
        this.bogrund = bogrund;
        this.boinfo = boinfo;
        this.caller = caller;
        this.aoaddress = aoaddress;
        this.tnumber = tnumber;
    }

    public static IncidentData getInstance() {
        if (mInstance == null) {
            mInstance = new IncidentData();
        }
        return mInstance;
    }

    public String getTasktype() {
        return tasktype;
    }

    public void setTasktype(String tasktype) {
        this.tasktype = tasktype;
    }

    public Boolean getEmergency() {
        return emergency;
    }

    public void setEmergency(Boolean emergency) {
        this.emergency = emergency;
    }

    public String getAoaddress() {
        return aoaddress;
    }

    public void setAoaddress(String aoaddress) {
        this.aoaddress = aoaddress;
    }

    public String getBoaddress() {
        return boaddress;
    }

    public void setBoaddress(String boaddress) {
        this.boaddress = boaddress;
    }

    public String getBogrund() {
        return bogrund;
    }

    public void setBogrund(String bogrund) {
        this.bogrund = bogrund;
    }

    public String getBoinfo() {
        return boinfo;
    }

    public void setBoinfo(String boinfo) {
        this.boinfo = boinfo;
    }

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTnumber() {
        return tnumber;
    }

    public void setTnumber(String tnumber) {
        this.tnumber = tnumber;
    }

    // Incident Status
    public String getIncistatus() {
        return incistatus;
    }

    public void setIncistatus(String incistatus) {
        this.incistatus = incistatus;
    }

}
