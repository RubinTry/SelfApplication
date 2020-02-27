package cn.rubintry.self.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import cn.rubintry.self.R;
import cn.rubintry.self.common.RequestCodeConstants;
import cn.rubintry.self.common.base.BaseActivity;
import cn.rubintry.self.common.manager.LoginManager;
import cn.rubintry.self.model.LoginModel;

/**
 * @author logcat
 */
public class LoginActivity extends BaseActivity {


    @BindView(R.id.edtUsername)
    EditText edtUsername;

    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @Override
    protected boolean lightMode() {
        return true;
    }

    @Override
    protected int attachedLayoutRes() {
        return R.layout.activity_login;
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
    protected void initViews() {
        super.initViews();
    }

    @Override
    protected void requestData() {
        super.requestData();
    }

    @OnClick({R.id.tvLogin , R.id.tvRegister})
    void onClick(View view){
        KeyboardUtils.hideSoftInput(this);
        switch (view.getId()){
            case R.id.tvLogin:
                apiPresenter.login(edtUsername.getText().toString() , edtPassword.getText().toString() , RequestCodeConstants.LOGIN);
                break;
            case R.id.tvRegister:
                startActivity(new Intent(this , RegisterActivity.class));
                break;

                default:
                    break;
        }
    }

    @Override
    public void onNext(Object o, int requestCode) {
        super.onNext(o, requestCode);
        switch (requestCode){
            case RequestCodeConstants.LOGIN:
                LoginModel loginModel = (LoginModel) o;
                ToastUtils.showShort("登录成功");
                boolean successSave = LoginManager.getInstance().setLoginInfo(loginModel);
                if(successSave){
                    startActivity(new Intent(this , MainActivity.class));
                    finish();
                }else{
                    ToastUtils.showShort("用户信息存储失败");
                }
                break;
                default:
                    break;
        }
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
