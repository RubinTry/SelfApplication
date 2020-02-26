package cn.rubintry.self.common.http;

import android.content.Context;
import com.trello.rxlifecycle3.android.ActivityEvent;

import cn.rubintry.self.common.base.RxBaseActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * rxJava线程调度管理类
 * @author logcat
 */
public class RxHelper {


    public static <T> ObservableTransformer<T , T> bindToLifeCycle(final Context context , final ActivityEvent event){
        ObservableTransformer<T , T> observableTransformer = new ObservableTransformer<T, T>() {

            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                if(context instanceof RxBaseActivity){
                    RxBaseActivity rxBaseActivity = (RxBaseActivity) context;
                    return (ObservableSource<T>)upstream.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .compose(rxBaseActivity.bindUntilEvent(event));
                }
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
        return observableTransformer;
    }
}
