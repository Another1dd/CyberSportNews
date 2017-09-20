package com.another1dd.cybersportnews.activities

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.another1dd.cybersportnews.R
import com.another1dd.cybersportnews.constants.CybersportNews
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tabs = LinkedHashMap<Array<String>, Boolean>()
    internal var tabCount = 0
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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


        setSupportActionBar(mainToolbar)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)
        actionBar?.setDisplayShowHomeEnabled(false)
        actionBar?.setDisplayShowTitleEnabled(true)
        actionBar?.setDisplayUseLogoEnabled(false)
        actionBar?.setHomeButtonEnabled(false)


//        updateMaterialViewPager()
    }

    /*override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(com.another1dd.cybersportnews.R.menu.menu_main, menu)
        val dotaMenu = menu.findItem(com.another1dd.cybersportnews.R.id.dotaMenu)
        val csgoMenu = menu.findItem(com.another1dd.cybersportnews.R.id.csGoMenu)
        val heathstoneMenu = menu.findItem(com.another1dd.cybersportnews.R.id.hearthstoneMenu)
        val lolMenu = menu.findItem(com.another1dd.cybersportnews.R.id.lolMenu)
        val wotMenu = menu.findItem(com.another1dd.cybersportnews.R.id.wotMenu)
        val hotsMenu = menu.findItem(com.another1dd.cybersportnews.R.id.hotsMenu)
        val sc2Menu = menu.findItem(com.another1dd.cybersportnews.R.id.sc2Menu)
        val overwatchMenu = menu.findItem(com.another1dd.cybersportnews.R.id.overwatchMenu)
        val otherMenu = menu.findItem(com.another1dd.cybersportnews.R.id.otherMenu)
        val lifeMenu = menu.findItem(com.another1dd.cybersportnews.R.id.lifeMenu)

        dotaMenu.isChecked = tabs[CybersportNews.DOTA]!!
        csgoMenu.isChecked = tabs[CybersportNews.CSGO]!!
        heathstoneMenu.isChecked = tabs[CybersportNews.HEARTHSTONE]!!
        lolMenu.isChecked = tabs[CybersportNews.LOL]!!
        wotMenu.isChecked = tabs[CybersportNews.WOT]!!
        hotsMenu.isChecked = tabs[CybersportNews.HOTS]!!
        sc2Menu.isChecked = tabs[CybersportNews.STARCRAFT]!!
        overwatchMenu.isChecked = tabs[CybersportNews.OVERWATCH]!!
        otherMenu.isChecked = tabs[CybersportNews.OTHER]!!
        lifeMenu.isChecked = tabs[CybersportNews.LIFE]!!
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.dotaMenu -> {
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
            R.id.csGoMenu -> {
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
            R.id.hearthstoneMenu -> {
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
            R.id.lolMenu -> {
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
            R.id.wotMenu -> {
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
            R.id.hotsMenu -> {
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
            R.id.sc2Menu -> {
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
            R.id.overwatchMenu -> {
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
            com.another1dd.cybersportnews.R.id.otherMenu -> {
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
            com.another1dd.cybersportnews.R.id.lifeMenu -> {
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
    }*/

    private fun SavePreferences(key: String, value: Boolean?) {
        sharedPreferences = getSharedPreferences(CybersportNews.APP_PREF, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value!!)
        editor.apply()
    }
}
