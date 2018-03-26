package com.example.ultim.contacts.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ultim.contacts.R;


public class DialerFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialer, container, false);

        //getting data from the password screen fragment then displaying it in a toast
        Bundle bundle = getArguments();
        if(bundle != null){
            String user = bundle.getString("username");
            Toast.makeText(getContext(), "Welcome " + user + ", this is the username passed from password fragment", Toast.LENGTH_LONG).show();
        }

        TextView dialer = (TextView) v.findViewById(R.id.dialer_screen);
        Button contacts = (Button) v.findViewById(R.id.button_contacts_list);

        Button add = (Button) v.findViewById(R.id.button_add);
        Button zero = (Button) v.findViewById(R.id.button10);
        Button one = (Button) v.findViewById(R.id.button1);
        Button two = (Button) v.findViewById(R.id.button2);
        Button three = (Button) v.findViewById(R.id.button3);
        Button four = (Button) v.findViewById(R.id.button4);
        Button five = (Button) v.findViewById(R.id.button5);
        Button six = (Button) v.findViewById(R.id.button6);
        Button seven = (Button) v.findViewById(R.id.button7);
        Button eight = (Button) v.findViewById(R.id.button8);
        Button nine = (Button) v.findViewById(R.id.button9);

        /**
         * LISTENERS FOR THE DIALER BUTTONS
         */
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cString = (String) dialer.getText();
                dialer.setText(cString + "0");

            }
        });

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cString = (String) dialer.getText();
                dialer.setText(cString + "1");

            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cString = (String) dialer.getText();
                dialer.setText(cString + "2");

            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cString = (String) dialer.getText();
                dialer.setText(cString + "3");;

            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cString = (String) dialer.getText();
                dialer.setText(cString + "4");

            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cString = (String) dialer.getText();
                dialer.setText(cString + "5");

            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cString = (String) dialer.getText();
                dialer.setText(cString + "6");

            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cString = (String) dialer.getText();
                dialer.setText(cString + "7");

            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cString = (String) dialer.getText();
                dialer.setText(cString + "8");

            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cString = (String) dialer.getText();
                dialer.setText(cString + "9");

            }
        });

        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //--GO TO PASSWORD FRAGMENT --
                //--transaction manager
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                //start transaction
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                //fragment used
                PasswordScreenFragment dFragment = new PasswordScreenFragment();

                //replace current fragment with this fragment
                fragmentTransaction.replace(R.id.fragmentContainer, dFragment, "Add_fragment_call").commit();
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //--transaction manager
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                //start transaction
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                //fragment used
                AddContactFragment dFragment = new AddContactFragment();
                //replace current fragment with this fragment
                fragmentTransaction.replace(R.id.fragmentContainer, dFragment, "Add_fragment_call").commit();
            }

        });



        // Inflate the layout for this fragment
        return v;



    }
}
