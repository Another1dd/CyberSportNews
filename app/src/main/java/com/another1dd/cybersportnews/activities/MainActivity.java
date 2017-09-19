package com.another1dd.cybersportnews.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.another1dd.cybersportnews.fragments.RecyclerViewFragment;
import com.github.florent37.materialviewpager.MaterialViewPager;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    MaterialViewPager materialViewPager;
    LinkedHashMap<String[], Boolean> tabs = new LinkedHashMap<>();
    final static String DOTA_IC = "Dota 2";
    final static String CSGO_IC = "Counter-Strike GO";
    final static String HEARTHSTONE_IC = "Hearthstone";
    final static String LOL_IC = "League Of Legends";
    final static String WOT_IC = "World Of Tanks";
    final static String HOTS_IC = "Heroes Of The Storm";
    final static String STARCRAFT_IC = "Starcraft 2";
    final static String OVERWATCH_IC = "Overwatch";
    final static String OTHER_IC = "Другое";
    final static String LIFE_IC = "Life";
    final static String[] DOTA = new String[]{"http://www.cybersport.ru/news/?game=21&MUL_MODE=", DOTA_IC};
    final static String[] CSGO = new String[]{"http://www.cybersport.ru/news/?game=19&MUL_MODE=", CSGO_IC};
    final static String[] HEARTHSTONE = new String[]{"http://www.cybersport.ru/news/?game=86659&MUL_MODE=", HEARTHSTONE_IC};
    final static String[] LOL = new String[]{"http://www.cybersport.ru/news/?game=23955&MUL_MODE=", LOL_IC};
    final static String[] WOT = new String[]{"http://www.cybersport.ru/news/?game=22&MUL_MODE=", WOT_IC};
    final static String[] HOTS = new String[]{"http://www.cybersport.ru/news/?game=108725&MUL_MODE=", HOTS_IC};
    final static String[] STARCRAFT = new String[]{"http://www.cybersport.ru/news/?game=23677&MUL_MODE=", STARCRAFT_IC};
    final static String[] OVERWATCH = new String[]{"http://www.cybersport.ru/news/?game=134502&MUL_MODE=", OVERWATCH_IC};
    final static String[] OTHER = new String[]{"http://www.cybersport.ru/news/?game=41377&MUL_MODE=", OTHER_IC};
    final static String[] LIFE = new String[]{"http://www.cybersport.ru/news/?game=102405&MUL_MODE=", LIFE_IC};
    final static String APP_PREF = "spref_tabs";
    int tabCount = 0;
    SharedPreferences sharedPreferences;


    final static String MAIN_HTTP = "http://www.cybersport.ru/news/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.another1dd.cybersportnews.R.layout.activity_main);

        sharedPreferences = getSharedPreferences(APP_PREF, MODE_PRIVATE);


        tabs.put(DOTA, sharedPreferences.getBoolean(DOTA_IC, true));
            tabs.put(CSGO, sharedPreferences.getBoolean(CSGO_IC, true));
            tabs.put(HEARTHSTONE, sharedPreferences.getBoolean(HEARTHSTONE_IC, true));
            tabs.put(LOL, sharedPreferences.getBoolean(LOL_IC, true));
            tabs.put(WOT, sharedPreferences.getBoolean(WOT_IC, false));
            tabs.put(HOTS, sharedPreferences.getBoolean(HOTS_IC, false));
            tabs.put(STARCRAFT, sharedPreferences.getBoolean(STARCRAFT_IC, false));
            tabs.put(OVERWATCH, sharedPreferences.getBoolean(OVERWATCH_IC, false));
            tabs.put(OTHER, sharedPreferences.getBoolean(OTHER_IC, false));
            tabs.put(LIFE, sharedPreferences.getBoolean(LIFE_IC, false));

        tabCount = tabs.size();

        materialViewPager = findViewById(com.another1dd.cybersportnews.R.id.materialViewPager);

        Toolbar toolbar = materialViewPager.getToolbar();

        if (toolbar != null) {
            setSupportActionBar(toolbar);

            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setHomeButtonEnabled(false);
        }

        updateMaterialViewPager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(com.another1dd.cybersportnews.R.menu.menu_main, menu);
        MenuItem dota_menu = menu.findItem(com.another1dd.cybersportnews.R.id.dota_menu);
        MenuItem csgo_menu = menu.findItem(com.another1dd.cybersportnews.R.id.csgo_menu);
        MenuItem heathstone_menu = menu.findItem(com.another1dd.cybersportnews.R.id.hearthstone_menu);
        MenuItem lol_menu = menu.findItem(com.another1dd.cybersportnews.R.id.lol_menu);
        MenuItem wot_menu = menu.findItem(com.another1dd.cybersportnews.R.id.wot_menu);
        MenuItem hots_menu = menu.findItem(com.another1dd.cybersportnews.R.id.hots_menu);
        MenuItem sc2_menu = menu.findItem(com.another1dd.cybersportnews.R.id.sc2_menu);
        MenuItem overwatch_menu = menu.findItem(com.another1dd.cybersportnews.R.id.overwatch_menu);
        MenuItem other_menu = menu.findItem(com.another1dd.cybersportnews.R.id.other_menu);
        MenuItem life_menu = menu.findItem(com.another1dd.cybersportnews.R.id.life_menu);

        if (tabs.get(DOTA)) {
            dota_menu.setChecked(true);
        } else {
            dota_menu.setChecked(false);
        }

        if (tabs.get(CSGO)) {
            csgo_menu.setChecked(true);
        } else {
            csgo_menu.setChecked(false);
        }

        if (tabs.get(HEARTHSTONE)) {
            heathstone_menu.setChecked(true);
        } else {
            heathstone_menu.setChecked(false);
        }

        if (tabs.get(LOL)) {
            lol_menu.setChecked(true);
        } else {
            lol_menu.setChecked(false);
        }

        if (tabs.get(WOT)) {
            wot_menu.setChecked(true);
        } else {
            wot_menu.setChecked(false);
        }

        if (tabs.get(HOTS)) {
            hots_menu.setChecked(true);
        } else {
            hots_menu.setChecked(false);
        }

        if (tabs.get(STARCRAFT)) {
            sc2_menu.setChecked(true);
        } else {
            sc2_menu.setChecked(false);
        }

        if (tabs.get(OVERWATCH)) {
            overwatch_menu.setChecked(true);
        } else {
            overwatch_menu.setChecked(false);
        }

        if (tabs.get(OTHER)) {
            other_menu.setChecked(true);
        } else {
            other_menu.setChecked(false);
        }

        if (tabs.get(LIFE)) {
            life_menu.setChecked(true);
        } else {
            life_menu.setChecked(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case com.another1dd.cybersportnews.R.id.dota_menu:
                if (item.isChecked()) {
                    item.setChecked(false);
                    tabs.put(DOTA, false);
                    SavePreferences(DOTA_IC, false);
                } else {
                    item.setChecked(true);
                    tabs.put(DOTA, true);
                    SavePreferences(DOTA_IC, true);
                }
                updateMaterialViewPager();
                return true;
            case com.another1dd.cybersportnews.R.id.csgo_menu:
                if (item.isChecked()) {
                    item.setChecked(false);
                    tabs.put(CSGO, false);
                    SavePreferences(CSGO_IC, false);
                } else {
                    item.setChecked(true);
                    tabs.put(CSGO, true);
                    SavePreferences(CSGO_IC, true);
                }
                updateMaterialViewPager();
                return true;
            case com.another1dd.cybersportnews.R.id.hearthstone_menu:
                if (item.isChecked()) {
                    item.setChecked(false);
                    tabs.put(HEARTHSTONE, false);
                    SavePreferences(HEARTHSTONE_IC, false);
                } else {
                    item.setChecked(true);
                    tabs.put(HEARTHSTONE, true);
                    SavePreferences(HEARTHSTONE_IC, true);
                }
                updateMaterialViewPager();
                return true;
            case com.another1dd.cybersportnews.R.id.lol_menu:
                if (item.isChecked()) {
                    item.setChecked(false);
                    tabs.put(LOL, false);
                    SavePreferences(LOL_IC, false);
                } else {
                    item.setChecked(true);
                    tabs.put(LOL, true);
                    SavePreferences(LOL_IC, true);
                }
                updateMaterialViewPager();
                return true;
            case com.another1dd.cybersportnews.R.id.wot_menu:
                if (item.isChecked()) {
                    item.setChecked(false);
                    tabs.put(WOT, false);
                    SavePreferences(WOT_IC, false);
                } else {
                    item.setChecked(true);
                    tabs.put(WOT, true);
                    SavePreferences(WOT_IC, true);
                }
                updateMaterialViewPager();
                return true;
            case com.another1dd.cybersportnews.R.id.hots_menu:
                if (item.isChecked()) {
                    item.setChecked(false);
                    tabs.put(HOTS, false);
                    SavePreferences(HOTS_IC, false);
                } else {
                    item.setChecked(true);
                    tabs.put(HOTS, true);
                    SavePreferences(HOTS_IC, true);
                }
                updateMaterialViewPager();
                return true;
            case com.another1dd.cybersportnews.R.id.sc2_menu:
                if (item.isChecked()) {
                    item.setChecked(false);
                    tabs.put(STARCRAFT, false);
                    SavePreferences(STARCRAFT_IC, false);
                } else {
                    item.setChecked(true);
                    tabs.put(STARCRAFT, true);
                    SavePreferences(STARCRAFT_IC, true);
                }
                updateMaterialViewPager();
                return true;
            case com.another1dd.cybersportnews.R.id.overwatch_menu:
                if (item.isChecked()) {
                    item.setChecked(false);
                    tabs.put(OVERWATCH, false);
                    SavePreferences(OVERWATCH_IC, false);
                } else {
                    item.setChecked(true);
                    tabs.put(OVERWATCH, true);
                    SavePreferences(OVERWATCH_IC, true);
                }
                updateMaterialViewPager();
                return true;
            case com.another1dd.cybersportnews.R.id.other_menu:
                if (item.isChecked()) {
                    item.setChecked(false);
                    tabs.put(OTHER, false);
                    SavePreferences(OTHER_IC, false);
                } else {
                    item.setChecked(true);
                    tabs.put(OTHER, true);
                    SavePreferences(OTHER_IC, true);
                }
                updateMaterialViewPager();
                return true;
            case com.another1dd.cybersportnews.R.id.life_menu:
                if (item.isChecked()) {
                    item.setChecked(false);
                    tabs.put(LIFE, false);
                    SavePreferences(LIFE_IC, false);
                } else {
                    item.setChecked(true);
                    tabs.put(LIFE, true);
                    SavePreferences(LIFE_IC, true);
                }
                updateMaterialViewPager();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }




    private void updateMaterialViewPager() {
        final ArrayList<String[]> arrayList = new ArrayList<>();

        for (Map.Entry<String[], Boolean> map : tabs.entrySet()) {
            if (map.getValue()) {
                arrayList.add(map.getKey());
            }
        }


        tabCount = arrayList.size();


        materialViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return RecyclerViewFragment.newInstance(arrayList.get(0)[0]);
                    case 1:
                        return RecyclerViewFragment.newInstance(arrayList.get(1)[0]);
                    case 2:
                        return RecyclerViewFragment.newInstance(arrayList.get(2)[0]);
                    case 3:
                        return RecyclerViewFragment.newInstance(arrayList.get(3)[0]);
                    case 4:
                        return RecyclerViewFragment.newInstance(arrayList.get(4)[0]);
                    case 5:
                        return RecyclerViewFragment.newInstance(arrayList.get(5)[0]);
                    case 6:
                        return RecyclerViewFragment.newInstance(arrayList.get(6)[0]);
                    case 7:
                        return RecyclerViewFragment.newInstance(arrayList.get(7)[0]);
                    case 8:
                        return RecyclerViewFragment.newInstance(arrayList.get(8)[0]);
                    case 9:
                        return RecyclerViewFragment.newInstance(arrayList.get(9)[0]);
                    default:
                        return RecyclerViewFragment.newInstance(MAIN_HTTP);
                }


            }

            @Override
            public int getCount() {
                return tabCount;
            }


            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return arrayList.get(0)[1];
                    case 1:
                        return arrayList.get(1)[1];
                    case 2:
                        return arrayList.get(2)[1];
                    case 3:
                        return arrayList.get(3)[1];
                    case 4:
                        return arrayList.get(4)[1];
                    case 5:
                        return arrayList.get(5)[1];
                    case 6:
                        return arrayList.get(6)[1];
                    case 7:
                        return arrayList.get(7)[1];
                    case 8:
                        return arrayList.get(8)[1];
                    case 9:
                        return arrayList.get(9)[1];
                    default:
                        return "Page " + position;
                }
            }
        });


        materialViewPager.getViewPager().setOffscreenPageLimit(tabCount);

        materialViewPager.getPagerTitleStrip().setViewPager(materialViewPager.getViewPager());
    }

    private void SavePreferences(String key, Boolean value) {
        sharedPreferences = getSharedPreferences(APP_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }



}