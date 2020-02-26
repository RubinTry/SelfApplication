package cn.rubintry.self.common.base;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.trello.rxlifecycle3.LifecycleProvider;
import com.trello.rxlifecycle3.LifecycleTransformer;
import com.trello.rxlifecycle3.RxLifecycle;
import com.trello.rxlifecycle3.android.ActivityEvent;
import com.trello.rxlifecycle3.android.RxLifecycleAndroid;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * 绑定生命周期，与网络请求有关（基于RxJava的第三方库）
 * @author logcat
 */
public class RxBaseActivity extends AppCompatActivity implements LifecycleProvider<ActivityEvent> {
    public final BehaviorSubject<ActivityEvent> lifeCycleSubject = BehaviorSubject.create();



    @NonNull
    @Override
    public Observable lifecycle() {
        return lifeCycleSubject.hide();
    }

    @NonNull
    @Override
    public LifecycleTransformer bindToLifecycle() {
        return RxLifecycleAndroid.bindActivity(lifeCycleSubject);
    }

    @NonNull
    @Override
    public LifecycleTransformer bindUntilEvent(@NonNull ActivityEvent event) {
        return RxLifecycle.bindUntilEvent(lifeCycleSubject , event);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        lifeCycleSubject.onNext(ActivityEvent.CREATE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        lifeCycleSubject.onNext(ActivityEvent.START);
    }

    @Override
    protected void onPause() {
        super.onPause();
        lifeCycleSubject.onNext(ActivityEvent.PAUSE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        lifeCycleSubject.onNext(ActivityEvent.STOP);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lifeCycleSubject.onNext(ActivityEvent.DESTROY);
    }

    @Override
    protected void onResume() {
        super.onResume();
        lifeCycleSubject.onNext(ActivityEvent.RESUME);
    }
}
