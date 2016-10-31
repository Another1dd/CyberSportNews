package com.another1dd.cybersportnews_materialviewpager;



import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import java.net.URI;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.LinkedHashMap;

import java.util.Map;
import java.util.concurrent.ExecutionException;

class TestRecyclerViewAdapter extends RecyclerView.Adapter<TestRecyclerViewAdapter.ViewHolder> {

    static final String MAIN_NEWS_TEXT = "MNT";
    static final String LEAD = "lead";
    static final String PANEL_BODY = "panel";

    private LinkedHashMap<String[], String> map;


    static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        ViewHolder(CardView itemView) {
            super(itemView);
            cardView = itemView;
        }
    }


    TestRecyclerViewAdapter(LinkedHashMap<String[], String> map) {
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
        final ArrayList<String[]> list = new ArrayList<>();
        final String[] textParsed = {""};

        for (Map.Entry entry : map.entrySet())
        {
            list.add((String[]) entry.getKey());


        }

        final CardView cardView = holder.cardView;
        final TextView textView = (TextView) cardView.findViewById(R.id.txt);
        String title = list.get(position)[0];
        String time = list.get(position)[1];
        SpannableStringBuilder titleSpan = new SpannableStringBuilder(title + " - " + time);
        titleSpan.setSpan(new RelativeSizeSpan(0.8f), title.length() + 3,
                title.length() + time.length() + 3, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        titleSpan.setSpan(new ForegroundColorSpan(Color.rgb(105, 105, 105)), title.length() + 3,
                title.length() + time.length() + 3, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        textView.setText(titleSpan);


        final TextView textView1 = (TextView) cardView.findViewById(R.id.news_txt);
        textView1.setVisibility(View.GONE);
        final Button button = (Button) cardView.findViewById(R.id.button_http);

        button.setVisibility(View.GONE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browseIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(map.get(list.get(holder.getAdapterPosition() - 1))));
                v.getContext().startActivity(browseIntent);

            }
        });

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

                            HashMap<String,String> map = parseText.get();
                            String lead = map.get(LEAD);
                            String main = map.get(MAIN_NEWS_TEXT);
                            String panel = map.get(PANEL_BODY);
                            try {
                                lead = lead.substring(0, lead.indexOf("Связанные"));
                            } catch (Exception e)
                            {

                            }
                            if (lead == null)
                            {
                                textParsed[0] = panel;
                            }else {
                                textParsed[0] = lead + "\n" + main;
                            }



                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                    textView1.setText(textParsed[0]);
                    textView1.setVisibility(View.VISIBLE);
                    button.setVisibility(View.VISIBLE);
                }else {
                    textView1.setVisibility(View.GONE);
                    button.setVisibility(View.GONE);
                }



            }
        });






    }
}