package com.another1dd.cybersportnews.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.another1dd.cybersportnews.model.ParseTitle
import com.another1dd.cybersportnews.adapters.TestRecyclerViewAdapter
import com.github.florent37.materialviewpager.MaterialViewPagerHelper
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter

import java.util.LinkedHashMap
import java.util.concurrent.ExecutionException


class RecyclerViewFragment : Fragment() {

    private var linkedHashMap = LinkedHashMap<Array<String>, String>()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(com.another1dd.cybersportnews.R.layout.fragment_recycler_view, container, false)

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mRecyclerView = view!!.findViewById<View>(com.another1dd.cybersportnews.R.id.recyclerView) as RecyclerView


        val layoutManager = LinearLayoutManager(activity)
        mRecyclerView.layoutManager = layoutManager
        mRecyclerView.setHasFixedSize(true)

        val swipeRefreshLayout = view.findViewById<View>(com.another1dd.cybersportnews.R.id.swipe) as SwipeRefreshLayout
        swipeRefreshLayout.setColorSchemeResources(com.another1dd.cybersportnews.R.color.red, com.another1dd.cybersportnews.R.color.red, com.another1dd.cybersportnews.R.color.red)
        swipeRefreshLayout.setOnRefreshListener {
            val parseTitle = ParseTitle()
            parseTitle.execute(arguments.getString(BUNDLE_CONTENT))

            try {
                linkedHashMap = parseTitle.get()


            } catch (e: InterruptedException) {
                e.printStackTrace()
            } catch (e: ExecutionException) {
                e.printStackTrace()
            }

            val mAdapter = RecyclerViewMaterialAdapter(TestRecyclerViewAdapter(linkedHashMap))
            mRecyclerView.adapter = mAdapter
            swipeRefreshLayout.isRefreshing = false
        }

        val parseTitle = ParseTitle()
        parseTitle.execute(arguments.getString(BUNDLE_CONTENT))

        try {
            linkedHashMap = parseTitle.get()


        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        }


        val mAdapter = RecyclerViewMaterialAdapter(TestRecyclerViewAdapter(linkedHashMap))
        mRecyclerView.adapter = mAdapter


        MaterialViewPagerHelper.registerRecyclerView(activity, mRecyclerView)
    }

    companion object {

        private val BUNDLE_CONTENT = "bundle_content"


        fun newInstance(href: String): RecyclerViewFragment {
            val fragment = RecyclerViewFragment()
            val arguments = Bundle()
            arguments.putString(BUNDLE_CONTENT, href)
            fragment.arguments = arguments
            return fragment
        }
    }
}
