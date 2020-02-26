package cn.rubintry.self.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import cn.rubintry.self.R;
import cn.rubintry.self.common.RequestCodeConstants;
import cn.rubintry.self.common.base.BaseActivity;
import cn.rubintry.self.common.utils.PasswordUtils;

/**
 * @author logcat
 */
public class RegisterActivity extends BaseActivity {


    @BindView(R.id.edtUsername)
    EditText edtUsername;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.edtPasswordAgain)
    EditText edtPasswordAgain;
    @Override
    protected boolean lightMode() {
        return true;
    }

    @Override
    protected boolean showBackBtn() {
        return true;
    }

    @Override
    protected String setTitleString() {
        return "注册";
    }

    @Override
    protected int attachedLayoutRes() {
        return R.layout.activity_register;
    }

    @Override
    protected void initViews() {
        super.initViews();
    }

    @Override
    protected void requestData() {
        super.requestData();
    }


    @OnClick({R.id.tvRegister})
    void onClick(View view){
        switch (view.getId()){
            case R.id.tvRegister:
                if(checkInfo()){
                    apiPresenter.register(edtUsername.getText().toString() , edtPassword.getText().toString() , RequestCodeConstants.REGISTER);
                }
                break;
        }
    }

    private boolean checkInfo() {
        if(TextUtils.isEmpty(edtUsername.getText().toString())){
            ToastUtils.showShort("用户名不能为空");
            return false;
        }else if(TextUtils.isEmpty(edtPassword.getText().toString())){
            ToastUtils.showShort("密码不能为空");
            return false;
        }else if(TextUtils.isEmpty(edtPasswordAgain.getText().toString())){
            ToastUtils.showShort("请再次输入密码");
            return false;
        }else if(!TextUtils.equals(edtPassword.getText().toString() , edtPasswordAgain.getText().toString())){
            ToastUtils.showShort("两次密码不匹配");
            return false;
        }else if(!PasswordUtils.isStrongPassword(edtPassword.getText().toString())){
            ToastUtils.showShort("密码强度低");
            return false;
        }else if(!RegexUtils.isMobileSimple(edtUsername.getText().toString())){
            ToastUtils.showShort("手机号格式不正确");
            return false;
        }
        return true;
    }



    @Override
    public void onNext(Object o, int requestCode) {
        super.onNext(o, requestCode);
        switch (requestCode){
            case RequestCodeConstants.REGISTER:
                ToastUtils.showShort("注册成功");
                finish();
                break;
                default:
                    break;
        }
    }

    @Override
    public void onError(Throwable e, int request) {
        super.onError(e, request);
    }
}
