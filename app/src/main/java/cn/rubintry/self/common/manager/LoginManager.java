package cn.rubintry.self.common.manager;

import com.orhanobut.hawk.Hawk;

import cn.rubintry.self.common.constants.KeyConstants;
import cn.rubintry.self.model.LoginModel;

public class LoginManager {
    private static final class Singleton{
        private static final LoginManager INSTANCE = new LoginManager();
    }

    public static LoginManager getInstance(){
        return Singleton.INSTANCE;
    }

    /**
     * 存储登录信息
     * @param loginInfo
     */
    public boolean setLoginInfo(LoginModel loginInfo){
        if(Hawk.contains(KeyConstants.LOGIN_INFO)){
            Hawk.delete(KeyConstants.LOGIN_INFO);
        }
        return Hawk.put(KeyConstants.LOGIN_INFO , loginInfo);
    }

    /**
     * 获取登录信息
     * @return
     */
    public LoginModel getLoginInfo(){
        LoginModel loginModel = Hawk.get(KeyConstants.LOGIN_INFO);
        return loginModel;
    }

    /**
     * 直接获取用户名
     * @return
     */
    public String getUsername(){
        return getLoginInfo() == null ? "" : getLoginInfo().getUsername();
    }
}
