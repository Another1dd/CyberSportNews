package com.another1dd.cybersportnews_materialviewpager;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class RecyclerViewFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    final ArrayList<String> arrayList = new ArrayList<>();

    public static RecyclerViewFragment newInstance() {
        return new RecyclerViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        //permet un affichage sous forme liste verticale
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        //100 faux contenu
        ParseTitle parseTitle = new ParseTitle();
        parseTitle.execute("http://www.cybersport.ru/news/?game=21&MUL_MODE=");

        try {
            final LinkedHashMap<String, String> hashMap = parseTitle.get();


            for (Map.Entry entry : hashMap.entrySet())
            {
                arrayList.add(entry.getKey().toString());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        //penser à passer notre Adapter (ici : TestRecyclerViewAdapter) à un RecyclerViewMaterialAdapter
        mAdapter = new RecyclerViewMaterialAdapter(new TestRecyclerViewAdapter(arrayList));
        mRecyclerView.setAdapter(mAdapter);

        //notifier le MaterialViewPager qu'on va utiliser une RecyclerView
        MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView);
    }
}
