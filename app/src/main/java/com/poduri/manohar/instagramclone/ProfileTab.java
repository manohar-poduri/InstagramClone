package com.poduri.manohar.instagramclone;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {

    EditText edtProfileName,edtProfileBio,edtProfileProfession,edtProfileHobbies,edtProfileSport;
    Button btnUpdateInfo;


    public ProfileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);



       edtProfileName = view.findViewById(R.id.edtProfileName);
       edtProfileBio = view.findViewById(R.id.edtProfileBio);
       edtProfileProfession = view.findViewById(R.id.edtProfileProfession);
       edtProfileHobbies = view.findViewById(R.id.edtProfileHobbies);
       edtProfileSport = view.findViewById(R.id.edtProfileSport);


       btnUpdateInfo = view.findViewById(R.id.btnUpdateInfo);

        final ParseUser parseUser = ParseUser.getCurrentUser();

       if (parseUser.get("ProfileName") == null) {
           edtProfileName.setText("");
       } else {
           edtProfileName.setText(parseUser.get("ProfileName") + "");
       }
       if (parseUser.get("ProfileBio") == null) {
           edtProfileBio.setText("");
       } else {
           edtProfileBio.setText(parseUser.get("ProfileBio") + "");
       } if (parseUser.get("ProfileProfession") == null){
            edtProfileProfession.setText("");

        } else {
            edtProfileProfession.setText(parseUser.get("ProfileProfession") + "");

        } if (parseUser.get("ProfileHobbies") == null) {
            edtProfileHobbies.setText("");

        } else {
            edtProfileHobbies.setText(parseUser.get("ProfileHobbies") + "");

        }

        if (parseUser.get("ProfileSport") == null) {
            edtProfileSport.setText("");
        } else {
            edtProfileSport.setText(parseUser.get("ProfileSport") + "");
        }




       btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               parseUser.put("ProfileName",edtProfileName.getText().toString());
               parseUser.put("ProfileBio",edtProfileBio.getText().toString());
               parseUser.put("ProfileProfession",edtProfileProfession.getText().toString());
               parseUser.put("ProfileHobbies",edtProfileHobbies.getText().toString());
               parseUser.put("ProfileSport",edtProfileSport.getText().toString());



               parseUser.saveInBackground(new SaveCallback() {
                   @Override
                   public void done(ParseException e) {
                       if(e == null) {
                           FancyToast.makeText(getContext(),"Info Updated",FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();


                       } else{
                           FancyToast.makeText(getContext(),e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

                       }
                   }
               });
           }
       });
       return view;


    }

}
