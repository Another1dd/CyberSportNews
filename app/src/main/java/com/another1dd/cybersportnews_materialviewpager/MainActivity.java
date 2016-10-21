package com.another1dd.cybersportnews_materialviewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

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




        //le MaterialViewPager
        this.materialViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);



        materialViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.blue,
                                "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2014/06/wallpaper_51.jpg");
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.green,
                                "https://fs01.androidpit.info/a/63/0e/android-l-wallpapers-630ea6-h900.jpg");
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.cyan,
                                "http://www.droid-life.com/wp-content/uploads/2014/10/lollipop-wallpapers10.jpg");
                    case 3:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.red,
                                "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });

        //remplir le ViewPager
        this.materialViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return RecyclerViewFragment.newInstance();
                }
                else return new BlankFragment();
            }

            @Override
            public int getCount() {
                return tabCount;
            }



            //Заголовки наших страник
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
        this.materialViewPager.getViewPager().setOffscreenPageLimit(tabCount);
        //relie les tabs au viewpager
        this.materialViewPager.getPagerTitleStrip().setViewPager(this.materialViewPager.getViewPager());
    }
}
