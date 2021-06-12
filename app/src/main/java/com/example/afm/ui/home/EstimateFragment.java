package com.example.afm.ui.home;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.afm.R;

import java.text.MessageFormat;

public class EstimateFragment extends Fragment {

     EditText enterSource,enterDestination;
     TextView fare;
     Button calFare;

    private EstimateViewModel mViewModel;

    public static EstimateFragment newInstance() {
        return new EstimateFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.estimate_fragment, container, false);
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
                for (int s=0;s<sources.length;s++) {
                    for (int d=0;d<destinations.length;d++) {
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
                        } else {
//                            Toast.makeText(getActivity(), "Source/Destination Not Within Our Database!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(EstimateViewModel.class);
        // TODO: Use the ViewModel
    }

}