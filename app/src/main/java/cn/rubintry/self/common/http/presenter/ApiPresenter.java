package cn.rubintry.self.common.http.presenter;

import android.content.Context;

import com.trello.rxlifecycle3.android.ActivityEvent;

import cn.rubintry.self.common.http.RetrofitManager;
import cn.rubintry.self.common.http.RxHelper;
import cn.rubintry.self.common.http.RxSubscriber;
import cn.rubintry.self.model.BaseModel;
import cn.rubintry.self.model.LoginModel;

public class ApiPresenter extends BasePresenter {
    /**
     * 构造方法让Presenter层持有View层的引用
     *
     * @param context
     * @param view
     */
    public ApiPresenter(Context context, IBasePresenter view) {
        super(context, view);
    }


    /**
     * 登录
     * @param username
     * @param password
     * @param requestCode
     */
    public void login(String username, String password, int requestCode) {
        RetrofitManager.getDefault().login(username, password)
                .compose(RxHelper.<BaseModel<LoginModel>>bindToLifeCycle(context, ActivityEvent.PAUSE))
                .subscribe(new RxSubscriber<LoginModel>(context) {

                    @Override
                    public void onSuccess(LoginModel loginModel) {
                        view.onNext(loginModel, requestCode);
                    }

                    @Override
                    public void onFail(Throwable e) {
                        view.onError(e, requestCode);
                    }
                });
    }


    /**
     * 注册
     * @param username
     * @param password
     * @param requestCode
     */
    public void register(String username, String password, int requestCode) {
        RetrofitManager.getDefault().register(username, password)
                .compose(RxHelper.<BaseModel>bindToLifeCycle(context, ActivityEvent.PAUSE))
                .subscribe(new RxSubscriber(context) {
                    @Override
                    public void onSuccess(Object o) {
                        view.onNext(o, requestCode);
                    }

                    @Override
                    public void onFail(Throwable e) {
                        view.onError(e, requestCode);
                    }
                });
    }


    /**
     * 修改密码
     * @param id
     * @param username
     * @param password
     * @param requestCode
     */
    public void changePassword(int id , String username , String password , int requestCode){
        RetrofitManager.getDefault().changePassword(id , username , password)
                .compose(RxHelper.<BaseModel>bindToLifeCycle(context , ActivityEvent.PAUSE))
                .subscribe(new RxSubscriber(context) {
                    @Override
                    public void onSuccess(Object o) {
                        view.onNext(o , requestCode);
                    }

                    @Override
                    public void onFail(Throwable e) {
                        view.onError(e , requestCode);
                    }
                });
    }
}
