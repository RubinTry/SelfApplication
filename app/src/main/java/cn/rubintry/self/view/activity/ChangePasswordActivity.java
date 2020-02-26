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
import cn.rubintry.self.common.manager.LoginManager;
import cn.rubintry.self.common.utils.PasswordUtils;

/**
 * @author logcat
 * 修改密码activity
 */
public class ChangePasswordActivity extends BaseActivity {

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
        return "修改密码";
    }

    @Override
    protected int attachedLayoutRes() {
        return R.layout.activity_change_password;
    }

    @Override
    protected void initViews() {
        super.initViews();
    }

    @Override
    protected void requestData() {
        super.requestData();
    }

    @OnClick({R.id.tvSubmit})
    void onClick(View view){
        switch (view.getId()){
            case R.id.tvSubmit:
                if(checkInfo()){
                    apiPresenter.changePassword(LoginManager.getInstance().getLoginInfo().getId(),
                            LoginManager.getInstance().getUsername() , edtPassword.getText().toString() , RequestCodeConstants.CHANGE_PASSWORD);
                }
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


    private boolean checkInfo() {
        if(TextUtils.isEmpty(edtPassword.getText().toString())){
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
        }
        return true;
    }
}
