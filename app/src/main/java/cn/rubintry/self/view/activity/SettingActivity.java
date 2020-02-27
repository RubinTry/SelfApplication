package cn.rubintry.self.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.orhanobut.hawk.Hawk;

import butterknife.OnClick;
import cn.rubintry.self.R;
import cn.rubintry.self.common.base.BaseActivity;

/**
 * @author logcat
 */
public class SettingActivity extends BaseActivity {


    @Override
    protected boolean lightMode() {
        return true;
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
    protected boolean showBackBtn() {
        return true;
    }

    @Override
    protected String setTitleString() {
        return "设置";
    }

    @Override
    protected int setTitleColor() {
        return 0;
    }

    @Override
    protected int attachedLayoutRes() {
        return R.layout.activity_setting;
    }



    @Override
    protected void initViews() {
        super.initViews();
    }

    @Override
    protected void requestData() {
        super.requestData();
    }

    @OnClick({R.id.tvLogOut})
    void onClick(View view){
        switch (view.getId()){
            case R.id.tvLogOut:
                Hawk.deleteAll();
                ActivityUtils.finishAllActivities();
                startActivity(new Intent(this , LoginActivity.class));
                ToastUtils.showShort("退出成功");
                break;
                default:
                    break;
        }
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
