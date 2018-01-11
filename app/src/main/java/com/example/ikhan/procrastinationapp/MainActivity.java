package com.example.ikhan.procrastinationapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        final TextView etUsername=(TextView)findViewById(R.id.Username);
        final TextView etPassword=(TextView)findViewById(R.id.Password);
        final Button login=(Button)findViewById(R.id.Login);
        final Button signup=(Button)findViewById(R.id.SignUp);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, Register_Activity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username=etUsername.getText().toString();
                final String password=etPassword.getText().toString();
                Response.Listener<String>responseListener=new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            boolean success= jsonObject.getBoolean("success");
                            if (success){
                                String name=jsonObject.getString("name");
                                String apps=jsonObject.getString("apps");
                                String userID=jsonObject.getString("userID");
                                Intent intent=new Intent(MainActivity.this, UserActivity.class);
                                intent.putExtra("name",name);
                                intent.putExtra("username",username);
                                intent.putExtra("apps",apps);
                                intent.putExtra("userID", userID);
                                MainActivity.this.startActivity(intent);
                            }else{
                                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                                builder.setMessage("Incorrect Login Credentials")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                LoginRequest loginRequest=new LoginRequest(username, password, responseListener);
                RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
                requestQueue.add(loginRequest);


            }
        });
    }

}
