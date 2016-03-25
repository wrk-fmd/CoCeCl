package it.fmd.cocecl.fragments;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import it.fmd.cocecl.APPConstants;
import it.fmd.cocecl.R;

public class communicationFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_communication, container, false);

        return v;
    }

    public void comcalls(View view) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        PackageManager pm = getContext().getPackageManager();
        switch (view.getId()) {

            case R.id.button65:

                callIntent.setData(Uri.parse("tel:" + APPConstants.polnbr));
                startActivity(callIntent);

                if (pm.checkPermission(Manifest.permission.CALL_PHONE, getContext().getPackageName()) == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                break;

            case R.id.button64:
                callIntent.setData(Uri.parse("tel:" + APPConstants.fdnbr));
                startActivity(callIntent);

                if (pm.checkPermission(Manifest.permission.CALL_PHONE, getContext().getPackageName()) == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                break;

            case R.id.button67:
                EditText dialer = (EditText) getActivity().findViewById(R.id.dialereditext);
                String dialernbr = dialer.getText().toString();

                if (dialer.getText().toString().trim().length() > 0) {

                    callIntent.setData(Uri.parse("tel:" + dialernbr));
                    startActivity(callIntent);

                    if (pm.checkPermission(Manifest.permission.CALL_PHONE, getContext().getPackageName()) == PackageManager.PERMISSION_GRANTED) {

                    } else {

                    }

                } else {
                    Toast.makeText(getContext(), "Keine Nummer eingegeben!", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
