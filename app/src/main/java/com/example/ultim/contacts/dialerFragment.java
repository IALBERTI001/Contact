package com.example.ultim.contacts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class dialerFragment extends Fragment {
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


        // Inflate the layout for this fragment
        return v;
    }

}
