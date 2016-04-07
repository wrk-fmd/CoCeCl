package it.fmd.cocecl.dataStorage;

import java.io.Serializable;

public class UnitData implements Serializable {
    private static final long serialVersionUID = 1L;

    int unitID;

    String unitname;
    String unameshort;

    String unitnumber; // rufzeichen
    String vehicleplate; // kennzeichen

    String unittype;
    String unittask;

    Boolean mdunit;
    Boolean mobileunit;

    public int getUnitID() {
        return unitID;
    }

    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public String getUnameshort() {
        return unameshort;
    }

    public void setUnameshort(String unameshort) {
        this.unameshort = unameshort;
    }

    public String getUnitnumber() {
        return unitnumber;
    }

    public void setUnitnumber(String unitnumber) {
        this.unitnumber = unitnumber;
    }

    public String getUnittype() {
        return unittype;
    }

    public void setUnittype(String unittype) {
        this.unittype = unittype;
    }

    public String getUnittask() {
        return unittask;
    }

    public void setUnittask(String unittask) {
        this.unittask = unittask;
    }

    public String getVehicleplate() {
        return vehicleplate;
    }

    public void setVehicleplate(String vehicleplate) {
        this.vehicleplate = vehicleplate;
    }

    public Boolean getMdunit() {
        return mdunit;
    }

    public void setMdunit(Boolean mdunit) {
        this.mdunit = mdunit;
    }

    public Boolean getMobileunit() {
        return mobileunit;
    }

    public void setMobileunit(Boolean mobileunit) {
        this.mobileunit = mobileunit;
    }
}
