package com.poduri.manohar.instagramclone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.nio.file.WatchEvent;

public class SignUp extends AppCompatActivity {

    protected void OnCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }


   public void helloworldtapped(View view) {
     //   ParseObject boxer =new ParseObject("Boxer");
       // boxer.put("Punch_Speed",200);
        //boxer.saveInBackground(new SaveCallback() {
          //  @Override
            //public void done(ParseException e) {
              //  if(e==null){
                //    Toast.makeText(SignUp.this,"boxer object is saved successfully",Toast.LENGTH_LONG).show();
                //}
            //}
       // });


    ParseObject kickboxer = new ParseObject("kickboxer");
    kickboxer.put("punch_power",2000);
    kickboxer.put("name","john");
    kickboxer.put("punch_speed",3000);
    kickboxer.put("kickspeed",4000);
    kickboxer.put("kickpower",5000);

    kickboxer.saveInBackground(new SaveCallback() {
        @Override
        public void done(ParseException e) {
            if(e==null)
            {
                Toast.makeText(SignUp.this,"kickboxer saved sucessfully",Toast.LENGTH_SHORT).show();
            }
        }
    });
    }
}

