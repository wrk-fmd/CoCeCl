package it.fmd.cocecl.dataStorage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import it.fmd.cocecl.dataStorage.cocesoAPI.incidentAPI.GetActiveIncidentsAnswer;

public class ActiveIncidents {

    public static void main(String[] args) {

        Gson gson = new GsonBuilder().create();

        Collection<IncidentData> incidents = prepareIncidents();
        GetActiveIncidentsAnswer answer = GetActiveIncidentsAnswer.create(true, incidents);

        println("Answer is: %s", answer.toString());

        String serializedAnswer = gson.toJson(answer);

        println("Answer as json: %s", serializedAnswer);

        GetActiveIncidentsAnswer deserializedAnswer = gson.fromJson(serializedAnswer, GetActiveIncidentsAnswer.class);

        println("Answer deserialized: %s", deserializedAnswer);
    }

    private static Collection<IncidentData> prepareIncidents() {
        Collection<IncidentData> incidents = new ArrayList<>();
        incidents.add(prepareIncident(123));
        incidents.add(prepareIncident(234));
        return incidents;
    }

    private static IncidentData prepareIncident(int id) {
        IncidentData incident = IncidentData.create(id);
        /*List<UnitData> units = Collections.singletonList(UnitData.create(id * 2 - 5));
        incident.setUnits(units);
        incident.setBlue(id % 2 == 0);
        incident.setCaller("Just any caller");*/
        return incident;
    }

    private static void println(final String message, final Object... parameters) {
        System.out.println(String.format(message, parameters));
    }
}
