package com.poduri.manohar.instagramclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtLoginEmail, edtLoginPassword;
    private Button btnLoginActivity, btnSignUpLoginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Log In");


        edtLoginEmail = findViewById(R.id.edtLoginEmail);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);
        edtLoginPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    onClick(btnLoginActivity);
                }
                return false;
            }
        });
        btnLoginActivity = findViewById(R.id.btnLoginActivity);
        btnSignUpLoginActivity = findViewById(R.id.btnSignUpLoginActivity);

        btnLoginActivity.setOnClickListener(this);
        btnSignUpLoginActivity.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();


        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnLoginActivity:



                if (edtLoginEmail.getText().toString().equals("") || edtLoginPassword.getText().toString().equals("")) {
                    FancyToast.makeText(this,"Email, Password is required!",FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();


                } else {

                    final ParseUser appUser = new ParseUser();
                    appUser.setEmail(edtLoginEmail.getText().toString());
                    appUser.setPassword(edtLoginPassword.getText().toString());

                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Signing Up " + edtLoginEmail.getText().toString());
                    progressDialog.show();

                    ParseUser.logInInBackground(edtLoginEmail.getText().toString(), edtLoginPassword.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {

                            if (user != null && e == null) {
                                FancyToast.makeText(LoginActivity.this, user.getUsername() + " is logged in success", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                                transitionToSocialMediaActivity();

                            } else {
                                FancyToast.makeText(LoginActivity.this,e.getMessage() + "there is an error", FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                            }
                        }
                    });
                }
                break;


            case R.id.btnSignUpLoginActivity:

                Intent intent = new Intent(LoginActivity.this,SignUp.class);
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
        Intent intent = new Intent(LoginActivity.this, SocialMediaActivity.class);
        startActivity(intent);
        finish();
    }

}
