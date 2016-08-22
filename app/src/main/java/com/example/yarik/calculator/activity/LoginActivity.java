package com.example.yarik.calculator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.yarik.calculator.R;
import com.example.yarik.calculator.internet.LoginRequest;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private String login, password;
    private CallbackManager callbackManager;
    private SignInButton signInButtonGoogle;
    private Button btnSignOutGoogle;
    private GoogleApiClient mGoogleApiClient;
    private int RC_SIGN_IN = 100;
    private TwitterLoginButton twitterLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);

        final EditText etLoginActivity = (EditText) findViewById(R.id.etLoginActivityLogin);
        final EditText etPasswordActivity = (EditText) findViewById(R.id.etLoginActivityPassword);
        final TextView tvForgottenPassword = (TextView) findViewById(R.id.tvForgottenPassword);
        final TextView tvRegistration = (TextView) findViewById(R.id.tvRegistration);
        final Button btnLogin = (Button) findViewById(R.id.btnSend);

        LoginButton btnFacebook = (LoginButton) findViewById(R.id.login_button);
        signInButtonGoogle = (SignInButton) findViewById(R.id.btn_sign_in_google);
        btnSignOutGoogle = (Button) findViewById(R.id.btn_sign_out);
        twitterLoginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);

        callbackManager = CallbackManager.Factory.create();
        btnFacebook.setReadPermissions("user_friends");
        btnFacebook.registerCallback(callbackManager,mCallback);

        GoogleSignInOptions gso = new GoogleSignInOptions.
                Builder(GoogleSignInOptions.
                DEFAULT_SIGN_IN).
                requestEmail().
                build();
        mGoogleApiClient = new GoogleApiClient.
                Builder(this).
                enableAutoManage(this, this).
                addApi(Auth.GOOGLE_SIGN_IN_API, gso).
                build();
        signInButtonGoogle.setSize(SignInButton.SIZE_WIDE);
        signInButtonGoogle.setScopes(gso.getScopeArray());

        twitterLoginButton.setCallback(new Callback<TwitterSession>() {

            @Override
            public void success(Result<TwitterSession> result) {
                TwitterSession session = result.data;
                String msg = "@" + session.getUserName() + " logged in! (#" + session.getUserId() + ")";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                LoginActivity.this.startActivity(new Intent(LoginActivity.this, ProblemSolvingActivity.class));
            }

            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });

        tvForgottenPassword.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick (View view){
                startActivity(new Intent(LoginActivity.this, ForgottenPasswordActivity.class));
            }
        });

        tvRegistration.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick (View view){
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        signInButtonGoogle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick (View view){
                signInGoogle();
            }
        });

        btnSignOutGoogle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                signOut();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick (View view){
                login = etLoginActivity.getText().toString();
                password = etPasswordActivity.getText().toString();

            Response.Listener<String> responseListener = new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");

                        if (success) {
                            LoginActivity.this.startActivity(new Intent(LoginActivity.this, ProblemSolvingActivity.class));
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setMessage("Login Failed")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                            }

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                }
            };
                LoginRequest loginRequest = new LoginRequest(login, password, responseListener);
                RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
                requestQueue.add(loginRequest);
            }
        });
}

    private FacebookCallback<LoginResult> mCallback = new FacebookCallback<LoginResult>() {

        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();
            if (profile != null){
                LoginActivity.this.startActivity(new Intent(LoginActivity.this, ProblemSolvingActivity.class));
            }
        }

        @Override
        public void onCancel() {
        }

        @Override
        public void onError(FacebookException error) {
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResultGoogle(result);
            if (result != null){
                LoginActivity.this.startActivity(new Intent(LoginActivity.this, ProblemSolvingActivity.class));
            }
        }else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
            twitterLoginButton.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void signInGoogle() {

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        updateUIGoogleBatton(false);
                    }
                });
    }

    private void handleSignInResultGoogle(GoogleSignInResult result) {

        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
            String email = acct.getEmail();
            updateUIGoogleBatton(true);

        } else {
            Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
            updateUIGoogleBatton(false);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {}

    private void updateUIGoogleBatton(boolean isSignedIn) {
        if (isSignedIn) {
            signInButtonGoogle.setVisibility(View.GONE);
            btnSignOutGoogle.setVisibility(View.VISIBLE);
        } else {
            signInButtonGoogle.setVisibility(View.VISIBLE);
            btnSignOutGoogle.setVisibility(View.GONE);
        }
    }
}


