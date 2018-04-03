package com.example.ultim.contacts;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.widget.Toast;

import com.example.ultim.contacts.db.DBAdapter;
import com.example.ultim.contacts.views.DialerFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //--
        super.onCreate(savedInstanceState);

        //--
        setContentView(R.layout.activity_main);

        //--


        //Fragment manager support
        FragmentManager fragmentManager = getSupportFragmentManager();
        //Fragment Transaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //what am i adding
        DialerFragment dialer = new DialerFragment();
        //where am I adding it
        fragmentTransaction.add(R.id.fragmentContainer, dialer, "password_fragment" );
        //Do it!
        fragmentTransaction.commit();



    }

    public void addToDataBase(String name, String email, String phone){
        DBAdapter db = new DBAdapter(this);

        db.open();
        db.insertContact(name, email, phone);
        db.close();
        Toast.makeText(this, "Entry added to database", Toast.LENGTH_SHORT);

    }


    public static void addToDataBase(Editable text, Editable text1, Editable text2) {
    }
}
