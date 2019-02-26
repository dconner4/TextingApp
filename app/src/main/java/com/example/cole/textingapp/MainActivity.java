package com.example.cole.textingapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    Button buttonSend;
    EditText textPhone, textMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED)
        {
            //Checking if app has permission to read the phone state
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.SEND_SMS))
            {
                //If required to ask for permission
                //Ask for permission
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.SEND_SMS}, 1);
            }
            else
            {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.SEND_SMS}, 1);
            }
        }

        buttonSend = (Button) findViewById(R.id.buttonSend);
        textMessage = (EditText)findViewById(R.id.txtMsg);
        textPhone = (EditText) findViewById(R.id.txtPhoneNum);

        buttonSend.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String number = textPhone.getText().toString();
                String message = textMessage.getText().toString();

                try
                {
                    SmsManager smsManager = SmsManager.getDefault();

                    smsManager.sendTextMessage(number, null, message, null, null);
                    Toast.makeText(MainActivity.this, "Message Sent", Toast.LENGTH_SHORT).show();

                }
                catch (Exception ex)
                {
                    Toast.makeText(MainActivity.this, "Message Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        switch(requestCode)
        {
            case 1:
            {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    if(ContextCompat.checkSelfPermission(MainActivity.this,
                            Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)
                    {
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(this, "No Permission Granted", Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
            }
        }
    }
}
