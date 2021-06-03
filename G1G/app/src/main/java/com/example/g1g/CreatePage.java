package com.example.g1g;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class CreatePage extends AppCompatActivity {

    //Constants
    private static final String TAG = "CreatePage";

    //properties
    private FirebaseAnalytics mFirebaseAnalytics;
    private Button createAcc;
    private FirebaseAuth mAuth;
    private User user;
    private String email;
    private EditText university;
    private String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_page);

        final EditText password;
        //instantiating  an array to hold the user information
        // having an array makes it easier and efficient to check info
        password = findViewById( R.id.editTextPassword3);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        university = findViewById( R.id.editText5);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //adding an onClickListener to the create button
        createAcc = (Button) findViewById(R.id.createAcc);
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pass = password.getText().toString();
                email = ( (EditText)findViewById(R.id.editText8)).getText().toString();
                createUser(email, pass);
            }
        });
        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                pass = password.getText().toString();
                email = ( (EditText)findViewById(R.id.editText8)).getText().toString();
                createUser( email, pass);
            }
        });
    }

    /**
     * When the Activity starts
     */
    @Override
    public void onStart() {
        super.onStart();
        // checks for presence of a logged in user
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    /**
     * updateUI method updates the UI of
     * based on the actions of the user
     */
    protected void updateUI(FirebaseUser user) {
        //if the user exits the create button is set invisible
        if (user != null) {
            createAcc.setVisibility(Button.INVISIBLE);
            Toast.makeText(this, "User email is logged in",
                    Toast.LENGTH_SHORT);
        }
    }

    /**
     * this method creates an user in
     * the database
     *
     * @param email
     * @param password
     */
    protected void createUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //setUser();
                            updateUI(user);
                            createAcc.setVisibility( Button.VISIBLE);

                            //go to Sell book page
                            sell();


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(CreatePage.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                        createAcc.setVisibility( Button.VISIBLE);
                    }
                });
    }

    /** takes to the sell book page of the
     * application
     */
    private void sell()
    {
        Intent intent;
        intent = new Intent( getApplicationContext(), SellBook.class);
        startActivity( intent);
    }

    /** this method sets up a user object and stores it
     * in database
     */
    private void setUser()
    {
        //creating an instance
        this.user = new User( "", pass, ( (EditText)findViewById( R.id.editTextUsername2)).getText().toString()
                , Integer.parseInt(( (EditText)findViewById( R.id.editText6)).getText().toString()),
                ( (EditText)findViewById( R.id.editText4)).getText().toString(), email);

        //addToDatabase();
    }

    /** this method sets up the object in the database, as
     *  an HashMap object
     */
    private void addToDatabase()
    {
        //Variable declaration
        FirebaseDatabase firebaseDatabase;
        final DatabaseReference databaseReference;;

        //initialisation
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();databaseReference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            //Variable Declaration
            HashMap< String, User> hashMap;

            hashMap = new HashMap<>();
            setUser();
            //if there is already a users child
            if ( dataSnapshot.hasChild( "Users"))
            {
                hashMap = (HashMap<String, User>) dataSnapshot.child( "Users").getValue();
                hashMap.put( user.getUserName(), user);
                System.out.println( "logged in");
            }
            else
            {
                hashMap.put( user.getUserName(), user);

                databaseReference.child( "Users").updateChildren( (HashMap)hashMap);
            }
        }
        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {}});

    }
}