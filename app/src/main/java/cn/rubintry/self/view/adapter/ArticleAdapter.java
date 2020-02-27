package cn.rubintry.self.view.adapter;

import android.view.View;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.rubintry.self.R;
import cn.rubintry.self.model.ArticleModel;

/**
 * @author logcat
 * 文章适配器
 */
public class ArticleAdapter extends BaseQuickAdapter<ArticleModel , BaseViewHolder> {
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ArticleAdapter(int layoutResId, @Nullable List<ArticleModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleModel item) {
        helper.setText(R.id.tvArticleTitle , item.getTitle());
        CardView cdArticleContainer = helper.itemView.findViewById(R.id.cdArticleContainer);
        cdArticleContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(item.getContent_url());
                }
            }
        });
    }


    public interface OnItemClickListener{
        void onItemClick(String url);
    }
}
