package cn.rubintry.self.common.http.widgets;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;

import androidx.appcompat.app.AlertDialog;

import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.rubintry.self.R;

/**
 * @author logcat
 */
public class LoadingDialog extends AlertDialog {

    public LoadingDialog(Context context) {
        super(context);
    }

    @BindView(R.id.avi)
    AVLoadingIndicatorView avi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mloading_view);
        ButterKnife.bind(this);
        setCancelable(true);
        setCanceledOnTouchOutside(false);
    }


    @Override
    public void show() {
        super.show();
        avi.show();
        // 接着清除flags
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        // 然后弹出输入法
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setGravity(Gravity.CENTER);
        //调整窗体大小
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        params.dimAmount = 0f;
        getWindow().setAttributes(params);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    @Override
    public void cancel() {
        avi.hide();
        super.cancel();
    }
}
