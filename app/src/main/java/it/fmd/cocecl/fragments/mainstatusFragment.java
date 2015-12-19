package it.fmd.cocecl.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import it.fmd.cocecl.R;

import static android.graphics.Color.GREEN;

public class mainstatusFragment extends Fragment {

    /*
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }
    */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mainstatus2, container, false);

        final Button button38 = (Button) v.findViewById(R.id.button38);
        final Button button39 = (Button) v.findViewById(R.id.button39);
        final Button button40 = (Button) v.findViewById(R.id.button40);

        //return v;

        button38.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st1();
            }
        });

        button39.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st6();
            }
        });

        button40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st9();
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
    }


    // Button state & color functions START //
    // Status EB NEB AD mainstatusFragment //
    // TODO: set by server ebst(); nebst(); adst(); not by user (for now)

    public void st1() {

        AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(getActivity());
        dlgBuilder.setMessage("Einheit EB melden?");
        dlgBuilder.setCancelable(false);
        dlgBuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                ebst();

                //Toast.makeText(getActivity(), "Im Dienst", Toast.LENGTH_SHORT).show();
            }
        });

        dlgBuilder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog alert = dlgBuilder.create();
        alert.show();
    }


    public void ebst() {

        Button button38 = (Button) getActivity().findViewById(R.id.button38);
        Button button39 = (Button) getActivity().findViewById(R.id.button39);
        Button button40 = (Button) getActivity().findViewById(R.id.button40);

        CardView cardviewst = (CardView) getActivity().findViewById(R.id.cardviewst);
        LinearLayout statusbtnlinlay = (LinearLayout) getActivity().findViewById(R.id.statusbtnlinlay);

        TextView textView111 = (TextView) getActivity().findViewById(R.id.textView111);

        button38.setEnabled(false);
        button38.setClickable(false);
        button38.setBackgroundColor(GREEN);

        button39.setEnabled(true);
        button39.setClickable(false);
        button39.setBackgroundColor(Color.parseColor("#bdbdbd"));
        //button39.setBackgroundResource(android.R.drawable.btn_default);

        button40.setEnabled(true);
        button40.setClickable(false);
        button40.setBackgroundColor(Color.parseColor("#bdbdbd"));
        //button40.setBackgroundResource(android.R.drawable.btn_default);

        //cardviewst.setCardBackgroundColor(GREEN);
        statusbtnlinlay.setBackgroundColor(GREEN);

        textView111.setVisibility(View.VISIBLE);
        textView111.setText(R.string.eb);

    }

    public void st6() {

        final TextView textView111 = (TextView) getActivity().findViewById(R.id.textView111);

        AlertDialog.Builder dlgbuilder = new AlertDialog.Builder(getActivity());
        dlgbuilder.setTitle("Einheit nicht einsatzbereit melden?");
        dlgbuilder.setItems(new CharSequence[]
                        {"NEB (andere Grund)", "Tanken", "Material nachfassen", "Bereitschaft", "Nein"},

                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        switch (which) {
                            case 0:
                                nebst();
                                //Toast.makeText(getActivity(), "Nicht EB", Toast.LENGTH_SHORT).show();
                                textView111.setVisibility(View.VISIBLE);
                                textView111.setText("Nicht EB");
                                break;
                            case 1:
                                nebst();
                                //Toast.makeText(getActivity(), "Tanken", Toast.LENGTH_SHORT).show();
                                textView111.setVisibility(View.VISIBLE);
                                textView111.setText("Tanken");
                                break;
                            case 2:
                                nebst();
                                //Toast.makeText(getActivity(), "Mat. nachfassen", Toast.LENGTH_SHORT).show();
                                textView111.setVisibility(View.VISIBLE);
                                textView111.setText("Material nachfassen");
                                break;
                            case 3:
                                nebst();
                                //Toast.makeText(getActivity(), "Bereitschaft", Toast.LENGTH_SHORT).show();
                                textView111.setVisibility(View.VISIBLE);
                                textView111.setText("Bereitschaft");
                                break;
                            case 4:
                                //Toast.makeText(getActivity(), "weiter EB", Toast.LENGTH_SHORT).show();
                                textView111.setVisibility(View.VISIBLE);
                                textView111.setText("weiter EB");
                                break;
                        }
                    }
                });
        dlgbuilder.create().show();
    }


    public void nebst() {

        Button button38 = (Button) getActivity().findViewById(R.id.button38);
        Button button39 = (Button) getActivity().findViewById(R.id.button39);
        Button button40 = (Button) getActivity().findViewById(R.id.button40);

        CardView cardviewst = (CardView) getActivity().findViewById(R.id.cardviewst);
        LinearLayout statusbtnlinlay = (LinearLayout) getActivity().findViewById(R.id.statusbtnlinlay);

        button38.setEnabled(true);
        button38.setClickable(false);
        button38.setBackgroundColor(Color.parseColor("#bdbdbd"));
        //button38.setBackgroundResource(android.R.drawable.btn_default);

        button39.setEnabled(false);
        button39.setClickable(false);
        button39.setBackgroundColor(Color.parseColor("#EF6C00"));

        button40.setEnabled(true);
        button40.setClickable(false);
        button40.setBackgroundColor(Color.parseColor("#bdbdbd"));
        //button40.setBackgroundResource(android.R.drawable.btn_default);

        //cardviewst.setCardBackgroundColor(Color.parseColor("#EF6C00"));
        statusbtnlinlay.setBackgroundColor(Color.parseColor("#EF6C00"));
    }

    public void st9() {

        AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(getActivity());
        dlgBuilder.setMessage("Einheit ausser Dienst stellen?");
        dlgBuilder.setCancelable(false);
        dlgBuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                adst();

                Toast.makeText(getActivity(), "Ausser Dienst", Toast.LENGTH_SHORT).show();
            }
        });

        dlgBuilder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog alert = dlgBuilder.create();
        alert.show();
    }


    public void adst() {

        Button button38 = (Button) getActivity().findViewById(R.id.button38);
        Button button39 = (Button) getActivity().findViewById(R.id.button39);
        Button button40 = (Button) getActivity().findViewById(R.id.button40);

        CardView cardviewst = (CardView) getActivity().findViewById(R.id.cardviewst);
        LinearLayout statusbtnlinlay = (LinearLayout) getActivity().findViewById(R.id.statusbtnlinlay);

        TextView textView111 = (TextView) getActivity().findViewById(R.id.textView111);

        button38.setEnabled(true);
        button38.setClickable(false);
        button38.setBackgroundColor(Color.parseColor("#bdbdbd"));
        //button38.setBackgroundResource(android.R.drawable.btn_default);

        button39.setEnabled(true);
        button39.setClickable(false);
        button39.setBackgroundColor(Color.parseColor("#bdbdbd"));
        //button39.setBackgroundResource(android.R.drawable.btn_default);

        button40.setEnabled(false);
        button40.setClickable(false);
        button40.setBackgroundColor(Color.parseColor("#9C27B0"));

        textView111.setVisibility(View.VISIBLE);
        textView111.setText("Ausser Dienst");

        //cardviewst.setCardBackgroundColor(Color.parseColor("#9C27B0"));
        statusbtnlinlay.setBackgroundColor(Color.parseColor("#9C27B0"));
    }
}