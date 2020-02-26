package cn.rubintry.self.common.http.presenter;

import android.content.Context;

/**
 * @author logcat
 * presenter的基类
 */
public abstract class BasePresenter <V extends IBasePresenter> {
    protected Context context;
    
    protected V view;

    /**
     * 构造方法让Presenter层持有View层的引用
     * @param context
     * @param view
     */
    public BasePresenter(Context context, V view) {
        this.context = context;
        this.view = view;
    }

    
}
