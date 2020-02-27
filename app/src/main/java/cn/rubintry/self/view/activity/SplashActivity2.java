package cn.rubintry.self.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import cn.rubintry.self.R;
import cn.rubintry.self.common.base.BaseActivity;
import cn.rubintry.self.common.manager.LoginManager;
import cn.rubintry.self.model.LoginModel;

/**
 * @author logcat
 */
public class SplashActivity2 extends BaseActivity {

    @Override
    protected boolean lightMode() {
        return true;
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
        return R.layout.activity_splash2;
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
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LoginModel loginInfo = LoginManager.getInstance().getLoginInfo();
                if(loginInfo == null){
                    startActivity(new Intent(SplashActivity2.this , LoginActivity.class));
                    finish();
                }else{
                    startActivity(new Intent(SplashActivity2.this , MainActivity.class));
                    finish();
                }
            }
        } , 1500);
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
}
