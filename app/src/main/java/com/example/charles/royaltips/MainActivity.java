package com.example.charles.royaltips;

import android.app.Fragment;
import android.os.Bundle;
import android.app.FragmentManager;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.charles.royaltips.fragment.Page1Fragment;
import com.example.charles.royaltips.fragment.Page2Fragment;
import com.example.charles.royaltips.fragment.Page3Fragment;
import com.example.charles.royaltips.model.Arena;
import com.example.charles.royaltips.model.Card;
import com.example.charles.royaltips.model.Chest;
import com.example.charles.royaltips.model.Home;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();

    private final String[] PAGE_TITLES = new String[] {
            "Page 1",
            "Page 2",
            "Page 3"
    };

    private final Fragment[] PAGES = new Fragment[] {
            new Page1Fragment(),
            new Page2Fragment(),
            new Page3Fragment()
    };

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout();

        //requestHome();
        //requestCards();
        //requestChests();
        //requestArenas();
    }

    void initLayout() {
        // Set the Toolbar as the activity's app bar (instead of the default ActionBar)
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Connect the ViewPager to our custom PageAdapter. The PageAdapter supplies the pages
        // (Fragments) to the ViewPager, which the ViewPager needs to display.
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new MyPageAdapter(getFragmentManager()));

        // Connect the tabs with the ViewPager (the setupWithViewPager method does this for us in
        // both directions, i.e. when a new tab is selected, the ViewPager switches to this page,
        // and when the ViewPager switches to a new page, the corresponding tab is selected)
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * PageAdapter for supplying the viewPager with the pages (fragments) to display.
     */
    class MyPageAdapter extends FragmentPagerAdapter {
        MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PAGES[position];
        }

        @Override
        public int getCount() {
            return PAGES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return PAGE_TITLES[position];
        }
    }

    /**
     * Base route
     */
    void requestHome() {
        Call<Home> call = ApiRequest.instance().getHome();
        call.enqueue(new Callback<Home>() {
            @Override
            public void onResponse(Call<Home> call, Response<Home> response) {

                Log.d("#@@#","onResponse");

            }

            @Override
            public void onFailure(Call<Home> call, Throwable t) {

                Log.d("#@@#","onFailure");
            }
        });
    }


    /**
     * Card List
     */
    void requestCards() {
        Call<List<Card>> call = ApiRequest.instance().getCardList();
        call.enqueue(new Callback<List<Card>>() {
            @Override
            public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {

                Log.d("#@@#","onResponse");

            }

            @Override
            public void onFailure(Call<List<Card>> call, Throwable t) {

                Log.d("#@@#","onFailure");
            }
        });
    }

    /**
     * Chest List
     */
    void requestChests() {
        Call<List<Chest>> call = ApiRequest.instance().getChests();
        call.enqueue(new Callback<List<Chest>>() {
            @Override
            public void onResponse(Call<List<Chest>> call, Response<List<Chest>> response) {

                Log.d("#@@#","onResponse");

            }

            @Override
            public void onFailure(Call<List<Chest>> call, Throwable t) {

                Log.d("#@@#","onFailure");
            }
        });
    }

    /**
     * Arena List
     */
    void requestArenas() {
        Call<List<Arena>> call = ApiRequest.instance().getArenaList();
        call.enqueue(new Callback<List<Arena>>() {
            @Override
            public void onResponse(Call<List<Arena>> call, Response<List<Arena>> response) {

                Log.d("#@@#","onResponse");

            }

            @Override
            public void onFailure(Call<List<Arena>> call, Throwable t) {

                Log.d("#@@#","onFailure");
            }
        });
    }
}
