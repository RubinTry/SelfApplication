package cn.rubintry.self.view.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.rubintry.self.R;
import cn.rubintry.self.common.SelfApplication;
import cn.rubintry.self.common.utils.GlideUtils;
import cn.rubintry.self.model.DataModel;

/**
 * @author logcat
 */
public class NewsListAdapter extends BaseQuickAdapter<DataModel , BaseViewHolder> {
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public NewsListAdapter(int layoutResId, @Nullable List<DataModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DataModel item) {
        helper.setText(R.id.tvNewsTitle , item.getTitle());
        helper.setText(R.id.tvAuthor , "来源：" + item.getAuthor_name());
        helper.setText(R.id.tvDate , "发布时间：" + item.getDate());
        ImageView imgNewsImage = helper.itemView.findViewById(R.id.imgNewsImage);
        GlideUtils.load(SelfApplication.getContext() , item.getThumbnail_pic_s() , imgNewsImage);
        CardView cdNewsContainer = helper.itemView.findViewById(R.id.cdNewsContainer);
        cdNewsContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(item);
                }
            }
        });
    }


    public interface OnItemClickListener{
        void onItemClick(DataModel data);
    }

}
