package com.example.yarik.calculator.internet;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://yaroslavapatyev.zzz.com.ua/Register.php";
    private Map<String,String> params;

    public RegisterRequest (String firstName, String lastName, String email, String Phone, String login, String password, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("firstname", firstName);
        params.put("lastname", lastName);
        params.put("email", email);
        params.put("phone", Phone);
        params.put("login", login);
        params.put("password", password);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
