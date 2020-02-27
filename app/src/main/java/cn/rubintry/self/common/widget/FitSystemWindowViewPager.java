package cn.rubintry.self.common.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;

/**
 * @author logcat
 * 解决fragment无法fitSystemWindow的问题
 */
public class FitSystemWindowViewPager extends ViewPager {
    public FitSystemWindowViewPager(@NonNull Context context) {
        super(context);
    }

    public FitSystemWindowViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public WindowInsets dispatchApplyWindowInsets(WindowInsets insets) {
        WindowInsets result = super.dispatchApplyWindowInsets(insets);
        if (!insets.isConsumed()) {
            final int count = getChildCount();
            for (int i = 0; i < count; i++)
                result = getChildAt(i).dispatchApplyWindowInsets(insets);
        }
        return result;
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        ViewCompat.requestApplyInsets(child);
    }
}
