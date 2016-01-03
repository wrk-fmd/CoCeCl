package it.fmd.cocecl.contentviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import it.fmd.cocecl.R;

public class AssignedUnitsAdapter extends ArrayAdapter<AssignedUnits> {

    public AssignedUnitsAdapter(Context context, ArrayList<AssignedUnits> aunitses) {

        super(context, 0, aunitses);

    }

    // Constructor
    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position

        AssignedUnits aunitses = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.assignedunits_layout, parent, false);

        }

        // Lookup view for data population

        TextView AUnit = (TextView) convertView.findViewById(R.id.assignedUnitTV);

        // Populate the data into the template view using the data object

        AUnit.setText(aunitses.aunit);

        // Return the completed view to render on screen

        return convertView;

    }

}