package com.example.ultim.contacts.views;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ultim.contacts.R;
import com.example.ultim.contacts.db.DBAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsListFragment extends Fragment {
    int index = 1;

    public ContactsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contacts_list, container, false);

        Button previous = (Button) v.findViewById(R.id.b_previous);
        Button next = (Button) v.findViewById(R.id.b_next);
        Button delete = (Button) v.findViewById(R.id.b_delete);

        TextView name = (TextView) v.findViewById(R.id.txt_name);
        TextView email = (TextView) v.findViewById(R.id.txt_email);
        TextView phone = (TextView) v.findViewById(R.id.txt_phone);

        Context context = (Context) getActivity();


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //TODO: set the next item on DB on the screen
                DBAdapter db = new DBAdapter(context);
                db.open();

                Cursor c = db.getContact(index);

                String dbName = c.getString(1);
                String dbEmail = c.getString(2);
                String dbPhone = c.getString(3);

                name.setText(dbName);
                email.setText(dbEmail);
                phone.setText(dbPhone);
                index++;

                db.close();



            }
        });

        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DBAdapter db = new DBAdapter(context);
                db.open();
                db.deleteContact(index);
                index--;
                db.close();

            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBAdapter db = new DBAdapter(context);
                db.open();
                index--;
                Cursor c = db.getContact(index);

                String dbName = c.getString(1);
                String dbEmail = c.getString(2);
                String dbPhone = c.getString(3);

                name.setText(dbName);
                email.setText(dbEmail);
                phone.setText(dbPhone);


                db.close();


            }
        });






        // Inflate the layout for this fragment
        return v;
    }

}
