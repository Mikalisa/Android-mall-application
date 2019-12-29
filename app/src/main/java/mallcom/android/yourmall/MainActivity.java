package mallcom.android.yourmall;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewOutlineProvider;

import java.util.ArrayList;

import layout.AboutActivity;
import layout.HomeFragment;
import layout.MarketFragment;

/**
 MIT License

 Copyright (c) 2019 Michal Youssef Issa

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.

 */
public class MainActivity extends AppCompatActivity {

    private ArrayList<DataSectionModel> allSampleData;
    private MallFragmentPagerAdapter adapter;

    private ViewOutlineProvider mOutlineProvider;

    TabLayout tabLayout;
    private int[] tabIcons = {
            R.drawable.ic_shopping,
            R.drawable.ic_goods};

    private int SETTINGS_ACTION = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences pref = PreferenceManager
                  .getDefaultSharedPreferences(this);
        String themeName = pref.getString("theme", "Theme1");
        if (themeName.equals("Theme1")) {
            setTheme(R.style.AppTheme);
        } else if (themeName.equals("Theme2")) {
            setTheme(R.style.AppThemeBlue);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page

        setupViewPager(viewPager);

        AppBarLayout appBar = (AppBarLayout) findViewById(R.id.appBar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            appBar.setElevation(0);
        }

        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);

    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new MallFragmentPagerAdapter(getSupportFragmentManager(), MainActivity.this);
        adapter.addFrag(new HomeFragment(), getString(R.string.market));
        adapter.addFrag(new MarketFragment(), getString(R.string.trading));
        viewPager.setAdapter(adapter);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.more, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {


            case R.id.about_buttom:
                startActivity(new Intent(this, AboutActivity.class));
                return true;


        }

        return super.onOptionsItemSelected(item);
    }


}


