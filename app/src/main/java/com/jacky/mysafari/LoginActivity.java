 package com.jacky.mysafari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

 public class LoginActivity extends AppCompatActivity {

     private Button mLoginButton;
     private EditText mDetails;
     private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mDetails = (EditText) findViewById(R.id.name);
        mLoginButton = (Button) findViewById(R.id.loginButton);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mDetails.getText().toString();
                Toast.makeText(LoginActivity.this, "Details Captured!!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
    }



//     public void btn_signup(View view) {
//
//        startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
//     }
//
//     public void btn_main(View view) {
//         startActivity(new Intent(getApplicationContext(), MainActivity.class));
//     }
 }
