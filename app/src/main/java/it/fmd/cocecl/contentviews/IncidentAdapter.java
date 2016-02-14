package it.fmd.cocecl.contentviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import it.fmd.cocecl.R;

/**
 * Incident ListView on mainstatusFragment
 **/

public class IncidentAdapter extends ArrayAdapter<Incidents> {

    public IncidentAdapter(Context context, ArrayList<Incidents> incidentses) {

        super(context, 0, incidentses);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position

        Incidents incidents = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.incident_rv_layout, parent, false);

        }

        // Lookup view for data population

        TextView ECode = (TextView) convertView.findViewById(R.id.firstLine);

        TextView EDescription = (TextView) convertView.findViewById(R.id.secondLine);

        TextView BOAdress = (TextView) convertView.findViewById(R.id.thirdLine);

        TextView EStshort = (TextView) convertView.findViewById(R.id.stshort);

        // Populate the data into the template view using the data object

        ECode.setText(incidents.ecode);

        EDescription.setText(incidents.edescription);

        BOAdress.setText(incidents.eadress);

        EStshort.setText(incidents.estshort);

        // Return the completed view to render on screen

        return convertView;

    }
/*
    @Override
    public int getCount() {
        return incidentses.size();
    }
*/
}

/*
    private LayoutInflater mInflater;

    private String[] mStrings;
    private TypedArray mIcons;

    private int mViewResourceId;

    public IncidentAdapter(Context ctx, int viewResourceId, String[] strings, TypedArray icons) {

        super(ctx, viewResourceId, strings);

        mInflater = (LayoutInflater)ctx.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        mStrings = strings;
        mIcons = icons;

        mViewResourceId = viewResourceId;
    }

    @Override
    public int getCount() {
        return mStrings.length;
    }

    @Override
    public String getItem(int position) {
        return mStrings[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);

        ImageView iv = (ImageView)convertView.findViewById(R.id.icon);
        iv.setImageDrawable(mIcons.getDrawable(position));

        TextView header = (TextView)convertView.findViewById(R.id.firstLine);
        header.setText(mStrings[position]);

        TextView footer = (TextView)convertView.findViewById(R.id.secondLine);
        footer.setText(mStrings[position]);

        return convertView;
    }
}
*/

/* Recycler View
public class IncidentAdapter extends RecyclerView.Adapter<IncidentAdapter.ViewHolder> {
    private ArrayList<String> values;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
        }
    }

    public void add(int position, String item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public IncidentAdapter(ArrayList<String> myDataset) {
        values = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public IncidentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.incident_rv_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String name = values.get(position);
        holder.txtHeader.setText(name);
        holder.txtHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(position);
            }
        });

        holder.txtFooter.setText("Footer: " + name);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}
*/