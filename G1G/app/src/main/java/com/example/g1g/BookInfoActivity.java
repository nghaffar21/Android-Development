package com.example.g1g;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class BookInfoActivity extends AppCompatActivity {

    Button addToCart;
    TextView textView4;
    String book;
    String author;
    String price;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);

        //Variable declaration
        textView4 = ( TextView) findViewById( R.id.textView4);
        book = getIntent().getStringExtra( "book");
        price = getIntent().getStringExtra( "price");
        author= getIntent().getStringExtra( "author");
        textView4.setText( book);
        databaseReference = FirebaseDatabase.getInstance().getReference();


        findViewById( R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeBook();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        System.out.println( book);
        ( (TextView)findViewById( R.id.displayPrice)).setText( price);
        ( (TextView)findViewById(R.id.displayAuthor)).setText( author);
        textView4.setVisibility( View.VISIBLE);
    }

    /** this method removes the book from the
     *  database
     */
    private void removeBook()
    {
        HashMap< String, Object> hashMap;
        hashMap = new HashMap<>();

        System.out.println( book);
        /*databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HashMap< String, Object> temp;
                temp = (HashMap< String, Object>)dataSnapshot.child( book).getValue();
                Book bookSold;
                bookSold = ( Book)temp.get( "ID: ");
                System.out.println( temp.get( "Author: "));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

        databaseReference.child( book).removeValue();

        addToSold();
    }

    /** this method adds the book to the
     * sold bookshelf in the database
     */
    private void addToSold()
    {
    }
}
