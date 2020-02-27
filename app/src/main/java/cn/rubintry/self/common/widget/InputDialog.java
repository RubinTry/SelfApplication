package cn.rubintry.self.common.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.rubintry.self.R;

/**
 * @author logcat
 * 输入框弹窗
 */
public class InputDialog extends AlertDialog {

    @BindView(R.id.tvInputDialogTitle)
    TextView tvInputDialogTitle;
    @BindView(R.id.edtInputDialogContent)
    EditText edtInputDialogContent;

    private OnButtonClickListener onButtonClickListener;
    private String title;
    protected InputDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public InputDialog(Builder builder) {
        super(builder.contextWeakReference.get(), true, new OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {

            }
        });
        this.onButtonClickListener = builder.onButtonClickListener;
        this.title = builder.title;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_input);
        ButterKnife.bind(this);
        if(!TextUtils.isEmpty(title)){
            tvInputDialogTitle.setText(title);
        }

    }


    @OnClick({R.id.tvBtnInputDialogConfirm , R.id.tvBtnInputDialogCancel})
    void onClick(View view){
        switch (view.getId()){
            case R.id.tvBtnInputDialogCancel:
                if(onButtonClickListener != null){
                    onButtonClickListener.onCancelClick();
                }
                this.cancel();
                break;
            case R.id.tvBtnInputDialogConfirm:
                if(TextUtils.isEmpty(edtInputDialogContent.getText().toString())){
                    ToastUtils.showShort("请先输入内容");
                    return;
                }
                if(onButtonClickListener != null){
                    onButtonClickListener.onConfirmClick(edtInputDialogContent.getText().toString());
                }
                edtInputDialogContent.setText("");
                this.cancel();
                break;
                default:
                    break;
        }
    }

    @Override
    public void show() {
        super.show();
        // 接着清除flags
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        // 然后弹出输入法
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setGravity(Gravity.CENTER);
        //调整窗体大小
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = SizeUtils.dp2px(315);
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
        getWindow().setBackgroundDrawableResource(R.drawable.shape_input_dialog);
    }

    public static class Builder{
        private WeakReference<Context> contextWeakReference;
        private OnButtonClickListener onButtonClickListener;
        private String title;
        public Builder(Context context) {
            this.contextWeakReference = new WeakReference<>(context);
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
            this.onButtonClickListener = onButtonClickListener;
            return this;
        }


        public InputDialog build(){
            return new InputDialog(this);
        }
    }

    public interface OnButtonClickListener{
        void onConfirmClick(String content);
        void onCancelClick();
    }
}
