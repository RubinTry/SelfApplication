package cn.rubintry.self.common.http.callback;



import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

/**
 * @author logcat
 */
public class DiffTCallback<T> extends DiffUtil.Callback {
    private List<T> mOldData , mNewData;

    public DiffTCallback(List<T> mOldData, List<T> mNewData) {
        this.mOldData = mOldData;
        this.mNewData = mNewData;
    }


    @Override
    public int getOldListSize() {
        return mOldData != null ? mOldData.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return mNewData != null ? mNewData.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int i, int i1) {
        return mOldData.get(i).equals(mNewData.get(i1));
    }

    @Override
    public boolean areContentsTheSame(int i, int i1) {
        T oldData = mOldData.get(i);
        T newData = mNewData.get(i1);
        if(!oldData.equals(newData)){
            return false;
        }
        if(oldData != newData){
            return false;
        }
        return true;
    }
}
