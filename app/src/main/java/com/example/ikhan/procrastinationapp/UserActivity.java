package com.example.ikhan.procrastinationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        String username=intent.getStringExtra("username");
        //String message="Hello "+name;
        //TextView welcome=(TextView)findViewById(R.id.welcome);
        //welcome.setText(message);
    }
}
