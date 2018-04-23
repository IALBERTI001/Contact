package com.example.ultim.contacts.views;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.ultim.contacts.R;
import com.example.ultim.contacts.db.DBAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddContactFragment extends Fragment {
    Context myContext;
    private final String TAG = "AddContact";

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
        EditText phoneN = (EditText) v.findViewById(R.id.et_phone);

        Bundle bundle = this.getArguments();
        Log.i(TAG, "onCreateView: Getting arguments from bundle");

        if(bundle != null){
            Log.e(TAG, "onCreateView: Getting the bundle ", null );
            String number = bundle.getString("phone");
            phoneN.setText(number);
        }

        addToDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getting the main activity to call on the add to database method

                //--Context used to communicate
                myContext = (Context) getActivity();

                String iName = name.getText().toString();
                String iEmail = email.getText().toString();
                String iPhone = phoneN.getText().toString();


                DBAdapter db = new DBAdapter(myContext);
                db.open();
                db.insertContact(iName, iEmail, iPhone);
                db.close();


                Log.i(TAG, "onClick: " + db.toString());
                Log.i(TAG, "onClick: " +  "name= " + iName + " email= " + iEmail + " phone= " + iPhone);

                //go back to main screen
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                DialerFragment landingScreenFragment = new DialerFragment();
                fragmentTransaction.replace(R.id.fragmentContainer, landingScreenFragment, "back_to_landing").commit();

            }
        });




        return v;

    }

}
