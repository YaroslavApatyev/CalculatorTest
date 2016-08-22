package com.example.yarik.calculator.internet;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ForgottenRequest extends StringRequest {

    private static final  String LOGIN_REQUEST_URL = "http://yaroslavapatyev.zzz.com.ua/ForgotPassword.php";
    private Map<String,String> params;

    public ForgottenRequest(String email, Response.Listener<String> listener){
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("email", email);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
