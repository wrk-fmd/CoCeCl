package it.fmd.cocecl.contentviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import it.fmd.cocecl.R;
import it.fmd.cocecl.dataStorage.GCMMessage;

public class GCMMessageAdapter extends ArrayAdapter<GCMMessage> {

    TextView sender, date, body, title;

    /**
     * TextView 11 = Sender
     * TextView 142 = DateTime
     * TextView 143 = Title
     * TextView 144 = Body
     *
     * @param context
     * @param gcmMessages
     */

    public GCMMessageAdapter(Context context, ArrayList<GCMMessage> gcmMessages) {

        super(context, 0, gcmMessages);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position

        GCMMessage gcmMessage = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.gcm_message_layout, parent, false);

        }

        // Lookup view for data population

        sender = (TextView) convertView.findViewById(R.id.textView11);

        date = (TextView) convertView.findViewById(R.id.textView142);

        title = (TextView) convertView.findViewById(R.id.textView143);

        body = (TextView) convertView.findViewById(R.id.textView144);

        // Populate the data into the template view using the data object

        sender.setText(gcmMessage.getId());

        date.setText(gcmMessage.getCreatedAt());

        title.setText(gcmMessage.getTitle());

        body.setText(gcmMessage.getMessage());

        // Return the completed view to render on screen

        return convertView;

    }
}
