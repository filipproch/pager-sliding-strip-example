package cz.jacktech.pager_sliding_strip_example;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TestAdapter mAdapter;
    private PagerSlidingTabStrip mTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(mAdapter = new TestAdapter(getSupportFragmentManager()));

        // Bind the mTabs to the ViewPager
        mTabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        mTabs.setViewPager(pager);


        mAdapter.addTab("test 1");
        mAdapter.addTab("test 2");
        mAdapter.addTab("test 3");


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.addTab("test "+(mAdapter.getCount()+1));
            }
        });

    }

    private class TestAdapter extends FragmentPagerAdapter {

        private ArrayList<String> tabs = new ArrayList<>();

        public TestAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment frag = new ExampleFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title", (String) getPageTitle(position));
            frag.setArguments(bundle);
            return frag;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs.get(position);
        }

        @Override
        public int getCount() {
            return tabs.size();
        }

        public void addTab(String tab) {
            tabs.add(tab);
            notifyDataSetChanged();
            mTabs.notifyDataSetChanged();
        }

        public void removeTab(int position) {
            tabs.remove(position);
            notifyDataSetChanged();
            mTabs.notifyDataSetChanged();
        }

    }
}
