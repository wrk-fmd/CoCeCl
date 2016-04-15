package it.fmd.cocecl.dataStorage;

import java.io.Serializable;

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

public class IncidentData implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;

    // HEADER
    //Auftrag, Abtransport, Standortwechsel, Transfer
    private String tasktype;
    //Emergency
    private Boolean emergency;
    //PriorityTask
    private Integer priority;

    // BODY
    //Adress
    private String boaddress;
    private String bogrund;
    private String boinfo;
    private String caller;

    // FOOTER
    //AO
    private String aoaddress;
    private String tnumber;

    //STATUS
    private String incistatus; // QU(ASSIGNED), ZBO, ABO, ZAO, AAO, END(AUTO_DETACH)

/*
    public IncidentData(String tasktype, Boolean emergency, String priority, String boaddress, String bogrund, String boinfo, String caller, String aoaddress, String tnumber) {

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
*/

    public IncidentData() {
        super();
    }

    private IncidentData(final int id) {
        super();
        this.id = id;
    }

    public int getId() {
        return id;
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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(final Integer priority) {
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

    public static IncidentData create(final int id) {
        return new IncidentData(id);
    }

}
