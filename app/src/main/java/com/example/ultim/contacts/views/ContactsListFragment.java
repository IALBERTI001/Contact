package com.example.ultim.contacts.views;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ultim.contacts.R;
import com.example.ultim.contacts.db.DBAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsListFragment extends Fragment {
    int index = 1;


    private final String TAG = "Contact List Fragment:";

    public ContactsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contacts_list, container, false);
        Context context = (Context) getActivity();

        //--buttons used in the layout --
        Button previous = (Button) v.findViewById(R.id.b_previous);
        Button next = (Button) v.findViewById(R.id.b_next);
        Button delete = (Button) v.findViewById(R.id.b_delete);

        //--textviews used in the layout
        TextView name = (TextView) v.findViewById(R.id.txt_name);
        TextView email = (TextView) v.findViewById(R.id.txt_email);
        TextView phone = (TextView) v.findViewById(R.id.txt_phone);


        //autofill the first contact in the database
        //TODO: check the limit of the db??
        DBAdapter db = new DBAdapter(context);
        db.open();
        Cursor c = db.getContact(1);
        if(c.moveToFirst()) {


            //--retrieving the strings from the 3 columns of my database (1. name, 2. email, 3.phone number)
            String dbName = c.getString(1);
            String dbEmail = c.getString(2);
            String dbPhone = c.getString(3);

            //--setting the textviews to the information from the database
            name.setText(dbName);
            email.setText(dbEmail);
            phone.setText(dbPhone);

            //--send the index + 1 to prep for next button press.
            //--close db connection to be used again elsewhere
            db.close();
        }else{
            Toast.makeText(context, "No contact found", Toast.LENGTH_SHORT).show();

        }




            next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //TODO: set the next item on DB on the screen
                try {
                    DBAdapter db = new DBAdapter(context);

                    //open db to get transaction
                    db.open();
                    //gets data at the current index
                    index++;
                    Cursor c = db.getContact(index);
                    if(c.moveToFirst()){
                        //--retrieving the strings from the 3 columns of my database (1. name, 2. email, 3.phone number)
                        String dbName = c.getString(1);
                        String dbEmail = c.getString(2);
                        String dbPhone = c.getString(3);

                        //--setting the textviews to the information from the database
                        name.setText(dbName);
                        email.setText(dbEmail);
                        phone.setText(dbPhone);

                        //--send the index + 1 to prep for next button press.

                        //--close db connection to be used again elsewhere
                        db.close();

                    }else{
                        Toast.makeText(context, "You are at the last contact!", Toast.LENGTH_SHORT).show();

                    }


                }catch(SQLException e){
                    Log.e(TAG, "Error in getting next contact" );
                    e.printStackTrace();
                }



            }
        });

        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    DBAdapter db = new DBAdapter(context);

                    db.open();
                    db.deleteContact(index);
                    db.close();
                }catch(SQLException e){
                    Log.e(TAG, "onClick: Error in deleteing current index" );
                    e.printStackTrace();
                }

            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    DBAdapter db = new DBAdapter(context);
                    db.open();
                    index--;
                    Cursor c = db.getContact(index);
                    if(c.moveToFirst()) {

                        String dbName = c.getString(1);
                        String dbEmail = c.getString(2);
                        String dbPhone = c.getString(3);

                        name.setText(dbName);
                        email.setText(dbEmail);
                        phone.setText(dbPhone);
                    }else{
                        Toast.makeText(context, "You are at the first contact!", Toast.LENGTH_SHORT).show();
                    }


                    db.close();
                }catch(SQLException e){
                    Log.e(TAG, "onClick: Error getting the previous contact" );
                    e.printStackTrace();
                }


            }
        });


        // Inflate the layout for this fragment
        return v;
    }

}
