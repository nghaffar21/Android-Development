package com.example.g1g;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter {
    //fields
    LayoutInflater mInflater;
    ArrayList<String> items;
    ArrayList<String> prices;
    ArrayList<String> edition;

    public ItemAdapter(Context context, ArrayList<String> items, ArrayList<String> prices, ArrayList<String> edition)
    {
        this.items = items;
        this.prices = prices;
        this.edition = edition;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get( position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //how to show or display data
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        TextView cost;
        TextView seller;
        TextView edi;
        String name;
        String descrip;
        String pri;

        //initialisation
        v = mInflater.inflate( R.layout.books, null);
        seller = (TextView) v.findViewById(R.id.seller);
        cost = (TextView) v.findViewById(R.id.price);
        edi = (TextView) v.findViewById(R.id.edition);

        name = items.get( position);
        pri = prices.get( position);
        descrip = edition.get( position);

        cost.setText(pri);
        seller.setText(name);
        edi.setText( descrip);

        return v;
    }
}
