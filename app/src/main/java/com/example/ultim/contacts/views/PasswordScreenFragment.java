package com.example.ultim.contacts.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ultim.contacts.R;


public class PasswordScreenFragment extends Fragment {
    String username = "Admin";
    String password = "password";




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_password_screen, container, false);

        //Button for the onClick listener
        Button login = (Button) rootView.findViewById(R.id.button_login);

        //get data from the data fields
        final EditText myUsername = (EditText) rootView.findViewById(R.id.username);
        final EditText myPassword = (EditText) rootView.findViewById(R.id.password);

        //--on click listner for the login button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //EditText


                //getting the strings from the fields
                String inputUsername = myUsername.getText().toString();
                String inputPassword = myPassword.getText().toString();

                //compare
                if(username.equalsIgnoreCase(inputUsername)){

                    if(password.equalsIgnoreCase(inputPassword)){

                        //get the username for the second fragment
                        //bundle to save it to
                        Bundle bundle = new Bundle();
                        //add username to the bundle
                        bundle.putString( "username" , inputUsername);


                        //Inflate the dialer fragment if correct
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        //start transaction
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        //fragment used
                        ContactsListFragment dFragment = new ContactsListFragment();
                        //saving bundle to fragment
                        dFragment.setArguments(bundle);

                        //replace current fragment with this fragment
                        fragmentTransaction.replace(R.id.fragmentContainer, dFragment, "dialer_fragment_call" ).commit();




                    }else{
                        //TODO: add a dialog saying password is incorrect
                        Toast.makeText(getContext(), "Password entered is not Valid (should be password)", Toast.LENGTH_LONG).show();
                    }
                }else{
                    //TODO: add username not found
                    Toast.makeText(getContext(), "Username entered is not valid (should be: Admin)" , Toast.LENGTH_LONG).show();
                }



            }
        });


        // Inflate the layout for this fragment
        return rootView;
    }


}
