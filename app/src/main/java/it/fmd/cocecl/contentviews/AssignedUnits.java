package it.fmd.cocecl.contentviews;

/**
 * Incident ListView on mainstatusFragment
 **/

public class AssignedUnits {

    private static AssignedUnits mInstance = null;

    public static AssignedUnits getInstance() {
        if (mInstance == null) {
            mInstance = new AssignedUnits();
        }
        return mInstance;
    }

    public String aunit;

    public String statusaunit;

    /*
    public AssignedUnits(String aunit, String statusaunit) {

        this.aunit = aunit;

        this.statusaunit = statusaunit;
    }
    */

    public String getAunit() {
        return aunit;
    }

    public void setAunit(String aunit) {
        this.aunit = aunit;
    }

    public String getStatusaunit() {
        return statusaunit;
    }

    public void setStatusaunit(String statusaunit) {
        this.statusaunit = statusaunit;
    }
}