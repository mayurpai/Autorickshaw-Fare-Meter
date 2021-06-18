package com.example.myapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.MessageFormat;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EstimateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EstimateFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EstimateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EstimateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EstimateFragment newInstance(String param1, String param2) {
        EstimateFragment fragment = new EstimateFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    EditText enterSource,enterDestination;
    TextView fare;
    Button calFare;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_estimate, container, false);
        enterSource = view.findViewById(R.id.editText);
        enterDestination = view.findViewById(R.id.editText4);
        fare = view.findViewById(R.id.textView10);
        calFare = view.findViewById(R.id.button);
        //2.84    //3.43               //4.56  //6.15  //7.13       //7.86
        String sources[] = {"Sahyadri","V K Shetty Auditorium","Adyar Complex","Barakah International","Shetty Lunch Home","Kannur","First Neuro Hospital","Padil","Nagori","Pumpwell","Kankanady"};
        String destinations[] = {"Sahyadri","V K Shetty Auditorium","Adyar Complex","Barakah International","Shetty Lunch Home","Kannur","First Neuro Hospital","Padil","Nagori","Pumpwell","Kankanady"};
        String minKm = "2";
        String minCharge = "23.00";
        String addChargePerKm = "13";
        double distFive = Double.parseDouble(minCharge)+(0.84*13);
        double distSix = Double.parseDouble(minCharge)+(1.43*13);
        double distSeven = Double.parseDouble(minCharge)+(2.56*13);
        double distEight = Double.parseDouble(minCharge)+(4.15*13);
        double distNine = Double.parseDouble(minCharge)+(5.13*13);
        double distTen = Double.parseDouble(minCharge)+(5.86*13);

        calFare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(enterSource.getText().toString().matches("") && enterDestination.getText().toString().matches("")){
                    Toast toast = Toast.makeText(getActivity(), "Enter The Source And Destination!", Toast.LENGTH_SHORT);
                    toast.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, 1000);
                }
                if(enterSource.getText().toString().matches("")){
                    Toast toast = Toast.makeText(getActivity(), "Enter The Source!", Toast.LENGTH_SHORT);
                    toast.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, 1000);
                }
                if(enterDestination.getText().toString().matches("")){
                    Toast toast = Toast.makeText(getActivity(), "Enter The Destination!", Toast.LENGTH_SHORT);
                    toast.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, 1000);
                }
                for (int s=0;s<sources.length;s++) {
                    for (int d=0;d<destinations.length;d++) {
//                        if(!(enterSource.getText().toString().equals("Sahyadri")) ||
//                                !(enterSource.getText().toString().equals("V K Shetty Auditorium")) ||
//                                !(enterSource.getText().toString().equals("Adyar Complex")) ||
//                                !(enterSource.getText().toString().equals("Barakah International")) ||
//                                !(enterSource.getText().toString().equals("Shetty Lunch Home")) ||
//                                !(enterSource.getText().toString().equals("Kannur")) ||
//                                !(enterSource.getText().toString().equals("First Neuro Hospital")) ||
//                                !(enterSource.getText().toString().equals("Padil")) ||
//                                !(enterSource.getText().toString().equals("Nagori")) ||
//                                !(enterSource.getText().toString().equals("Pumpwell")) ||
//                                !(enterSource.getText().toString().equals("Kankanady"))) {
//                            Toast toast = Toast.makeText(getActivity(), "Source/Destination Not Within Our Database!", Toast.LENGTH_SHORT);
//                            toast.show();
//                            Handler handler = new Handler();
//                            handler.postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//                                    toast.cancel();
//                                }
//                            }, 1000);
//                        }
//                        if(!(enterDestination.getText().toString().equals("Sahyadri|V K Shetty Auditorium|Adyar Complex|Barakah International|Shetty Lunch Home|Kannur|First Neuro Hospital|Padil|Nagori|Pumpwell|Kankanady"))) {
//                            Toast toast = Toast.makeText(getActivity(), "Source/Destination Not Within Our Database!", Toast.LENGTH_SHORT);
//                            toast.show();
//                            Handler handler = new Handler();
//                            handler.postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//                                    toast.cancel();
//                                }
//                            }, 1000);
//                        }
                        if (sources[s].equals(enterSource.getText().toString()) && destinations[d].equals(enterDestination.getText().toString())) {
                            int sourceVar = s;
                            int destinationVar = d;
                            int dist = Math.abs(s-d);
                            if (dist == 10) {
                                fare.setText(MessageFormat.format("{0}₹ " + distTen, fare.getText()));
                                calFare.setClickable(false);
                            }
                            else if (dist == 9) {
                                fare.setText(MessageFormat.format("{0}₹ " + distNine, fare.getText()));
                                calFare.setClickable(false);
                            }
                            else if (dist == 8) {
                                fare.setText(MessageFormat.format("{0}₹ " + distEight, fare.getText()));
                                calFare.setClickable(false);
                            }
                            else if (dist == 7) {
                                fare.setText(MessageFormat.format("{0}₹ " + distSeven, fare.getText()));
                                calFare.setClickable(false);
                            }
                            else if (dist == 6) {
                                fare.setText(MessageFormat.format("{0}₹ " + distSix, fare.getText()));
                                calFare.setClickable(false);
                            }
                            else if (dist == 5) {
                                fare.setText(MessageFormat.format("{0}₹ " + distFive, fare.getText()));
                                calFare.setClickable(false);
                            }
                            else if(dist>1 && dist<=4) {
                                fare.setText(MessageFormat.format("{0}₹ " + minCharge, fare.getText()));
                                calFare.setClickable(false);
                            }
                            else if (dist == 0) {
                                fare.setText(MessageFormat.format("{0}₹ 0.00 ", fare.getText()));
                                Toast.makeText(getActivity(), "No Services Available!", Toast.LENGTH_SHORT).show();
                                calFare.setClickable(false);
                            }
                        }
                    }
                }
            }
        });
        return view;
    }
}