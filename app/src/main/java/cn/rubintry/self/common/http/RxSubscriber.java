package cn.rubintry.self.common.http;

import android.content.Context;
import android.text.TextUtils;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.io.IOException;

import cn.rubintry.self.common.http.widgets.LoadingDialog;
import cn.rubintry.self.model.BaseModel;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * @author logcat
 */
public abstract class RxSubscriber<T> implements Observer<BaseModel<T>> {
    private String TAG = this.getClass().getSimpleName();
    private LoadingDialog loadingDialog;
    private Disposable disposable;
    private Context context;
    private String errorMsg;
    private boolean showDialog;

    public RxSubscriber(Context context) {
        this(context, true);
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(context);
        }
    }

    public RxSubscriber(Context context, boolean showDialog) {
        this.context = context;
        this.showDialog = showDialog;
    }

    @Override
    public void onSubscribe(Disposable d) {
        disposable = new CompositeDisposable();
        if (showDialog) {
            showLoading();
        }
    }

    private void showLoading() {
        loadingDialog.show();
    }

    @Override
    public void onNext(BaseModel<T> tBaseModel) {
        dismissDialog();
        if(!tBaseModel.isSuccess()){
            onError(new ApiException(tBaseModel.getMessage()));
            return;
        }
        onSuccess(tBaseModel.getData());
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }


    /**
     * api调用成功并有返回值
     *
     * @param t
     */
    public abstract void onSuccess(T t);


    /**
     * api调用失败并抛出异常
     *
     * @param e
     */
    public abstract void onFail(Throwable e);

    @Override
    public void onError(Throwable e) {
        if (e instanceof IOException) {
            errorMsg = "网络异常，请检查";
        } else if (e instanceof HttpException) {
            /** 网络异常，http 请求失败，即 http 状态码不在 [200, 300) 之间, such as: "server internal error". */
            errorMsg = ((HttpException) e).response().message();
        } else if (e instanceof ApiException) {
            /** 网络正常，http 请求成功，服务器返回逻辑错误 */
            errorMsg = ((ApiException) e).getMessage();
        } else {
            /** 其他未知错误 */
            errorMsg = !TextUtils.isEmpty(e.getMessage()) ? e.getMessage() : "unknown error";
        }

        ToastUtils.showShort(errorMsg);
        dismissDialog();
        onFail(e);


        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }


    private void dismissDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            //cancel方法中包含了dismiss()
            loadingDialog.cancel();
            loadingDialog = null;
        }
    }

    @Override
    public void onComplete() {

    }
}
