package com.example.g1g;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.service.autofill.Dataset;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.g1g.ItemAdapter;
import com.example.g1g.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class BookList extends AppCompatActivity {

    //Fields
    ListView list;
    EditText search;
    ItemAdapter adapter;
    Bundle bundle;
    //ArrayList<String> array;
    ArrayList<String> items;
    ArrayList<String> prices;
    ArrayList<String> edition;
    String searchString;
    //Database fields
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        //Coding starts
        list = (ListView) findViewById( R.id.list);
        items = new ArrayList<>();
        prices = new ArrayList<>();
        edition = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        //bundle = getIntent().getExtras();
        search = findViewById( R.id.searchText);
        search.setOnClickListener( new SearchOnClick());
        findViewById( R.id.button).setOnClickListener( new SearchOnClick());

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String temp = (String)list.getItemAtPosition( position);
                Toast.makeText( BookList.this, "You clicked on " + temp, Toast.LENGTH_SHORT).show();

                Intent intent;
                intent = new Intent( BookList.this, BookInfoActivity.class);
                intent.putExtra( "book", temp);
                intent.putExtra( "price", prices.get( position));
                intent.putExtra( "author", edition.get( position));
                //intent.putExtras("author", edition.get( position));
                startActivity( intent);
            }
        });
    }

    /** Inner class, this is a OnClickListener that responds
     * to click on the search button or search field
     */
    private class SearchOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            searchString = search.getText().toString();
            items = new ArrayList<>();
            prices = new ArrayList<>();
            edition = new ArrayList<>();

            if ( searchString != null)
            {
                System.out.println( "ad");
                databaseReference = firebaseDatabase.getReference().child(searchString);
                System.out.println( "cb");
            }
            else {
                databaseReference = firebaseDatabase.getReference();
            }

            databaseReference.addValueEventListener(new ValueListener());
        }
    }

    /** this is the implementation of ValueEventListener
     * and responds to changes in the database
     */
    private class ValueListener implements ValueEventListener {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for ( DataSnapshot snap: dataSnapshot.getChildren())
            {
                HashMap< String, String> temp;
                System.out.println( searchString);

                //temp = new HashMap<String, String>();

                temp =  ( HashMap<String, String>) snap.getValue();

                System.out.println( searchString + (temp).toString());
                items.add( temp.get( "Book: "));
                prices.add( temp.get( "Price: "));
                edition.add( temp.get( "Author: "));

                //items.add( ((HashMap<String, String>) snap).get("Book: "));
                //prices.add( ((HashMap<String, String>) snap).get("Price: "));
                //edition.add( ((HashMap<String, String>) snap).get("Author: "));

                //setting up the custom adapter
                adapter = new ItemAdapter( getApplicationContext(),  items, prices, edition);
                list.setAdapter( adapter);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            System.out.println( databaseError.toString());
        }
    }
}
