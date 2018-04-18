package com.example.ultim.contacts;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.Editable;
import android.view.View;
import android.widget.Toast;

import com.example.ultim.contacts.db.DBAdapter;
import com.example.ultim.contacts.views.DialerFragment;
import com.example.ultim.contacts.views.TextingFragment;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_SEND_SMS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        //--LOAD UP FIRST FRAGMENT--

        //Fragment manager support
        FragmentManager fragmentManager = getSupportFragmentManager();
        //Fragment Transaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //what am i adding
        DialerFragment dialer = new DialerFragment();
        //where am I adding it
        fragmentTransaction.replace(R.id.fragmentContainer, dialer, "password_fragment" );
        //Do it!
        fragmentTransaction.commit();

        //--used to get permission when sending and receiving a text
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, REQUEST_SEND_SMS);
        }



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

    public void sendText(View view){
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("7869183522", null, "Hello sms woo", null, null);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_SEND_SMS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {

            //--what to do on back pressed
            //Fragment manager support
            FragmentManager fragmentManager = getSupportFragmentManager();
            //Fragment Transaction
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //what am i adding
            DialerFragment dialer = new DialerFragment();
            //where am I adding it
            fragmentTransaction.replace(R.id.fragmentContainer, dialer, "password_fragment" );
            //Do it!
            fragmentTransaction.commit();
        } else {
            getFragmentManager().popBackStack();
        }

    }
}
