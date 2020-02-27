package cn.rubintry.self.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.rubintry.self.R;
import cn.rubintry.self.common.base.BaseFragment;
import cn.rubintry.self.common.manager.LoginManager;
import cn.rubintry.self.view.activity.SettingActivity;

/**
 * @author logcat
 * 我的
 */
public class MineFragment extends BaseFragment {
    @BindView(R.id.tvNickName)
    TextView tvNickName;
    @BindView(R.id.tvMobile)
    TextView tvMobile;


    private String mobile;
    @Override
    protected int setTopBarBackground() {
        return 0;
    }

    @Override
    protected boolean showBackBtn() {
        return false;
    }

    @Override
    protected String setTitleString() {
        return "";
    }

    @Override
    protected int setTitleColor() {
        return 0;
    }

    @Override
    protected int attachedLayoutRes() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initViews() {
        super.initViews();

        tvNickName.setText(LoginManager.getInstance().getUsername());
        mobile = LoginManager.getInstance().getLoginInfo().getMobile().substring(0 , 3) + "****" + LoginManager.getInstance().getLoginInfo().getMobile().substring(7 , 11);
        tvMobile.setText(mobile);
    }

    @Override
    protected void requestData() {
        super.requestData();
    }

    @OnClick({R.id.imgSetting})
    void onClick(View view){
        switch (view.getId()){
            case R.id.imgSetting:
                startActivity(new Intent(getContext() , SettingActivity.class));
                break;
                default:
                    break;
        }
    }
}
