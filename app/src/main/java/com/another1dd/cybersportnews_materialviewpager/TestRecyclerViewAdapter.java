package com.another1dd.cybersportnews_materialviewpager;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import junit.framework.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestRecyclerViewAdapter extends RecyclerView.Adapter<TestRecyclerViewAdapter.ViewHolder> {

    ArrayList<String> list;

    public static class ViewHolder  extends RecyclerView.ViewHolder{
        private CardView cardView;
        public ViewHolder(CardView itemView) {
            super(itemView);
            cardView = itemView;
        }
    }

    public TestRecyclerViewAdapter(ArrayList<String> list) {
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public TestRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView view = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_card, parent, false);
        return new TestRecyclerViewAdapter.ViewHolder(view) {
        };
    }


    public void onBindViewHolder(ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView textView = (TextView) cardView.findViewById(R.id.txt);
        textView.setText(list.get(position));
    }
}