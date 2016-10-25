package com.another1dd.cybersportnews_materialviewpager;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

public class MainActivity extends AppCompatActivity {

    MaterialViewPager materialViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //колличество страниц
        final int tabCount = 4;





        materialViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);



        materialViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.black,
                                getResources().getDrawable(R.drawable.dota2));
                    case 1:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.black,
                                getResources().getDrawable(R.drawable.csgo));
                    case 2:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.black,
                                getResources().getDrawable(R.drawable.lol));
                    case 3:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.black,
                                getResources().getDrawable(R.drawable.hots));
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });


        materialViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
               switch (position)
               {
                   case 0:
                       return RecyclerViewFragment.newInstance("http://www.cybersport.ru/news/?game=21&MUL_MODE=");
                   case 1:
                       return RecyclerViewFragment.newInstance("http://www.cybersport.ru/news/?game=19&MUL_MODE=");
                   case 2:
                       return RecyclerViewFragment.newInstance("http://www.cybersport.ru/news/?game=23955&MUL_MODE=");
                   case 3:
                       return RecyclerViewFragment.newInstance("http://www.cybersport.ru/news/?game=108725&MUL_MODE=");
                    default:
                        return RecyclerViewFragment.newInstance("http://www.cybersport.ru/news/");
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
                        return getResources().getString(R.string.Dota);
                    case 1:
                        return getResources().getString(R.string.CsGo);
                    case 2:
                        return getResources().getString(R.string.LoL);
                    case 3:
                        return getResources().getString(R.string.HoTs);
                    default:
                        return "Page " + position;
                }
            }
        });

        //есть возможность хранить в памяти определенное количество страниц иначе после прокрутки они будут загружаться заново
        materialViewPager.getViewPager().setOffscreenPageLimit(tabCount);

        materialViewPager.getPagerTitleStrip().setViewPager(this.materialViewPager.getViewPager());
    }
}
