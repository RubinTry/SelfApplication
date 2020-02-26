package cn.rubintry.self.common.hawk;

import android.util.Log;

public class LogInterceptor implements com.orhanobut.hawk.LogInterceptor {
    @Override
    public void onLog(String message) {
        Log.d("LogInterceptor" , "Hawk:  " + message);
    }
}
