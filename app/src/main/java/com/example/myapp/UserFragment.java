package com.example.myapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.telephony.RadioAccessSpecifier;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    EditText username;
    EditText email;
    EditText phone;
    EditText dob;
    RadioButton sex,male,female;
    Button submit;
    DBHelper DB;

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
        View view =  inflater.inflate(R.layout.fragment_user, container, false);
        DB = new DBHelper(getActivity());
        username = view.findViewById(R.id.editText);
        email = view.findViewById(R.id.editText4);
        phone = view.findViewById(R.id.editText2);
        dob = view.findViewById(R.id.editText5);
        submit = view.findViewById(R.id.button3);
        male = view.findViewById(R.id.radioButton2);
        female = view.findViewById(R.id.radioButton);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().length() > 1 && email.getText().toString().length() > 1 && phone.getText().toString().length() == 10 && dob.getText().toString().length() == 10) {
                    Toast toast = Toast.makeText(getActivity(), "Have A Safe Journey " + username.getText().toString() + ", Thank You!", Toast.LENGTH_SHORT);
                    toast.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, 1500);
                    String gender = null;
                    if (male.isChecked()) {
                        gender="Male";
                    }else if(female.isChecked()) {
                        gender = "Female";
                    }
                        Bundle bundle = new Bundle();
                        bundle.putString("user",username.getText().toString());
                        bundle.putString("mail",email.getText().toString());
                        bundle.putString("mob",phone.getText().toString());
                        bundle.putString("date",dob.getText().toString());
                        bundle.putString("gender",gender);
                    Boolean checkuser = DB.checkUsername(email.getText().toString());
                    if(!checkuser) {
                        Boolean insert = DB.insertData(username.getText().toString(), email.getText().toString(), phone.getText().toString(), dob.getText().toString());
                        if (insert) {
                            Toast.makeText(getActivity(), "Registered Successfully!", Toast.LENGTH_SHORT).show();
                            TripFragment fragment = new TripFragment();
                            fragment.setArguments(bundle);
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.frameLayout2, fragment);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                        } else {
                            Toast.makeText(getActivity(), "Registration Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(getActivity(), "User Already Exists, Kindly Sign In!", Toast.LENGTH_SHORT).show();
                    }
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
}