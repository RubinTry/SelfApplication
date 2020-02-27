package cn.rubintry.self.common.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.BarUtils;
import com.trello.rxlifecycle3.LifecycleProvider;
import com.trello.rxlifecycle3.LifecycleTransformer;
import com.trello.rxlifecycle3.RxLifecycle;
import com.trello.rxlifecycle3.android.FragmentEvent;
import com.trello.rxlifecycle3.android.RxLifecycleAndroid;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.rubintry.self.R;
import cn.rubintry.self.R2;
import cn.rubintry.self.common.http.presenter.ApiPresenter;
import cn.rubintry.self.common.http.presenter.IBasePresenter;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * @author logcat
 */
public abstract class BaseFragment extends RxBaseFragment implements IBasePresenter {

    @Nullable
    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    private TextView tvBarTitle;
    private ImageView imgToolbarBack;
    private ConstraintLayout clTopContainer;

    protected ApiPresenter apiPresenter;


    private View rootView;

    private AppCompatActivity context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(attachedLayoutRes() , container , false);
        context = (AppCompatActivity) getActivity();
        ButterKnife.bind(this , rootView);
        apiPresenter = new ApiPresenter(context , this);
        initToolbar();
        initViews();
        requestData();
        return rootView;
    }

    @Nullable
    @Override
    public Activity getContext() {
        return context;
    }

    private void initToolbar() {
        if(toolbar != null){
            context.setSupportActionBar(toolbar);
            if(context.getSupportActionBar() != null){
                context.getSupportActionBar().setDisplayShowCustomEnabled(true);
                context.getSupportActionBar().setDisplayShowHomeEnabled(false);
                context.getSupportActionBar().setBackgroundDrawable(null);

                View toolbarView = getLayoutInflater().inflate(R.layout.toolbar_style_layout, null);
                tvBarTitle = toolbarView.findViewById(R.id.tvBarTitle);
                tvBarTitle.setText(setTitleString());
                tvBarTitle.setTextColor(setTitleColor());
                clTopContainer = toolbarView.findViewById(R.id.clTopContainer);
                clTopContainer.setBackground(ContextCompat.getDrawable(context , setTopBarBackground()));
                imgToolbarBack = toolbarView.findViewById(R.id.imgToolbarBack);
                if(showBackBtn()){
                    imgToolbarBack.setVisibility(View.VISIBLE);
                }else{
                    imgToolbarBack.setVisibility(View.GONE);
                }
                imgToolbarBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.finish();
                    }
                });
                Toolbar.LayoutParams toolbarParams = new Toolbar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , BarUtils.getActionBarHeight() + BarUtils.getStatusBarHeight());
                context.getSupportActionBar().setCustomView(toolbarView , toolbarParams);
            }
        }
    }

    /**
     * 设置标题栏背景
     * @return
     */
    protected abstract int setTopBarBackground();

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


    protected abstract int setTitleColor();


    protected int attachedLayoutRes(){
        return 0;
    }

    protected void initViews(){

    }

    protected void requestData(){

    }

    @Override
    public void onNext(Object o, int requestCode) {

    }

    @Override
    public void onError(Throwable e, int request) {

    }
}
