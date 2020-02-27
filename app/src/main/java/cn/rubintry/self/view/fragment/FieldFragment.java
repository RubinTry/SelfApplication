package cn.rubintry.self.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.BusUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.rubintry.self.R;
import cn.rubintry.self.common.constants.BusConstants;
import cn.rubintry.self.common.constants.ExtraConstants;
import cn.rubintry.self.common.RequestCodeConstants;
import cn.rubintry.self.common.base.BaseFragment;
import cn.rubintry.self.common.manager.LoginManager;
import cn.rubintry.self.common.widget.InputDialog;
import cn.rubintry.self.model.FieldModel;
import cn.rubintry.self.view.adapter.FieldPagerAdapter;

/**
 * @author logcat
 */
public class FieldFragment extends BaseFragment {
    @BindView(R.id.tbMainField)
    TabLayout tbMainField;
    @BindView(R.id.vpMainField)
    ViewPager vpMainField;
    private InputDialog inputDialog;
    private List<FieldModel> fieldModels;

    private List<Fragment> pageList;
    private List<String> titleList;
    private FieldPagerAdapter fieldPagerAdapter;

    private boolean firstLoad = true;


    private int lastTabPosition = 0;

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
        return "领域";
    }

    @Override
    protected int setTitleColor() {
        return Color.WHITE;
    }

    @Override
    protected int attachedLayoutRes() {
        return R.layout.fragment_field;
    }

    @Override
    protected void initViews() {
        super.initViews();
        BusUtils.register(this);
        pageList = new ArrayList<>();
        titleList = new ArrayList<>();

        inputDialog = new InputDialog.Builder(getContext())
                .setTitle("添加领域")
                .setOnButtonClickListener(new InputDialog.OnButtonClickListener() {
                    @Override
                    public void onConfirmClick(String content) {
                        apiPresenter.addNewField(content, LoginManager.getInstance().getLoginInfo().getUser_id(), RequestCodeConstants.ADD_NEW_FIELD);
                    }

                    @Override
                    public void onCancelClick() {

                    }
                }).build();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BusUtils.unregister(this);
    }

    @Override
    protected void requestData() {
        super.requestData();
    }


    @Override
    public void onResume() {
        super.onResume();
        if(firstLoad){
            apiPresenter.getAllField(LoginManager.getInstance().getLoginInfo().getUser_id(), RequestCodeConstants.GET_ALL_FIELD);
            firstLoad = false;
        }

    }

    @OnClick({R.id.imgAddField})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgAddField:
                inputDialog.show();
                lastTabPosition = vpMainField.getCurrentItem();
                break;
            default:
                break;
        }
    }

    @Override
    public void onNext(Object o, int requestCode) {
        super.onNext(o, requestCode);
        switch (requestCode) {
            case RequestCodeConstants.GET_ALL_FIELD:
                fieldModels = (List<FieldModel>) o;
                pageList.clear();
                titleList.clear();
                if(fieldModels != null){
                    for (FieldModel fieldModel : fieldModels) {
                        ArticleFragment fragment = new ArticleFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString(ExtraConstants.FIELD_ID, fieldModel.getFieldId());
                        fragment.setArguments(bundle);
                        pageList.add(fragment);
                        titleList.add(fieldModel.getFieldName());
                    }
                    fieldPagerAdapter = new FieldPagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, pageList);
                    vpMainField.setAdapter(fieldPagerAdapter);
                    fieldPagerAdapter.notifyDataSetChanged();
                    vpMainField.setOffscreenPageLimit(fieldModels.size());
                    vpMainField.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tbMainField));
                    tbMainField.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                        @Override
                        public void onTabSelected(TabLayout.Tab tab) {
                            vpMainField.setCurrentItem(tab.getPosition());
                        }

                        @Override
                        public void onTabUnselected(TabLayout.Tab tab) {

                        }

                        @Override
                        public void onTabReselected(TabLayout.Tab tab) {

                        }
                    });

                    if(tbMainField.getTabCount() > 0){
                        tbMainField.removeAllTabs();
                        for (int i = 0; i < titleList.size(); i++) {
                            if (i == 0) {
                                tbMainField.addTab(tbMainField.newTab().setText(titleList.get(i)));
                            } else {
                                tbMainField.addTab(tbMainField.newTab().setText(titleList.get(i)));
                            }
                        }
                        tbMainField.getTabAt(lastTabPosition).select();
                    }else{
                        for (int i = 0; i < titleList.size(); i++) {
                            if (i == 0) {
                                tbMainField.addTab(tbMainField.newTab().setText(titleList.get(i)), true);
                            } else {
                                tbMainField.addTab(tbMainField.newTab().setText(titleList.get(i)));
                            }
                        }
                    }
                }

                break;
            case RequestCodeConstants.ADD_NEW_FIELD:
                ToastUtils.showShort("添加成功");
                apiPresenter.getAllField(LoginManager.getInstance().getLoginInfo().getUser_id(), RequestCodeConstants.GET_ALL_FIELD);

                break;
            default:
                break;
        }
    }

    @Override
    public void onError(Throwable e, int request) {
        super.onError(e, request);
    }


    @BusUtils.Bus(tag = BusConstants.REFRESH_FIELD , threadMode = BusUtils.ThreadMode.MAIN)
    public void refreshField(){
        apiPresenter.getAllField(LoginManager.getInstance().getLoginInfo().getUser_id(), RequestCodeConstants.GET_ALL_FIELD);
    }
}
