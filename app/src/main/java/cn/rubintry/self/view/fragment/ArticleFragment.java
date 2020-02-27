package cn.rubintry.self.view.fragment;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.BusUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.rubintry.self.R;
import cn.rubintry.self.common.constants.BusConstants;
import cn.rubintry.self.common.constants.ExtraConstants;
import cn.rubintry.self.common.RequestCodeConstants;
import cn.rubintry.self.common.base.BaseFragment;
import cn.rubintry.self.model.ArticleModel;
import cn.rubintry.self.view.activity.WebViewActivity;
import cn.rubintry.self.view.adapter.ArticleAdapter;

/**
 * @author logcat
 */
public class ArticleFragment extends BaseFragment implements OnRefreshListener {


    private String fieldId;

    @BindView(R.id.rvArticle)
    RecyclerView rvArticle;
    @BindView(R.id.refreshArticle)
    SmartRefreshLayout refreshArticle;
    private ArticleAdapter articleAdapter;
    private List<ArticleModel> articleList;

    @Override
    protected int setTopBarBackground() {
        return 0;
    }

    @Override
    protected boolean showBackBtn() {
        return false;
    }

    @Override
    protected String setTitleString() {
        return "";
    }

    @Override
    protected int setTitleColor() {
        return 0;
    }

    @Override
    protected int attachedLayoutRes() {
        return R.layout.fragment_article;
    }

    @Override
    protected void initViews() {
        super.initViews();
        fieldId = getArguments().getString(ExtraConstants.FIELD_ID);

        rvArticle.setLayoutManager(new LinearLayoutManager(getContext()));
        rvArticle.setNestedScrollingEnabled(false);
        articleList = new ArrayList<>();
        articleAdapter = new ArticleAdapter(R.layout.item_article , articleList);
        rvArticle.setAdapter(articleAdapter);
        articleAdapter.setOnItemClickListener(new ArticleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String url) {
                Intent intent = new Intent(getContext() , WebViewActivity.class);
                intent.putExtra(ExtraConstants.URL , url);
                getContext().startActivity(intent);
            }
        });

        refreshArticle.setOnRefreshListener(this);
    }

    @Override
    protected void requestData() {
        super.requestData();
    }

    @Override
    public void onNext(Object o, int requestCode) {
        super.onNext(o, requestCode);
        refreshArticle.finishRefresh();
        switch (requestCode) {
            case RequestCodeConstants.GET_ARTICLE_BY_FIELD_ID:
                List<ArticleModel> articleModels = (List<ArticleModel>) o;
                articleList.clear();
                if(articleModels != null){
                    for (ArticleModel articleModel : articleModels) {
                        articleList.add(articleModel);
                    }
                    articleAdapter.setNewData(articleList);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onError(Throwable e, int request) {
        super.onError(e, request);
        refreshArticle.finishRefresh();
    }

    @Override
    public void onResume() {
        super.onResume();
        apiPresenter.getArticleByField(fieldId, RequestCodeConstants.GET_ARTICLE_BY_FIELD_ID);

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        apiPresenter.getArticleByField(fieldId, RequestCodeConstants.GET_ARTICLE_BY_FIELD_ID);
        BusUtils.post(BusConstants.REFRESH_FIELD);
    }
}
