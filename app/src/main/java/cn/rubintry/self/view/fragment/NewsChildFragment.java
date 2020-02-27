package cn.rubintry.self.view.fragment;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.rubintry.self.R;
import cn.rubintry.self.common.RequestCodeConstants;
import cn.rubintry.self.common.base.BaseFragment;
import cn.rubintry.self.common.constants.ExtraConstants;
import cn.rubintry.self.model.DataModel;
import cn.rubintry.self.model.NewsModel;
import cn.rubintry.self.view.activity.WebViewActivity;
import cn.rubintry.self.view.adapter.NewsListAdapter;

/**
 * @author logcat
 */
public class NewsChildFragment extends BaseFragment implements OnRefreshListener {

    private static final String TAG = "NewsChildFragment";
    private String type;

    @BindView(R.id.rvNewsList)
    RecyclerView rvNewsList;
    @BindView(R.id.refreshNews)
    SmartRefreshLayout refreshNews;
    private NewsListAdapter newsListAdapter;
    private List<DataModel> newsList;

    private boolean firstLoad = true;


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
        return R.layout.fragment_news_child;
    }

    @Override
    protected void initViews() {
        super.initViews();

        type = getArguments().getString(ExtraConstants.NEWS_TYPE);

        rvNewsList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvNewsList.setNestedScrollingEnabled(false);
        newsList = new ArrayList<>();
        newsListAdapter = new NewsListAdapter(R.layout.item_news , newsList);
        rvNewsList.setAdapter(newsListAdapter);
        newsListAdapter.setOnItemClickListener(new NewsListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DataModel data) {
                Intent intent = new Intent(getContext() , WebViewActivity.class);
                intent.putExtra(ExtraConstants.URL , data.getUrl());
                getContext().startActivity(intent);
            }
        });

        refreshNews.setOnRefreshListener(this);
    }

    @Override
    protected void requestData() {
        super.requestData();
    }

    @Override
    public void onNext(Object o, int requestCode) {
        super.onNext(o, requestCode);
        refreshNews.finishRefresh();
        switch (requestCode){
            case RequestCodeConstants.GET_NEWS:
                NewsModel newsModel = (NewsModel) o;
                List<DataModel> newsLists = newsModel.getResult().getData();
                newsList.clear();
                newsList.addAll(newsLists);
                newsListAdapter.setNewData(newsList);
                Log.d(TAG, "onNext: " + newsModel);
                break;
                default:
                    break;
        }
    }

    @Override
    public void onError(Throwable e, int request) {
        super.onError(e, request);
        refreshNews.finishRefresh();
    }


    @Override
    public void onResume() {
        super.onResume();
        if(firstLoad){
            apiPresenter.getNews(type , RequestCodeConstants.GET_NEWS);
            firstLoad = false;
        }

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        apiPresenter.getNews(type , RequestCodeConstants.GET_NEWS);
    }
}
