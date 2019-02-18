package com.poduri.manohar.instagramclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText edtEmail, edtUsername, edtPassword;
    private Button btnSignUp, btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setTitle("Sign Up");

        edtEmail = findViewById(R.id.edtEmail);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);

        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    onClick(btnSignUp);
                }
                return false;
            }
        });
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnLogin);

        btnSignUp.setOnClickListener(this);
        btnLogin.setOnClickListener(this);


        if (ParseUser.getCurrentUser() != null) {
            // ParseUser.getCurrentUser().logOut();
            transitionToSocialMediaActivity();
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnSignUp:


                if (edtEmail.getText().toString().equals("") || edtUsername.getText().toString().equals("") || edtPassword.getText().toString().equals("")) {
                    FancyToast.makeText(this, "Email, Username, Password is required!", FancyToast.LENGTH_LONG, FancyToast.INFO, true).show();

                    transitionToSocialMediaActivity();


                } else {

                    final ParseUser appUser = new ParseUser();
                    appUser.setEmail(edtEmail.getText().toString());
                    appUser.setUsername(edtUsername.getText().toString());
                    appUser.setPassword(edtPassword.getText().toString());

                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Signing Up " + edtUsername.getText().toString());
                    progressDialog.show();


                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(SignUp.this, appUser.getUsername() + " is signed up success", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                                transitionToSocialMediaActivity();

                            } else {
                                FancyToast.makeText(SignUp.this, "This is an error:" + e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                            }
                            progressDialog.dismiss();
                        }
                    });

                }
                break;

            case R.id.btnLogin:


                Intent intent = new Intent(SignUp.this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void rootLayoutTapped(View view) {

        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void transitionToSocialMediaActivity() {
        Intent intent = new Intent(SignUp.this, SocialMediaActivity.class);
        startActivity(intent);
        finish();
    }
}