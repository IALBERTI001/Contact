package com.example.ultim.contacts;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class SignInFragment extends DialogFragment {



    public SignInFragment() {
        // Required empty public constructor
    }

    public static SignInFragment newInstance() {
        SignInFragment fragment = new SignInFragment();
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder =  new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_sigin_in, null);

        final EditText user = (EditText) view.findViewById(R.id.username);
        final EditText pw = (EditText) view.findViewById(R.id.password);

        // set dialog icon
        builder.setIcon(android.R.drawable.btn_dialog)
                // set Dialog Title
                .setTitle("Log In")
                // Set Dialog Message
                .setView(view)
                // positive button
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String inUser = user.getText().toString();
                        String inPw = pw.getText().toString();
                        String username = "admin";
                        String password = "1234";

                        if(inUser.equalsIgnoreCase(username)){
                            if(inPw.equalsIgnoreCase(password)){
                                //password and username accepted
                                Toast.makeText(getContext(), "Log in successful", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();


                            }else{
                               //TODO: add invalid password
                                Toast.makeText(getContext(), "Password is 1234.. not " + inPw, Toast.LENGTH_SHORT ).show();
                            }
                        }else{
                            //TODO: add invalid username
                            Toast.makeText(getContext(), "username is admin.. not" + inUser, Toast.LENGTH_SHORT ).show();
                        }


                    }
                })
                // negative button
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }



}