package com.jacky.mysafari;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;

import android.content.Intent;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.email)
    TextView memail;
    @BindView(R.id.password)
    TextView mpassword;
    @BindView(R.id.login)
    Button mlogin;
    @BindView(R.id.signup)
    TextView msignup;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mlogin.setOnClickListener(this);
        msignup.setOnClickListener(this);
    }

    public boolean validation() {
        boolean valid = false;

        if (memail.getText().toString().isEmpty() || !(Patterns.EMAIL_ADDRESS.matcher(memail.getText().toString()).matches())) {
            memail.setError("Invalid email address");
        } else if (mpassword.getText().toString().isEmpty() || mpassword.getText().toString().length() < 8) {   //  !(isValidPassword(mpassword.getText().toString().trim()))  ){
            mpassword.setError("Use at least one uppercase letter, lowercase letter, number, special characters and");
        } else {
            valid = true;

        }
        return valid;
    }

    @Override
    public void onClick(View v) {

        if (v == mlogin) {
            if (validation()) {
                String emails = memail.getText().toString();
                Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("emails", emails);
                startActivity(intent);

            }
        }
        if (v == msignup) {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        }


    }
}

