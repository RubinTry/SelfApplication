package cn.rubintry.self.common.base;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.trello.rxlifecycle3.LifecycleProvider;
import com.trello.rxlifecycle3.LifecycleTransformer;
import com.trello.rxlifecycle3.RxLifecycle;
import com.trello.rxlifecycle3.android.FragmentEvent;
import com.trello.rxlifecycle3.android.RxLifecycleAndroid;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class RxBaseFragment extends Fragment implements LifecycleProvider<FragmentEvent> {
    public final BehaviorSubject<FragmentEvent> lifeCycleSubject = BehaviorSubject.create();



    @NonNull
    @Override
    public Observable lifecycle() {
        return lifeCycleSubject.hide();
    }



    @NonNull
    @Override
    public LifecycleTransformer bindToLifecycle() {
        return RxLifecycleAndroid.bindFragment(lifeCycleSubject);
    }

    @NonNull
    @Override
    public LifecycleTransformer bindUntilEvent(@NonNull FragmentEvent event) {
        return RxLifecycle.bindUntilEvent(lifeCycleSubject , event);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifeCycleSubject.onNext(FragmentEvent.CREATE);
    }

    @Override
    public void onStart() {
        super.onStart();
        lifeCycleSubject.onNext(FragmentEvent.START);
    }

    @Override
    public void onPause() {
        super.onPause();
        lifeCycleSubject.onNext(FragmentEvent.PAUSE);
    }

    @Override
    public void onStop() {
        super.onStop();
        lifeCycleSubject.onNext(FragmentEvent.STOP);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lifeCycleSubject.onNext(FragmentEvent.DESTROY);
    }

    @Override
    public void onResume() {
        super.onResume();
        lifeCycleSubject.onNext(FragmentEvent.RESUME);
    }
}
