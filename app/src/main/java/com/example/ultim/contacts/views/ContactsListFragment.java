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
    private int index = 0;
    private int previousIndex = 0;
    private int placeHolder = 0;





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
        DBAdapter db = new DBAdapter(getContext());
        db.open();
        Cursor c = db.getAllCOntacts();
        //--move to first contact
        c.moveToFirst();
        //--get the position
        index = c.getPosition();
        Toast.makeText(getContext(), "You are on contact: " + index, Toast.LENGTH_SHORT).show();
        name.setText(c.getString(1));
        email.setText(c.getString(2));
        phone.setText(c.getString(3));

        db.close();




            next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!c.isLast()) {
                    db.open();
                    c.moveToNext();
                    index = c.getPosition();
                    Toast.makeText(getContext(), "You are on contact: " + index, Toast.LENGTH_SHORT).show();
                    db.getContact(index);
                    name.setText(c.getString(1));
                    email.setText(c.getString(2));
                    phone.setText(c.getString(3));
                    db.close();
                }else{
                    Toast.makeText(getContext(), "You are at the last contact", Toast.LENGTH_SHORT).show();
                }


            }
        });

        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                db.open();
                db.deleteContact(index);
                c.moveToFirst();
                index = c.getPosition();
                Toast.makeText(getContext(), "Contact Deleted", Toast.LENGTH_SHORT).show();
                name.setText(c.getString(1));
                email.setText(c.getString(2));
                phone.setText(c.getString(3));
                db.close();

            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.open();
                if(!c.isFirst()) {
                    c.moveToPrevious();
                    index = c.getPosition();
                    Toast.makeText(getContext(), "You are on contact: " + index, Toast.LENGTH_SHORT).show();
                    db.getContact(index);
                    name.setText(c.getString(1));
                    email.setText(c.getString(2));
                    phone.setText(c.getString(3));
                    db.close();
                }else{
                    Toast.makeText(getContext(), "You are at first ID", Toast.LENGTH_SHORT).show();
                }




            }
        });


        // Inflate the layout for this fragment
        return v;
    }

    public void setTextField(Cursor c, TextView v, int pos){
        v.setText(c.getString(pos));

    }




}
