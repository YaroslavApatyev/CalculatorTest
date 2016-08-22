package com.example.yarik.calculator.activity;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.yarik.calculator.R;
import com.example.yarik.calculator.internet.ForgottenRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class ForgottenPasswordActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etEmailValidate;
    private  Button btnForgottenPassword;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotten_password);

        etEmailValidate = (EditText)findViewById(R.id.etForgottenEmail);
        btnForgottenPassword = (Button)findViewById(R.id.btnForgottenPassword);

        btnForgottenPassword.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnForgottenPassword:
                email = etEmailValidate.getEditableText().toString().trim();

                if (isValidEmail(email)){

                    Response.Listener<String> responseListener = new Response.Listener<String>(){
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");

                                    if (success){
                                        Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
                                    }else {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(ForgottenPasswordActivity.this);
                                        builder.setMessage("Forgotten Failed").setNegativeButton("Retry",null).create().show();
                                    }
                                } catch (JSONException e) {
                                e.printStackTrace();
                                }
                        }
                    };

                    ForgottenRequest forgottenRequest = new ForgottenRequest(email, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(ForgottenPasswordActivity.this);
                    queue.add(forgottenRequest);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public final static boolean isValidEmail(CharSequence target) {

        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
