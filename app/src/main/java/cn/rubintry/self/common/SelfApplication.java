package cn.rubintry.self.common;

import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.blankj.utilcode.util.Utils;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.NoEncryption;

import cn.rubintry.self.common.hawk.LogInterceptor;

/**
 * @author logcat
 */
public class SelfApplication extends MultiDexApplication {

    private static Context context;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        Hawk.init(this)
                .setLogInterceptor(new LogInterceptor())
                .setEncryption(new NoEncryption())
                .build();
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
