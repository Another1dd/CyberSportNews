package com.another1dd.cybersportnews.activities

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.another1dd.cybersportnews.constants.CybersportNews

import com.another1dd.cybersportnews.fragments.RecyclerViewFragment
import com.github.florent37.materialviewpager.MaterialViewPager

import java.util.ArrayList
import java.util.LinkedHashMap

class MainActivity : AppCompatActivity() {
    private lateinit var materialViewPager: MaterialViewPager
    private var tabs = LinkedHashMap<Array<String>, Boolean>()
    internal var tabCount = 0
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.another1dd.cybersportnews.R.layout.activity_main)

        sharedPreferences = getSharedPreferences(CybersportNews.APP_PREF, Context.MODE_PRIVATE)


        tabs.put(CybersportNews.DOTA, sharedPreferences.getBoolean(CybersportNews.DOTA_IC, true))
        tabs.put(CybersportNews.CSGO, sharedPreferences.getBoolean(CybersportNews.CSGO_IC, true))
        tabs.put(CybersportNews.HEARTHSTONE, sharedPreferences.getBoolean(CybersportNews.HEARTHSTONE_IC, true))
        tabs.put(CybersportNews.LOL, sharedPreferences.getBoolean(CybersportNews.LOL_IC, true))
        tabs.put(CybersportNews.WOT, sharedPreferences.getBoolean(CybersportNews.WOT_IC, false))
        tabs.put(CybersportNews.HOTS, sharedPreferences.getBoolean(CybersportNews.HOTS_IC, false))
        tabs.put(CybersportNews.STARCRAFT, sharedPreferences.getBoolean(CybersportNews.STARCRAFT_IC, false))
        tabs.put(CybersportNews.OVERWATCH, sharedPreferences.getBoolean(CybersportNews.OVERWATCH_IC, false))
        tabs.put(CybersportNews.OTHER, sharedPreferences.getBoolean(CybersportNews.OTHER_IC, false))
        tabs.put(CybersportNews.LIFE, sharedPreferences.getBoolean(CybersportNews.LIFE_IC, false))

        tabCount = tabs.size

        materialViewPager = findViewById(com.another1dd.cybersportnews.R.id.materialViewPager)

        val toolbar = materialViewPager.toolbar

        if (toolbar != null) {
            setSupportActionBar(toolbar)

            val actionBar = supportActionBar
            actionBar?.setDisplayHomeAsUpEnabled(false)
            actionBar?.setDisplayShowHomeEnabled(false)
            actionBar?.setDisplayShowTitleEnabled(true)
            actionBar?.setDisplayUseLogoEnabled(false)
            actionBar?.setHomeButtonEnabled(false)
        }

        updateMaterialViewPager()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(com.another1dd.cybersportnews.R.menu.menu_main, menu)
        val dota_menu = menu.findItem(com.another1dd.cybersportnews.R.id.dota_menu)
        val csgo_menu = menu.findItem(com.another1dd.cybersportnews.R.id.csgo_menu)
        val heathstone_menu = menu.findItem(com.another1dd.cybersportnews.R.id.hearthstone_menu)
        val lol_menu = menu.findItem(com.another1dd.cybersportnews.R.id.lol_menu)
        val wot_menu = menu.findItem(com.another1dd.cybersportnews.R.id.wot_menu)
        val hots_menu = menu.findItem(com.another1dd.cybersportnews.R.id.hots_menu)
        val sc2_menu = menu.findItem(com.another1dd.cybersportnews.R.id.sc2_menu)
        val overwatch_menu = menu.findItem(com.another1dd.cybersportnews.R.id.overwatch_menu)
        val other_menu = menu.findItem(com.another1dd.cybersportnews.R.id.other_menu)
        val life_menu = menu.findItem(com.another1dd.cybersportnews.R.id.life_menu)

        dota_menu.isChecked = tabs[CybersportNews.DOTA]!!

        csgo_menu.isChecked = tabs[CybersportNews.CSGO]!!

        heathstone_menu.isChecked = tabs[CybersportNews.HEARTHSTONE]!!

        lol_menu.isChecked = tabs[CybersportNews.LOL]!!

        wot_menu.isChecked = tabs[CybersportNews.WOT]!!

        hots_menu.isChecked = tabs[CybersportNews.HOTS]!!

        sc2_menu.isChecked = tabs[CybersportNews.STARCRAFT]!!

        overwatch_menu.isChecked = tabs[CybersportNews.OVERWATCH]!!

        other_menu.isChecked = tabs[CybersportNews.OTHER]!!

        life_menu.isChecked = tabs[CybersportNews.LIFE]!!
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            com.another1dd.cybersportnews.R.id.dota_menu -> {
                if (item.isChecked) {
                    item.isChecked = false
                    tabs.put(CybersportNews.DOTA, false)
                    SavePreferences(CybersportNews.DOTA_IC, false)
                } else {
                    item.isChecked = true
                    tabs.put(CybersportNews.DOTA, true)
                    SavePreferences(CybersportNews.DOTA_IC, true)
                }
                updateMaterialViewPager()
                return true
            }
            com.another1dd.cybersportnews.R.id.csgo_menu -> {
                if (item.isChecked) {
                    item.isChecked = false
                    tabs.put(CybersportNews.CSGO, false)
                    SavePreferences(CybersportNews.CSGO_IC, false)
                } else {
                    item.isChecked = true
                    tabs.put(CybersportNews.CSGO, true)
                    SavePreferences(CybersportNews.CSGO_IC, true)
                }
                updateMaterialViewPager()
                return true
            }
            com.another1dd.cybersportnews.R.id.hearthstone_menu -> {
                if (item.isChecked) {
                    item.isChecked = false
                    tabs.put(CybersportNews.HEARTHSTONE, false)
                    SavePreferences(CybersportNews.HEARTHSTONE_IC, false)
                } else {
                    item.isChecked = true
                    tabs.put(CybersportNews.HEARTHSTONE, true)
                    SavePreferences(CybersportNews.HEARTHSTONE_IC, true)
                }
                updateMaterialViewPager()
                return true
            }
            com.another1dd.cybersportnews.R.id.lol_menu -> {
                if (item.isChecked) {
                    item.isChecked = false
                    tabs.put(CybersportNews.LOL, false)
                    SavePreferences(CybersportNews.LOL_IC, false)
                } else {
                    item.isChecked = true
                    tabs.put(CybersportNews.LOL, true)
                    SavePreferences(CybersportNews.LOL_IC, true)
                }
                updateMaterialViewPager()
                return true
            }
            com.another1dd.cybersportnews.R.id.wot_menu -> {
                if (item.isChecked) {
                    item.isChecked = false
                    tabs.put(CybersportNews.WOT, false)
                    SavePreferences(CybersportNews.WOT_IC, false)
                } else {
                    item.isChecked = true
                    tabs.put(CybersportNews.WOT, true)
                    SavePreferences(CybersportNews.WOT_IC, true)
                }
                updateMaterialViewPager()
                return true
            }
            com.another1dd.cybersportnews.R.id.hots_menu -> {
                if (item.isChecked) {
                    item.isChecked = false
                    tabs.put(CybersportNews.HOTS, false)
                    SavePreferences(CybersportNews.HOTS_IC, false)
                } else {
                    item.isChecked = true
                    tabs.put(CybersportNews.HOTS, true)
                    SavePreferences(CybersportNews.HOTS_IC, true)
                }
                updateMaterialViewPager()
                return true
            }
            com.another1dd.cybersportnews.R.id.sc2_menu -> {
                if (item.isChecked) {
                    item.isChecked = false
                    tabs.put(CybersportNews.STARCRAFT, false)
                    SavePreferences(CybersportNews.STARCRAFT_IC, false)
                } else {
                    item.isChecked = true
                    tabs.put(CybersportNews.STARCRAFT, true)
                    SavePreferences(CybersportNews.STARCRAFT_IC, true)
                }
                updateMaterialViewPager()
                return true
            }
            com.another1dd.cybersportnews.R.id.overwatch_menu -> {
                if (item.isChecked) {
                    item.isChecked = false
                    tabs.put(CybersportNews.OVERWATCH, false)
                    SavePreferences(CybersportNews.OVERWATCH_IC, false)
                } else {
                    item.isChecked = true
                    tabs.put(CybersportNews.OVERWATCH, true)
                    SavePreferences(CybersportNews.OVERWATCH_IC, true)
                }
                updateMaterialViewPager()
                return true
            }
            com.another1dd.cybersportnews.R.id.other_menu -> {
                if (item.isChecked) {
                    item.isChecked = false
                    tabs.put(CybersportNews.OTHER, false)
                    SavePreferences(CybersportNews.OTHER_IC, false)
                } else {
                    item.isChecked = true
                    tabs.put(CybersportNews.OTHER, true)
                    SavePreferences(CybersportNews.OTHER_IC, true)
                }
                updateMaterialViewPager()
                return true
            }
            com.another1dd.cybersportnews.R.id.life_menu -> {
                if (item.isChecked) {
                    item.isChecked = false
                    tabs.put(CybersportNews.LIFE, false)
                    SavePreferences(CybersportNews.LIFE_IC, false)
                } else {
                    item.isChecked = true
                    tabs.put(CybersportNews.LIFE, true)
                    SavePreferences(CybersportNews.LIFE_IC, true)
                }
                updateMaterialViewPager()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }


    private fun updateMaterialViewPager() {
        val arrayList = ArrayList<Array<String>>()

        for ((key, value) in tabs) {
            if (value) {
                arrayList.add(key)
            }
        }


        tabCount = arrayList.size


        materialViewPager.viewPager.adapter = object : FragmentStatePagerAdapter(supportFragmentManager) {

            override fun getItem(position: Int): Fragment {
                return when (position) {
                    0 -> RecyclerViewFragment.newInstance(arrayList[0][0])
                    1 -> RecyclerViewFragment.newInstance(arrayList[1][0])
                    2 -> RecyclerViewFragment.newInstance(arrayList[2][0])
                    3 -> RecyclerViewFragment.newInstance(arrayList[3][0])
                    4 -> RecyclerViewFragment.newInstance(arrayList[4][0])
                    5 -> RecyclerViewFragment.newInstance(arrayList[5][0])
                    6 -> RecyclerViewFragment.newInstance(arrayList[6][0])
                    7 -> RecyclerViewFragment.newInstance(arrayList[7][0])
                    8 -> RecyclerViewFragment.newInstance(arrayList[8][0])
                    9 -> RecyclerViewFragment.newInstance(arrayList[9][0])
                    else -> RecyclerViewFragment.newInstance(CybersportNews.MAIN_HTTP)
                }
            }

            override fun getCount(): Int {
                return tabCount
            }


            override fun getPageTitle(position: Int): CharSequence {
                return when (position) {
                    0 -> arrayList[0][1]
                    1 -> arrayList[1][1]
                    2 -> arrayList[2][1]
                    3 -> arrayList[3][1]
                    4 -> arrayList[4][1]
                    5 -> arrayList[5][1]
                    6 -> arrayList[6][1]
                    7 -> arrayList[7][1]
                    8 -> arrayList[8][1]
                    9 -> arrayList[9][1]
                    else -> "Page " + position
                }
            }
        }


        materialViewPager.viewPager.offscreenPageLimit = tabCount

        materialViewPager.pagerTitleStrip.setViewPager(materialViewPager.viewPager)
    }

    private fun SavePreferences(key: String, value: Boolean?) {
        sharedPreferences = getSharedPreferences(CybersportNews.APP_PREF, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value!!)
        editor.apply()
    }
}
