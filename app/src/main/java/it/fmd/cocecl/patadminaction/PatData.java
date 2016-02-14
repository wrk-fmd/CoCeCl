package it.fmd.cocecl.patadminaction;

/**
 * Store Patient Data in this class
 **/

/**
 * Patient Data
 * <p>
 * ID
 * PLS Number
 * Gender
 * Familyname
 * Name
 * Date of birth
 * Insurance Number
 * AO Hospital + Ward
 **/

public class PatData {

    private int id;
    private int PLSNr;
    private String gender;
    private String familyname;
    private String name;
    private long bDate;
    private int InsNr;
    private String aoWard;

    public PatData() {
        super();
    }

    public PatData(int id, int PLSNr, String gender, String familyname, String name, long bDate, int InsNr, String aoWard) {
        super();

        this.id = id;
        this.PLSNr = PLSNr;
        this.gender = gender;
        this.familyname = familyname;
        this.name = name;
        this.bDate = bDate;
        this.InsNr = InsNr;
        this.aoWard = aoWard;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPLSNr() {
        return PLSNr;
    }

    public void setPLSNr(int PLSNr) {
        this.PLSNr = PLSNr;
    }

    public String getFamilyname() {
        return name;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getbDate() {
        return bDate;
    }

    public void setbDate(long bDate) {
        this.bDate = bDate;
    }

    public int getInsNr() {
        return InsNr;
    }

    public void setInsNr(int insNr) {
        this.InsNr = InsNr;
    }

    public String getAoWard() {
        return name;
    }

    public void setAoWard(String aoWard) {
        this.aoWard = aoWard;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PatData other = (PatData) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        return "Patient [id=" + id + ", familyname=" + familyname + ", aoWard="
                + aoWard + ", PLS=" + PLSNr + "]";
    }
}

