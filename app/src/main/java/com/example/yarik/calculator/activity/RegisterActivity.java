package com.example.yarik.calculator.activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.yarik.calculator.R;
import com.example.yarik.calculator.internet.RegisterRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private String userFirstName, userLastName, userEmail, userPhone, userLogin, userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etFirstName = (EditText) findViewById(R.id.etFirstName);
        final EditText etLastName = (EditText) findViewById(R.id.etLastName);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etPhone = (EditText) findViewById(R.id.etPhone);
        final EditText etLogin = (EditText) findViewById(R.id.etLogin);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button btnRegister = (Button) findViewById(R.id.btnRegisterUser);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            userFirstName = etFirstName.getText().toString();
            userLastName = etLastName.getText().toString();
            userPhone = etPhone.getText().toString();
            userLogin = etLogin.getText().toString();
            userPassword = etPassword.getText().toString();

            if (isValidEmail(etEmail.getText().toString())){

                userEmail = etEmail.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                        @Override
                        public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");

                        if (success){
                            RegisterActivity.this.startActivity(new Intent(RegisterActivity.this, ProblemSolvingActivity.class));
                        }else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                            builder.setMessage("Register Failed")
                                .setNegativeButton("Retry",null)
                                .create()
                                .show();
                            }
                        } catch (JSONException e) {
                                e.printStackTrace();
                          }
                        }
                };

                    RegisterRequest registerRequest = new RegisterRequest(userFirstName, userLastName, userEmail, userPhone, userLogin, userPassword, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                    queue.add(registerRequest);
            }
            else {
                Toast.makeText(getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();
            }
            }
        });
    }

    public boolean isValidEmail(CharSequence target) {
        return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}



