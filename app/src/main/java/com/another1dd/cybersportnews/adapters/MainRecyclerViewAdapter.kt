package com.another1dd.cybersportnews.adapters


import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.another1dd.cybersportnews.model.ParseText
import kotlinx.android.synthetic.main.list_item_card.view.*
import java.util.*
import java.util.concurrent.ExecutionException

class MainRecyclerViewAdapter(private val map: LinkedHashMap<Array<String>, String>) : RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder>() {
    open class ViewHolder(internal val cardView: CardView) : RecyclerView.ViewHolder(cardView)

    override fun getItemCount(): Int {
        return map.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(com.another1dd.cybersportnews.R.layout.list_item_card, parent, false) as CardView
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = ArrayList<Array<String>>()
        val textParsed = arrayOf("")

        for ((key) in map) {
            list.add(key)
        }

        val cardView = holder.cardView
        val title = list[position][0]
        val time = list[position][1]
        val titleSpan = SpannableStringBuilder(title + " - " + time)
        titleSpan.setSpan(RelativeSizeSpan(0.8f), title.length + 3,
                title.length + time.length + 3, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        titleSpan.setSpan(ForegroundColorSpan(Color.rgb(105, 105, 105)), title.length + 3,
                title.length + time.length + 3, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

        holder.itemView.txt.text = titleSpan

        holder.itemView.newsTxt.visibility = View.GONE

        holder.itemView.buttonHttp.visibility = View.GONE

        holder.itemView.buttonHttp.setOnClickListener { v ->
            val browseIntent = Intent(Intent.ACTION_VIEW,
                    Uri.parse(map[list[holder.adapterPosition - 1]]))
            v.context.startActivity(browseIntent)
        }

        cardView.setOnClickListener {
            val parseText = ParseText()
            parseText.execute(map[list[holder.adapterPosition - 1]])

            if (holder.itemView.newsTxt.visibility == View.GONE) {
                if (textParsed[0] == "") {
                    try {
                        val map = parseText.get()
                        var lead: String? = map[LEAD]
                        val main = map[MAIN_NEWS_TEXT]
                        val panel = map[PANEL_BODY]
                        val blogText = map[BLOG_TEXT]
                        try {
                            lead = lead!!.substring(0, lead.indexOf("Связанные"))
                        } catch (e: Exception) {

                        }

                        if (lead == null && panel != null) {
                            textParsed[0] = panel
                        } else if ((panel == null) and (lead != null)) {
                            textParsed[0] = lead + "\n" + main
                        } else {
                            textParsed[0] = blogText.toString()
                        }
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    } catch (e: ExecutionException) {
                        e.printStackTrace()
                    }
                }
                holder.itemView.newsTxt.text = textParsed[0]
                holder.itemView.newsTxt.visibility = View.VISIBLE
                holder.itemView.buttonHttp.visibility = View.VISIBLE
            } else {
                holder.itemView.newsTxt.visibility = View.GONE
                holder.itemView.buttonHttp.visibility = View.GONE
            }
        }


    }

    companion object {
        val MAIN_NEWS_TEXT = "MNT"
        val LEAD = "lead"
        val PANEL_BODY = "panel"
        val BLOG_TEXT = "blog"
    }
}