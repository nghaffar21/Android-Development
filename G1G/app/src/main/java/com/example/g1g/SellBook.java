package com.example.g1g;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.g1g.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class SellBook extends AppCompatActivity {
    //fields
    private String bookName;
    private EditText author;
    private EditText price;
    private EditText edition;
    private EditText subject;
    private EditText field;
    private Book book;
    private String user;
    private FirebaseDatabase database;
    DatabaseReference myRef;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_book);

        //initialisation
        author =  findViewById( R.id.author);
        price = findViewById( R.id.condition);
        edition = findViewById( R.id.edition);
        field = findViewById( R.id.field);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        mAuth = FirebaseAuth.getInstance();

        Button add;
        add = findViewById( R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInstance();
            }
        });
    }

    /**sets the book instance in the
     * database
     */
    private void setInstance()
    {
        HashMap< String, Object> hashMap;
        hashMap  = new HashMap<>();

        //Saving data on the database
        bookName = ( (EditText)findViewById( R.id.bookName)).getText().toString();
        setBook();
        setUser();
        hashMap.put( "Book: ", bookName);
        hashMap.put( "Author: ", author.getText().toString());
        hashMap.put( "Price: ", price.getText().toString());
        hashMap.put( "Field: ", field.getText().toString());
        hashMap.put( "Edition: ", edition.getText().toString());
        myRef.child( bookName).updateChildren( hashMap);
    }


    public void displayToast(View v){
        Toast.makeText(SellBook.this,"Added to Cart", Toast.LENGTH_SHORT).show();
    }

    /**this method creates a instance of the Book class
     * that has to be added to the database
     */
    private void setBook()
    {
        book = new Book( bookName, 2000, author.getText().toString(),
                field.getText().toString(), Integer.parseInt( price.getText().toString()));
    }

    /**this method gets the email of the current
     * firebase user
     */
    private void setUser()
    {
        user = mAuth.getCurrentUser().getEmail();
    }
}