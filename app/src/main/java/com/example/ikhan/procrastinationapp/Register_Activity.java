package com.example.ikhan.procrastinationapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Register_Activity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register_);
        Button register=(Button)findViewById(R.id.bCreateAccount);
        final EditText etName=(EditText)findViewById(R.id.etName);
        final EditText etUsername=(EditText)findViewById(R.id.etUsername);
        final EditText etPassword=(EditText)findViewById(R.id.etPassword);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                final String name = etName.getText().toString();
                if (password.trim().length() != 0 && name.trim().length() != 0 && username.trim().length()!=0 ) {
                    Response.Listener<String> responseListener = new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                boolean success = jsonObject.getBoolean("success");
                                if (success) {
                                    Intent intent = new Intent(Register_Activity.this, MainActivity.class);
                                    Register_Activity.this.startActivity(intent);

                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Register_Activity.this);
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

                    RegisterRequest registerRequest = new RegisterRequest(name, username, password, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(Register_Activity.this);
                    queue.add(registerRequest);
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(Register_Activity.this);
                    builder.setMessage("Field can't be blank")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();
                }
            }
        });


    }
}
