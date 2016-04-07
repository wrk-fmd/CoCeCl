package it.fmd.cocecl.dataStorage.cocesoAPI.incidentAPI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import it.fmd.cocecl.dataStorage.IncidentData;
import it.fmd.cocecl.dataStorage.cocesoAPI.Answer;

public class GetActiveIncidentsAnswer extends Answer implements Serializable {
    private static final long serialVersionUID = 1L;

    private Collection<IncidentData> activeIncidents;

    private GetActiveIncidentsAnswer() {
        super();
    }

    private GetActiveIncidentsAnswer(final boolean success, final Collection<IncidentData> activeIncidents) {
        super();
        setSuccess(success);
        // NullPointerException on given null parameter intended
        this.activeIncidents = new ArrayList<>(activeIncidents);
    }

    public Collection<IncidentData> getActiveIncidents() {
        return activeIncidents;
    }

    public void setActiveIncidents(Collection<IncidentData> activeIncidents) {
        this.activeIncidents = activeIncidents;
    }

    public static GetActiveIncidentsAnswer create(boolean success, Collection<IncidentData> activeIncidents) {
        return new GetActiveIncidentsAnswer(success, activeIncidents);
    }

    @Override
    public String toString() {
        return super.toString() + "[" +
                "success=" + getSuccess() + "," +
                "activeIncidents=" + activeIncidents +
                "]";
    }
}