package com.jacky.mysafari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mnameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mnameView = (TextView) findViewById(R.id.userView);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        mnameView.setText("You have succefully logged in: " + name);
    }
}
