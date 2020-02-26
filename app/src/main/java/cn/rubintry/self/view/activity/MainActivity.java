package cn.rubintry.self.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.blankj.utilcode.util.AppUtils;

import cn.rubintry.self.R;
import cn.rubintry.self.common.base.BaseActivity;

/**
 * @author logcat
 */
public class MainActivity extends BaseActivity {


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
        return null;
    }

    @Override
    protected int attachedLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        super.initViews();
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
