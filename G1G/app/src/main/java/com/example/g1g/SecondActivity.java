package com.example.g1g;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SecondActivity extends AppCompatActivity {
    //Field Declarations
    private EditText email;
    private EditText password;
    private Button login;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate( savedInstanceState);
        setContentView( R.layout.activity_second);

        //initialisation
        email = findViewById( R.id.editTextUsername);
        password = findViewById( R.id.editTextPassword);
        login = findViewById( R.id.buttonSignIn2);
        mAuth = FirebaseAuth.getInstance();

        //adding on click listener to the login button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = email.getText().toString().trim();
                String txt_password = password.getText().toString().trim();

                if ( TextUtils.isEmpty( txt_email) || TextUtils.isEmpty( txt_password)){
                    Toast.makeText(SecondActivity.this, "Empty Credentials!", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(txt_email , txt_password);
                }
            }
        });
    }

    private void loginUser(String email, String password) {

        mAuth.signInWithEmailAndPassword(email , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(SecondActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SecondActivity.this , SecondActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();

                    //temporary
                    Intent startCreation;
                    startCreation = new Intent( getApplicationContext(), BookList.class);
                    startActivity( startCreation);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SecondActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
