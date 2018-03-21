package com.byvoid.wanangushi.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.byvoid.wanangushi.R;
import com.byvoid.wanangushi.base.BaseActivity;
import com.byvoid.wanangushi.fragment.TextFragment;
import com.yinglan.alphatabs.AlphaTabsIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author melody
 * @date 2018/3/9
 */
public class HomeActivity extends BaseActivity{

    private ViewPager mViewPager;
    private AlphaTabsIndicator mAlphaTabsIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void findView() {
        super.findView();
        mViewPager = findViewById(R.id.mViewPager);
        mAlphaTabsIndicator = findViewById(R.id.alphaIndicator);
    }

    @Override
    protected void bindData() {
        super.bindData();
        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mainAdapter);
        mViewPager.addOnPageChangeListener(mainAdapter);

        mAlphaTabsIndicator.setViewPager(mViewPager);
        mAlphaTabsIndicator.getTabView(0).showNumber(6);
        mAlphaTabsIndicator.getTabView(1).showNumber(888);
        mAlphaTabsIndicator.getTabView(2).showNumber(88);
        mAlphaTabsIndicator.getTabView(3).showPoint();
    }

    @Override
    protected void setListener() {
        super.setListener();
    }


    private class MainAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

        private List<Fragment> fragments = new ArrayList<>();
        private String[] titles = {"微信", "通讯录", "发现", "我"};

        public MainAdapter(FragmentManager fm) {
            super(fm);
            fragments.add(TextFragment.newInstance(titles[0]));
            fragments.add(TextFragment.newInstance(titles[1]));
            fragments.add(TextFragment.newInstance(titles[2]));
            fragments.add(TextFragment.newInstance(titles[3]));
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (0 == position) {
                mAlphaTabsIndicator.getTabView(0).showNumber(mAlphaTabsIndicator.getTabView(0).getBadgeNumber() - 1);
            } else if (2 == position) {
                mAlphaTabsIndicator.getCurrentItemView().removeShow();
            } else if (3 == position) {
                mAlphaTabsIndicator.removeAllBadge();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
