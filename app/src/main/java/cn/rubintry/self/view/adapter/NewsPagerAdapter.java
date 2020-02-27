package cn.rubintry.self.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author logcat
 */
public class NewsPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> pageList;

    public NewsPagerAdapter(@NonNull FragmentManager fm, int behavior, List<Fragment> pageList) {
        super(fm, behavior);
        this.pageList = pageList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return pageList.get(position);
    }

    @Override
    public int getCount() {
        return pageList.size();
    }
}
