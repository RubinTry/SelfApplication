package cn.rubintry.self.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.orhanobut.hawk.Hawk;

import cn.rubintry.self.R;
import cn.rubintry.self.common.constants.KeyConstants;
import cn.rubintry.self.common.manager.LoginManager;
import cn.rubintry.self.model.LoginModel;

/**
 * @author logcat
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //如果是第一个启动则跳到加载也
        if(!Hawk.contains(KeyConstants.FIRST_LAUNCH)){
            Hawk.put(KeyConstants.FIRST_LAUNCH , true);
            startActivity(new Intent(this , SplashActivity2.class));
            finish();
            return;
        }
        LoginModel loginInfo = LoginManager.getInstance().getLoginInfo();
        if(loginInfo == null){
            startActivity(new Intent(this , LoginActivity.class));
            finish();
        }else{
            startActivity(new Intent(this , MainActivity.class));
            finish();
        }
    }
}
