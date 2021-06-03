package com.example.g1g;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Search extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //Variable declaration
    ArrayList<String> bookArray = new ArrayList<String>();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    LayoutInflater inflater;
    LinearLayout book_scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //Number of rows in the list
        int Number_Of_Rows = 10;

        //The spinner for choosing the "Sort by:" menu options
        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner1_Options, android.R.layout.simple_spinner_item);
        //ArrayAdapter adapter = new ArrayAdapter( this, R.array.spinner1_Options, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this); //Add listener for when user clicks on one of the items in the menu
        //adapter.notifyDataSetChanged(); //needed?

        //The menu for scrolling books
        book_scroll = findViewById(R.id.book_scroll);

        inflater = LayoutInflater.from(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        System.out.println( "c");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for ( DataSnapshot snap: dataSnapshot.getChildren())
                {
                    HashMap< String, String> temp;
                    temp = ( HashMap< String, String>)snap.getValue();
                    bookArray.add( temp.get( "Author: "));
                }


                for (int i = 0; i < bookArray.size(); i= i + 2) {
                    View view = inflater.inflate(R.layout.item, book_scroll, false);

                    //( (TextView)findViewById(R.id.textView1)).setText( bookArray.get( i));


                    //TextView textView2 = view.findViewById(R.id.textView2);
                    //textView2.setText("Item: " + i);
                    //( (TextView)findViewById(R.id.textView2)).setText( bookArray.get( i + 1));
                    //ImageButton.setBackgroundResource();

                    //ImageButton imageButton2 = view.findViewById(R.id.imageButton2);
                    //imageButton.setBackgroundResource();

                    //book_scroll.addView(view);

                    TextView textView1 = view.findViewById(R.id.textView1);

                    TextView textView2 = view.findViewById(R.id.textView2);

                    textView1.setText( bookArray.get( i));
                    textView2.setText( bookArray.get( i + 1));

                    ImageButton imageButton1 = view.findViewById(R.id.imageButton1);

                    ImageButton imageButton2 = view.findViewById(R.id.imageButton2);

                    book_scroll.addView(view);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println( databaseError.toString());
            }
        });

       // bookArray.add( "Nima");
       // bookArray.add( "Ammaar");

       // bookArray.add( "Wasim");
       // bookArray.add( "Burak");

        //For the search widget
        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }

    }

    //has to be filled after creating sql
    private void doMySearch(String query) {
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //For the search widget
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_actions, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);//what is this??!
        SearchView searchView = (SearchView)
                MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        return false;
                    }
                });
        //SearchManager searchManager = (SearchManager)
        //        getSystemService(Context.SEARCH_SERVICE);
        //Can be replaced with getComponentName()
        //if this searchable activity is the current activity
        //ComponentName componentName=
        //        new ComponentName(context.MainActivity.class);
        //searchView.setSearchableInfo(
        //        searchManager.getSearchableInfo(componentName));

        return true;
    }
}






