package cn.rubintry.self.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import cn.rubintry.self.R;
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
