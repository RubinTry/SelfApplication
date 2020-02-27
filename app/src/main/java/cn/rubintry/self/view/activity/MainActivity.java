package cn.rubintry.self.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.rubintry.self.R;
import cn.rubintry.self.common.base.BaseActivity;
import cn.rubintry.self.common.widget.FitSystemWindowViewPager;
import cn.rubintry.self.view.adapter.MainPageAdapter;
import cn.rubintry.self.view.fragment.FieldFragment;
import cn.rubintry.self.view.fragment.MineFragment;
import cn.rubintry.self.view.fragment.NewsFragment;

/**
 * @author logcat
 */
public class MainActivity extends BaseActivity {


    @BindView(R.id.tbMain)
    TabLayout tbMain;
    @BindView(R.id.vpMain)
    FitSystemWindowViewPager vpMain;
    private MainPageAdapter mainPageAdapter;
    private List<Fragment> pageList;
    private List<String> titleList;
    @Override
    protected boolean lightMode() {
        return false;
    }

    @Override
    protected boolean showBackBtn() {
        return false;
    }

    @Override
    protected String setTitleString() {
        return null;
    }

    @Override
    protected int setTitleColor() {
        return 0;
    }

    @Override
    protected int attachedLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected int setTopBarBackground() {
        return 0;
    }

    @Override
    protected int setBackBtnBackground() {
        return 0;
    }

    @Override
    protected void initViews() {
        super.initViews();
        initTab();
    }

    private void initTab() {
        titleList = new ArrayList<>();
        titleList.add("领域");
        titleList.add("新闻");
        titleList.add("我的");
        pageList = new ArrayList<>();
        pageList.add(new FieldFragment());
        pageList.add(new NewsFragment());
        pageList.add(new MineFragment());
        mainPageAdapter = new MainPageAdapter(getSupportFragmentManager() , FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT , pageList);
        vpMain.setOffscreenPageLimit(3);
        vpMain.setAdapter(mainPageAdapter);
        mainPageAdapter.notifyDataSetChanged();
        vpMain.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tbMain));
        tbMain.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                selectTabView(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                releaseTabView(tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        for (int i = 0; i < titleList.size(); i++) {
            if(i == 0){
                tbMain.addTab(tbMain.newTab().setCustomView(getCustomView(i)) , true);
            }else{
                tbMain.addTab(tbMain.newTab().setCustomView(getCustomView(i)));
            }
        }
    }

    private void releaseTabView(TabLayout.Tab tab) {
        View view = tab.getCustomView();
        ImageView imgHomeTab = view.findViewById(R.id.imgMainTab);
        switch (tab.getPosition()){
            case 0:
                imgHomeTab.setBackground(ContextCompat.getDrawable(this , R.mipmap.area_normal));
                break;
            case 1:
                imgHomeTab.setBackground(ContextCompat.getDrawable(this , R.mipmap.news_normal));
                break;
            case 2:
                imgHomeTab.setBackground(ContextCompat.getDrawable(this , R.mipmap.mine_normal));
                break;

            default:
                break;
        }
        TextView tvHomeTab = view.findViewById(R.id.tvMainTab);
        tvHomeTab.setTextColor(ContextCompat.getColor(this , R.color.txtGray));
    }

    private void selectTabView(TabLayout.Tab tab){
        vpMain.setCurrentItem(tab.getPosition());
        View view = tab.getCustomView();
        ImageView imgHomeTab = view.findViewById(R.id.imgMainTab);
        TextView tvHomeTab = view.findViewById(R.id.tvMainTab);
        switch (tab.getPosition()){
            case 0:
                imgHomeTab.setBackground(ContextCompat.getDrawable(this , R.mipmap.area_select));
                break;
            case 1:
                imgHomeTab.setBackground(ContextCompat.getDrawable(this , R.mipmap.news_select));
                break;
            case 2:
                imgHomeTab.setBackground(ContextCompat.getDrawable(this , R.mipmap.mine_select));
                break;
            default:
                break;
        }
        tvHomeTab.setTextColor(ContextCompat.getColor(this , R.color.mainColor));
    }

    private View getCustomView(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.tab_item , null);
        TextView tvMainTab = view.findViewById(R.id.tvMainTab);
        ImageView imgMainTab = view.findViewById(R.id.imgMainTab);
        tvMainTab.setText(titleList.get(position));
        switch (position){
            case 0:
                imgMainTab.setBackground(ContextCompat.getDrawable(this , R.mipmap.area_normal));
                break;
            case 1:
                imgMainTab.setBackground(ContextCompat.getDrawable(this , R.mipmap.news_normal));
                break;
            case 2:
                imgMainTab.setBackground(ContextCompat.getDrawable(this , R.mipmap.mine_normal));
                break;
                default:
                    break;
        }
        return view;
    }

    @Override
    protected void requestData() {
        super.requestData();
    }

    @Override
    public void onNext(Object o, int requestCode) {
        super.onNext(o, requestCode);
    }

    @Override
    public void onError(Throwable e, int request) {
        super.onError(e, request);
    }

    @Override
    public void onBackPressed() {
        //跳转桌面
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(homeIntent);
    }
}
