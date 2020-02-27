package cn.rubintry.self.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import cn.rubintry.self.R;
import cn.rubintry.self.common.RequestCodeConstants;
import cn.rubintry.self.common.base.BaseFragment;
import cn.rubintry.self.common.constants.ExtraConstants;
import cn.rubintry.self.view.adapter.NewsPagerAdapter;

/**
 * @author logcat
 */
public class NewsFragment extends BaseFragment {
    private String TAG = this.getClass().getSimpleName();

    @BindView(R.id.tbMainNews)
    TabLayout tbMainNews;
    @BindView(R.id.vpNews)
    ViewPager vpNews;

    private NewsPagerAdapter newsPagerAdapter;

    private List<Fragment> newsPageList;
    private List<String> titleList;

    /**
     * 类型,,top(头条，默认),shehui(社会),guonei(国内)
     * ,guoji(国际),yule(娱乐),tiyu(体育)junshi(军事)
     * ,keji(科技),caijing(财经),shishang(时尚)
     */
    private Map<String , String> typeMap;
    @Override
    protected int setTopBarBackground() {
        return R.color.commonRed;
    }

    @Override
    protected boolean showBackBtn() {
        return false;
    }

    @Override
    protected String setTitleString() {
        return "新闻";
    }

    @Override
    protected int setTitleColor() {
        return Color.WHITE;
    }

    @Override
    protected int attachedLayoutRes() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initViews() {
        super.initViews();

        initTabs();
    }

    private void initTabs() {
        typeMap = new LinkedHashMap<>();
        typeMap.put("top" , "头条");
        typeMap.put("shehui" , "社会");
        typeMap.put("guonei" , "国内");
        typeMap.put("guoji" , "国际");
        typeMap.put("yule" , "娱乐");
        typeMap.put("tiyu" , "体育");
        typeMap.put("junshi" , "军事");
        typeMap.put("keji" , "科技");
        typeMap.put("caijing" , "财经");
        typeMap.put("shishang" , "时尚");

        newsPageList = new ArrayList<>();
        titleList = new ArrayList<>();

        for (String key : typeMap.keySet()){
            NewsChildFragment newsChildFragment = new NewsChildFragment();
            Bundle bundle = new Bundle();
            bundle.putString(ExtraConstants.NEWS_TYPE , key);
            newsChildFragment.setArguments(bundle);
            newsPageList.add(newsChildFragment);
            titleList.add(typeMap.get(key));
        }
        newsPagerAdapter = new NewsPagerAdapter(getChildFragmentManager() , FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT , newsPageList);
        vpNews.setAdapter(newsPagerAdapter);
        newsPagerAdapter.notifyDataSetChanged();
        vpNews.setOffscreenPageLimit(typeMap.size());
        vpNews.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tbMainNews));
        tbMainNews.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpNews.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        for (int i = 0; i < titleList.size(); i++) {
            if(i == 0){
                tbMainNews.addTab(tbMainNews.newTab().setText(titleList.get(i)) , true);
            }else{
                tbMainNews.addTab(tbMainNews.newTab().setText(titleList.get(i)));
            }
        }
    }

    @Override
    protected void requestData() {
        super.requestData();
    }

    @Override
    public void onResume() {
        super.onResume();
        newsPageList.get(vpNews.getCurrentItem()).onResume();
    }

    @Override
    public void onNext(Object o, int requestCode) {
        super.onNext(o, requestCode);

    }

    @Override
    public void onError(Throwable e, int request) {
        super.onError(e, request);
    }
}
