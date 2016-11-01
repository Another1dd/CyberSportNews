package com.another1dd.cybersportnews_materialviewpager;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;

import java.util.LinkedHashMap;
import java.util.concurrent.ExecutionException;


public class RecyclerViewFragment extends Fragment {

    LinkedHashMap<String[], String> linkedHashMap = new LinkedHashMap<>();

    private static final String BUNDLE_CONTENT = "bundle_content";

    public RecyclerViewFragment() {
    }


    public static RecyclerViewFragment newInstance(String href) {
        final RecyclerViewFragment fragment = new RecyclerViewFragment();
        final Bundle arguments = new Bundle();
        arguments.putString(BUNDLE_CONTENT, href);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler_view, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        swipeRefreshLayout.setColorSchemeResources
                (R.color.red, R.color.red, R.color.red);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ParseTitle parseTitle = new ParseTitle();
                parseTitle.execute(getArguments().getString(BUNDLE_CONTENT));

                try {
                    linkedHashMap = parseTitle.get();


                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                RecyclerView.Adapter mAdapter = new RecyclerViewMaterialAdapter(new TestRecyclerViewAdapter(linkedHashMap));
                mRecyclerView.setAdapter(mAdapter);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        ParseTitle parseTitle = new ParseTitle();
        parseTitle.execute(getArguments().getString(BUNDLE_CONTENT));

        try {
            linkedHashMap = parseTitle.get();


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        RecyclerView.Adapter mAdapter = new RecyclerViewMaterialAdapter(new TestRecyclerViewAdapter(linkedHashMap));
        mRecyclerView.setAdapter(mAdapter);


        MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView);
    }
}
