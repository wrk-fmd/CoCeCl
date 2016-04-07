package it.fmd.cocecl.dataStorage.cocesoAPI.incidentAPI;

import java.io.Serializable;

public class GetActiveIncidentsRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String session;

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}
