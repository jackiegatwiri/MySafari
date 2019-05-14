package com.jacky.mysafari;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.name) TextView mname;
    @BindView(R.id.email) TextView memail;
    @BindView(R.id.password) TextView mpassword;
    @BindView(R.id.confirmPassword) TextView mconfirmPassword;
    @BindView(R.id.signup) Button msignup;
    @BindView(R.id.login) TextView mlogin;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        msignup.setOnClickListener(this);
        mlogin.setOnClickListener(this);
    }
    public boolean validation(){
        boolean valid = false;

        if(mname.getText().toString().isEmpty()){
            mname.setError("Invalid name");
        }
        else if(memail.getText().toString().isEmpty() || !(Patterns.EMAIL_ADDRESS.matcher(memail.getText().toString()).matches())){
            memail.setError("Invalid email address");
        }
        else if(mpassword.getText().toString().isEmpty() || mpassword.getText().toString().length() < 8 ){   //  !(isValidPassword(mpassword.getText().toString().trim()))  ){
            mpassword.setError("Use at least one uppercase letter, lowercase letter, number, special characters and");
        }
        else if(!mconfirmPassword.getText().toString().equals(mpassword.getText().toString())){
            mconfirmPassword.setError("Password does not match");
        }
        else
            {
            valid = true;

        }
        return valid;
    }

//    public static boolean isValidPassword(final String password) { //password matcher
//
//        Pattern pattern;
//        Matcher matcher;
//        final String PASSWORD_PATTERN = "/^(?=.*\\d)(?=.*[A-Z])([@$%&#])[0-9a-zA-Z]{4,}$/";
//        pattern = Pattern.compile(PASSWORD_PATTERN);
//        matcher = pattern.matcher(password);
//
//        return matcher.matches();
//
//    }

    @Override
    public void onClick(View v) {
        if (v == msignup){
            if (validation()) {
                Toast.makeText(this, "Sign Up Successfully", Toast.LENGTH_SHORT).show();
            }
        }
        if (v == mlogin){
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
        }

    }

}


