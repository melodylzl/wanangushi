package com.byvoid.wanangushi.module.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;

import com.byvoid.wanangushi.R;
import com.byvoid.wanangushi.base.BaseActivity;
import com.byvoid.wanangushi.constant.HawkConstants;
import com.byvoid.wanangushi.http.BaseCallBack;
import com.byvoid.wanangushi.http.HttpService;
import com.byvoid.wanangushi.module.setting.fragment.SettingFragment;
import com.byvoid.wanangushi.module.story.fragment.StoryHomeFragment;
import com.byvoid.wanangushi.tinker.model.PatchInfo;
import com.orhanobut.hawk.Hawk;
import com.yinglan.alphatabs.AlphaTabsIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author melody
 * @date 2018/3/9
 */
public class HomeActivity extends BaseActivity{

    @BindView(R.id.mViewPager)
    protected ViewPager mViewPager;
    @BindView(R.id.alphaIndicator)
    protected AlphaTabsIndicator mAlphaTabsIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void bindData() {
        super.bindData();
        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mainAdapter);
        mViewPager.addOnPageChangeListener(mainAdapter);
        mAlphaTabsIndicator.setViewPager(mViewPager);

        getPatchInfo();
    }

    @Override
    protected void setListener() {
        super.setListener();
    }


    private class MainAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

        private List<Fragment> fragments = new ArrayList<>();

        public MainAdapter(FragmentManager fm) {
            super(fm);
            fragments.add(StoryHomeFragment.newInstance());
            fragments.add(SettingFragment.newInstance());
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

    /**
     * 获取补丁包
     */
    public void getPatchInfo(){
        HttpService.getPatchInfo(new BaseCallBack<PatchInfo>() {
            @Override
            public void onSuccess(PatchInfo data, String msg) {
                String patchUrl = data.getUrl();
                if (TextUtils.isEmpty(patchUrl)){
                    return;
                }
                Integer cachePatchUrlHashCode = Hawk.get(HawkConstants.PATCH_KEY);
                if (cachePatchUrlHashCode == null || patchUrl.hashCode() != cachePatchUrlHashCode){
                    HttpService.downloadPatch(getContext(),patchUrl);
                }
            }

            @Override
            public void onFail(String msg, int code) {

            }
        });
    }
}
