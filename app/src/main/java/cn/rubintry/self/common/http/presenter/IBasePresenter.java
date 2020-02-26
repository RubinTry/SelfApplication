package cn.rubintry.self.common.http.presenter;

/**
 * @author logcat
 * activity 和 apiPresenter的桥梁，打通他们的关系，用来传递数据
 */
public interface IBasePresenter<T> {


    /**
     * 请求成功
     * @param t  response中的数据结构体
     * @param requestCode  请求时的唯一请求码
     */
    void onNext(T t, int requestCode);


    /**
     * 请求失败
     * @param e  抛出的异常
     * @param request  请求时的唯一请求码
     */
    void onError(Throwable e, int request);
}
