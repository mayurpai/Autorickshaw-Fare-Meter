package com.example.afm.ui.home;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.os.Handler;

import com.example.afm.R;

public class DetailsFragment extends Fragment {

    private DetailsViewModel mViewModel;
    EditText username,email,phone,dob;
    RadioButton male,female;
    Button submit;

    public static DetailsFragment newInstance() {
        return new DetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.details_fragment, container, false);
        username = view.findViewById(R.id.editText);
        email = view.findViewById(R.id.editText4);
        phone = view.findViewById(R.id.editText2);
        dob = view.findViewById(R.id.editText5);
        male = view.findViewById(R.id.radioButton2);
        female = view.findViewById(R.id.radioButton);
        submit = view.findViewById(R.id.button3);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().length() > 1 && email.getText().toString().length() > 1 && phone.getText().toString().length() == 10 && dob.getText().toString().length() == 10 && (male.isChecked() || female.isChecked())) {
                Toast toast = Toast.makeText(getActivity(), "Have A Safe Journey " + username.getText().toString() + ", Thank You!", Toast.LENGTH_SHORT);
                            toast.show();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    toast.cancel();
                                }
                            }, 1500);
//                Fragment fragment = new TripFragment();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.frameLayout2, fragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
                }
                else {
                    Toast toast = Toast.makeText(getActivity(), "Kindly, Enter The Details Wisely", Toast.LENGTH_SHORT);
                    toast.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, 1000);
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(DetailsViewModel.class);
        // TODO: Use the ViewModel
    }

}