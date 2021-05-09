package com.example.emailintentmuliadisiregar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //deklare views
    EditText mRecipientEt, mSubject, mMessageEt, mSendEmailBtn;
    Button mSendEmailBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing views with activity_main.xml
        mRecipientEt = findViewById(R.id.recipientEt);
        mSubject = findViewById(R.id.SubjectEt);
        mMessageEt = findViewById(R.id.messageEt);
        mSendEmailBtn = findViewById(R.id.sendEmailBtn);

        //button click to get input and call sendEmail method
        mSendEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get input from EditTexts and save in variables
                String recipient = mRecipientEt.getText().toString().trim(); //trim will remove space before and after the text
                String subject = mSubject.getText().toString().trim();
                String message = mMessageEt.getText().toString().trim();

                //method call for email intent with these inputs as parameters
                SendEmailBtn (recipient, subject, message);

            }
        });

    }

    private void SendEmailBtn(String recipient, String subject, String message) {
        /*ACTIONT_SEND action to launch an email client installed on your Android device.*/
        Intent mEmailIntent = new Intent(Intent.ACTION_SEND);
        /*To send an email you need to specipy mailto: as URI sing setData() method
         and data type will be to text/plain using setType() method */
        mEmailIntent.setData(Uri.parse("mailto:"));
        mEmailIntent.setType("text/plain");
        // put recipient email in intent
        mEmailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {recipient});
        //put subject of email
        mEmailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        //put message of email in intent
        mEmailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try  {
            //no error, so start intent
            startActivity(Intent.createChooser(mEmailIntent, "Choose an Email Client"));


        }
        catch (Exception e) {


            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT) .show();
        }
    }
}