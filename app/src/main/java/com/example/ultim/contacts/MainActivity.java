package com.example.ultim.contacts;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SignInFragment signInFrag = SignInFragment.newInstance();
        signInFrag.show(getSupportFragmentManager(), "sign_in");



        //Fragment manager support
        FragmentManager fragmentManager = getSupportFragmentManager();
        //Fragment Transaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //what am i adding
        dialerFragment dialer = new dialerFragment();
        //where am I adding it
        fragmentTransaction.add(R.id.fragmentContainer, dialer, "password_fragment" );
        //Do it!
        fragmentTransaction.commit();



    }


}
