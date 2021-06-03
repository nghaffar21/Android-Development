package com.example.g1g;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //temp attempt ist creation
        Button signIn;
        Button createAcc;

        signIn = ( Button) findViewById( R.id.buttonSignIn1);
        createAcc = ( Button) findViewById( R.id.buttonCreateAcc);

        //program code
        //signIn.setBackground( new ColorDrawable());
        signIn.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v)
            {
                //new Main2Activity();
                Intent startSecond;
                startSecond = new Intent( getApplicationContext(), SecondActivity.class);
                startActivity( startSecond);
            }
        });

        //redirects to create a new account
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startCreation;
                startCreation = new Intent( getApplicationContext(), CreatePage.class);
                startActivity( startCreation);
            }
        });
    }
}
