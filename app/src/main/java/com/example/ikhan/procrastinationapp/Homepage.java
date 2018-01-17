package com.example.ikhan.procrastinationapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        final String username = intent.getStringExtra("username");
        final String apps=intent.getStringExtra("apps");
        final int userID = Integer.parseInt(intent.getStringExtra("userID"));

        Button bSettings = (Button) findViewById(R.id.bSettings);
        bSettings.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, UserActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("username",username);
                intent.putExtra("apps",apps);
                intent.putExtra("userID", userID);
                Homepage.this.startActivity(intent);
            }
        });
    }

}
