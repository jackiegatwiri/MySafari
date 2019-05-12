 package com.jacky.mysafari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

 public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

     public void btn_signup(View view) {

        startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
     }

     public void btn_main(View view) {
         startActivity(new Intent(getApplicationContext(), MainActivity.class));
     }
 }
