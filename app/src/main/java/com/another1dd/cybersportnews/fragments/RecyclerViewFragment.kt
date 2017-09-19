package com.another1dd.cybersportnews.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.another1dd.cybersportnews.R
import com.another1dd.cybersportnews.adapters.MainRecyclerViewAdapter
import com.another1dd.cybersportnews.extensions.inflate
import com.another1dd.cybersportnews.model.ParseTitle
import com.github.florent37.materialviewpager.MaterialViewPagerHelper
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter
import kotlinx.android.synthetic.main.fragment_recycler_view.*
import java.util.*
import java.util.concurrent.ExecutionException


class RecyclerViewFragment : Fragment() {
    private var linkedHashMap = LinkedHashMap<Array<String>, String>()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_recycler_view)

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)

        swipe.setColorSchemeResources(com.another1dd.cybersportnews.R.color.red, com.another1dd.cybersportnews.R.color.red, com.another1dd.cybersportnews.R.color.red)
        swipe.setOnRefreshListener {
            val parseTitle = ParseTitle()
            parseTitle.execute(arguments.getString(BUNDLE_CONTENT))

            try {
                linkedHashMap = parseTitle.get()


            } catch (e: InterruptedException) {
                e.printStackTrace()
            } catch (e: ExecutionException) {
                e.printStackTrace()
            }

            val mAdapter = RecyclerViewMaterialAdapter(MainRecyclerViewAdapter(linkedHashMap))
            recyclerView.adapter = mAdapter
            swipe.isRefreshing = false
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


        val mAdapter = RecyclerViewMaterialAdapter(MainRecyclerViewAdapter(linkedHashMap))
        recyclerView.adapter = mAdapter


        MaterialViewPagerHelper.registerRecyclerView(activity, recyclerView)
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
