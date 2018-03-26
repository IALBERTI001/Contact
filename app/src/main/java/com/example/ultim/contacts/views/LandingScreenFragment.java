package com.example.ultim.contacts.views;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ultim.contacts.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LandingScreenFragment extends Fragment {
    private Button contacts;
    private Button back;

    public LandingScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_landing_screen, container, false);
        // Buttons on landing screen --
        contacts = (Button) v.findViewById(R.id.b_contacts);
        back = (Button) v.findViewById(R.id.b_dialer);

        // On Click Listeners --
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //--OPEN THE DIALER FRAGMENT
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                DialerFragment dialerFrag = new DialerFragment();
                fragmentTransaction.replace(R.id.fragmentContainer, dialerFrag, "getting_dialer" ).commit();
            }
        });

        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //--OPEN THE PASSWORD SCREEN FRAGMENT
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                PasswordScreenFragment pw = new PasswordScreenFragment();
                fragmentTransaction.replace(R.id.fragmentContainer, pw, "--Password_Fragment").commit();
            }
        });

        return v;
    }

}
