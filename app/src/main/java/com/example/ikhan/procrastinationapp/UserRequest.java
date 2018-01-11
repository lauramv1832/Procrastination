package com.example.ikhan.procrastinationapp;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ikhan on 1/9/2018.
 */

public class UserRequest extends StringRequest{
    private static final String User_Request_URL="https://comexampleikhanprocrastinationapp.000webhostapp.com/Apps.php";
    private Map<String, String> params;
    public UserRequest(int userID, String apps,Response.Listener<String> listener){
        super(Method.POST, User_Request_URL, listener, null);
        params=new HashMap<>();
        params.put("userID", userID+"");
        params.put("apps", apps);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

