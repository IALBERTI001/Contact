package com.example.ultim.contacts.views;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.ultim.contacts.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddContactFragment extends Fragment {


    public AddContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_contact, container, false);
        // Inflate the layout for this fragment

        Button addToDB = (Button) v.findViewById(R.id.button_database);
        EditText name = (EditText) v.findViewById(R.id.et_name);
        EditText email = (EditText) v.findViewById(R.id.et_email);
        EditText phone = (EditText) v.findViewById(R.id.et_phone);

        addToDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getting the main activity to call on the add to database method
                //--TODO: Find a way to send information to the database

            }
        });




        return v;

    }

}
