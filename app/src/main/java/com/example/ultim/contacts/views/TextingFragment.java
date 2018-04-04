package com.example.ultim.contacts.views;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ultim.contacts.R;


public class TextingFragment extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_texting, container, false);

        Button send = (Button) v.findViewById(R.id.button_send);
        Button bDefault = (Button) v.findViewById(R.id.button_default);
        Button email = (Button) v.findViewById(R.id.button_email);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage("5554", null, "is this working?", null, null);
            }
        });

        bDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //Stack overflow solution for API error
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) // At least KitKat
                {
                    String defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(getContext()); // Need to change the build to API 19

                    Intent sendIntent = new Intent(Intent.ACTION_SEND);
                    sendIntent.setType("text/plain");
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "text");

                    if (defaultSmsPackageName != null)// Can be null in case that there is no default, then the user would be able to choose
                    // any app that support this intent.
                    {
                        sendIntent.setPackage(defaultSmsPackageName);
                    }
                    startActivity(sendIntent);

                }
                else // For early versions, do what worked for you before.
                {
                    Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
                    smsIntent.setType("vnd.android-dir/mms-sms");
                    smsIntent.putExtra("address","phoneNumber");
                    smsIntent.putExtra("sms_body","message");
                    startActivity(smsIntent);
                }
            }

        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] to = {"ndelessy@mdc.edu", "nelly.delessy@gmail.com"};
                String[] cc = {"nelly_delessy@yahoo.fr"};
                sendEmail(to, cc, "Hello", "Hello from the app!");
            }
        });







        // Inflate the layout for this fragment
        return v;
    }

    /** used to send emails
     *
     * @param emailAddresses
     * @param carbonCopies
     * @param subject
     * @param message
     */
    private void sendEmail (String[] emailAddresses, String[] carbonCopies, String subject, String message) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        String[] to = emailAddresses;
        String[] cc = carbonCopies;
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_CC, cc);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent, "Email"));
    }
}
