package com.another1dd.cybersportnews_materialviewpager;


import android.net.sip.SipSession;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import java.util.LinkedHashMap;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public class TestRecyclerViewAdapter extends RecyclerView.Adapter<TestRecyclerViewAdapter.ViewHolder> {


    private LinkedHashMap<String,String> map;





    public static class ViewHolder  extends RecyclerView.ViewHolder{
        private CardView cardView;
        public ViewHolder(CardView itemView) {
            super(itemView);
            cardView = itemView;
        }
    }

    public TestRecyclerViewAdapter(LinkedHashMap<String,String> map) {
        this.map = map;
    }

    @Override
    public int getItemCount() {
        return map.size();
    }

    @Override
    public TestRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView view = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_card, parent, false);
        return new TestRecyclerViewAdapter.ViewHolder(view) {
        };
    }



    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ArrayList<String> list = new ArrayList<>();
        final String[] textParsed = {""};

        for (Map.Entry entry : map.entrySet())
        {
            list.add(entry.getKey().toString());


        }

        final CardView cardView = holder.cardView;
        final TextView textView = (TextView) cardView.findViewById(R.id.txt);
        textView.setText(list.get(position));
        final TextView textView1 = (TextView) cardView.findViewById(R.id.news_txt);
        textView1.setVisibility(View.GONE);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseText parseText = new ParseText();
                parseText.execute(map.get(list.get(holder.getAdapterPosition() - 1)));



                if (textView1.getVisibility() == View.GONE)
                {
                    if (textParsed[0].equals(""))
                    {
                        try {
                            textParsed[0] = parseText.get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                    textView1.setText(textParsed[0]);
                    textView1.setVisibility(View.VISIBLE);
                }else {
                    textView1.setVisibility(View.GONE);
                }



            }
        });






    }
}