package com.example.g1g;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class UserProfile extends AppCompatActivity {

    //Fields
    private User user;

    //public UserProfile( User user)
    //{
    //  this.user = user;
    //}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //variable Declarations
        TextView userName;
        TextView university;
        TextView semester;
        TextView course;
        ImageView imageProfile;

        //initialisation-- finding the variable in the activity
        imageProfile = (ImageView) findViewById( R.id.imageView3);
        userName = (TextView) findViewById( R.id.textView4);
        university = (TextView) findViewById( R.id.textView7);
        semester = (TextView) findViewById( R.id.textView8);
        course = (TextView) findViewById( R.id.textView9);

        //displaying info
        userName.setText( user.getUserName());
        university.setText( "Bilkent");
        semester.setText( user.getRegisteredSemester());
        course.setText( user.getDepartment());
    }
}