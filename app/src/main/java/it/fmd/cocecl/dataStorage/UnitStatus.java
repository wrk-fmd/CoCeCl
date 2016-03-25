package it.fmd.cocecl.dataStorage;

import android.app.Application;

public class UnitStatus {

    public String ustatus; // EB, NEB, AD

    public String ustaddition; // Tanken, Material, Bereitschaft, NEB (andere)

    public UnitStatus(String ustatus, String ustaddition) {

        this.ustatus = ustatus;

        this.ustaddition = ustaddition;
    }

    public String getUstaddition() {
        return ustaddition;
    }

    public String getUstatus() {
        return ustatus;
    }

    public void setUstaddition(String ustaddition) {
        this.ustaddition = ustaddition;
    }

    public void setUstatus(String ustatus) {
        this.ustatus = ustatus;
    }
}
