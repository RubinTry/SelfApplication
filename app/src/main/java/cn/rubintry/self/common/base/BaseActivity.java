package cn.rubintry.self.common.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.blankj.utilcode.util.BarUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.rubintry.self.R;
import cn.rubintry.self.R2;
import cn.rubintry.self.common.http.presenter.ApiPresenter;
import cn.rubintry.self.common.http.presenter.IBasePresenter;

/**
 * @author logcat
 */
public abstract class BaseActivity extends RxBaseActivity implements IBasePresenter {

    @Nullable
    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    private TextView tvBarTitle;
    private ImageView imgToolbarBack;


    protected ApiPresenter apiPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //实现沉浸式状态栏
        transparentStatusBar(this.getWindow());
        //状态栏是否为明亮模式
        if(lightMode()){
            BarUtils.setNavBarLightMode(this , true);
        }else{
            BarUtils.setNavBarLightMode(this , false);
        }
        apiPresenter = new ApiPresenter(this , this);
        setContentView(attachedLayoutRes());
        ButterKnife.bind(this);
        initToolbar();
        initViews();
        requestData();
    }

    /**
     * 状态栏是否为明亮模式
     * @return
     */
    protected abstract boolean lightMode();

    protected int attachedLayoutRes(){
        return 0;
    }


    private void initToolbar() {
        if(toolbar != null){
            setSupportActionBar(toolbar);
            if(getSupportActionBar() != null){
                getSupportActionBar().setDisplayShowCustomEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(false);
                getSupportActionBar().setBackgroundDrawable(null);

                View toolbarView = getLayoutInflater().inflate(R.layout.toolbar_style_layout, null);
                tvBarTitle = toolbarView.findViewById(R.id.tvBarTitle);
                tvBarTitle.setText(setTitleString());
                imgToolbarBack = toolbarView.findViewById(R.id.imgToolbarBack);
                if(showBackBtn()){
                    imgToolbarBack.setVisibility(View.VISIBLE);
                }else{
                    imgToolbarBack.setVisibility(View.GONE);
                }
                imgToolbarBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                Toolbar.LayoutParams toolbarParams = new Toolbar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , BarUtils.getActionBarHeight() + BarUtils.getStatusBarHeight());
                getSupportActionBar().setCustomView(toolbarView , toolbarParams);
            }
        }
    }

    /**
     * 是否显示返回键（用toolbar的情况下）
     * @return
     */
    protected abstract boolean showBackBtn();

    /**
     * 设置标题文字
     * @return
     */
    protected abstract String setTitleString();

    protected void initViews(){

    }


    protected void requestData(){

    }


    /**
     * 沉浸式状态栏
     * @param window
     */
    public static void transparentStatusBar(final Window window) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int option = View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            int vis = window.getDecorView().getSystemUiVisibility();
            window.getDecorView().setSystemUiVisibility(option | vis);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @Override
    public void onNext(Object o, int requestCode) {

    }

    @Override
    public void onError(Throwable e, int request) {

    }
}
