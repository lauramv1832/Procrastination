package com.example.ikhan.procrastinationapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

//import android.support.v7.app.AppCompactActivity;


public class UserActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_user);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");
        String apps=intent.getStringExtra("apps");
        final int userID=Integer.parseInt(intent.getStringExtra("userID"));
        String message = name + "'s Apps";
        TextView welcome = (TextView) findViewById(R.id.welcome);
        //welcome.setText(message);


        Button bStart = (Button) findViewById(R.id.bStart);
        bStart.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String apps=GenerateString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {
                                //START RUNNING APP

                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
                                builder.setMessage("Register Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                UserRequest registerRequest = new UserRequest(userID, apps, responseListener);
                RequestQueue queue = Volley.newRequestQueue(UserActivity.this);
                queue.add(registerRequest);
            }

        });

        SetViews(apps);
    }
    private void SetViews(String apps) {
        if (!apps.equals("")) {
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearlayout);
            int s=0;
            for (int i = 0; i < linearLayout.getChildCount(); i++) {
                View v = linearLayout.getChildAt(i);
                try {
                    for (int index = 0; index < ((ViewGroup) v).getChildCount(); ++index) {
                        View nextChild = ((ViewGroup) v).getChildAt(index);
                        if (nextChild instanceof Switch) {

                            Switch swch = (Switch) nextChild;
                            if (apps.charAt(s) == '1') {
                                swch.setChecked(true);

                            }
                            s += 1;
                        }
                    }
                }catch (Exception e){
                    continue;
                };


            }
        }
    }
    private String GenerateString(){
        String str="";
        LinearLayout linearLayout= (LinearLayout)findViewById(R.id.linearlayout);
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            View v = linearLayout.getChildAt(i);
            try {
                for (int index = 0; index < ((ViewGroup) v).getChildCount(); ++index) {
                    View nextChild = ((ViewGroup) v).getChildAt(index);
                    if (nextChild instanceof Switch) {
                        if (((Switch) nextChild).isChecked()) {
                            str = str.concat("1");
                        } else {
                            str = str.concat("0");
                        }
                    }
                }
            }catch (Exception e){
                continue;
            };


        }

        return str;
    }
}




