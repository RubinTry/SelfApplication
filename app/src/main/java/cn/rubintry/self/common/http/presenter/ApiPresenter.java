package cn.rubintry.self.common.http.presenter;

import android.content.Context;

import com.trello.rxlifecycle3.android.ActivityEvent;

import java.util.List;

import cn.rubintry.self.common.http.RetrofitManager;
import cn.rubintry.self.common.http.RxHelper;
import cn.rubintry.self.common.http.RxSubscriber;
import cn.rubintry.self.model.ArticleModel;
import cn.rubintry.self.model.BaseModel;
import cn.rubintry.self.model.FieldModel;
import cn.rubintry.self.model.LoginModel;
import cn.rubintry.self.model.NewsModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiPresenter extends BasePresenter {
    /**
     * 构造方法让Presenter层持有View层的引用
     *
     * @param context
     * @param view
     */
    public ApiPresenter(Context context, IBasePresenter view) {
        super(context, view);
    }


    /**
     * 登录
     * @param username
     * @param password
     * @param requestCode
     */
    public void login(String username, String password, int requestCode) {
        RetrofitManager.getDefault().login(username, password)
                .compose(RxHelper.<BaseModel<LoginModel>>bindToLifeCycle(context, ActivityEvent.PAUSE))
                .subscribe(new RxSubscriber<LoginModel>(context) {

                    @Override
                    public void onSuccess(LoginModel loginModel) {
                        view.onNext(loginModel, requestCode);
                    }

                    @Override
                    public void onFail(Throwable e) {
                        view.onError(e, requestCode);
                    }
                });
    }


    /**
     * 注册
     * @param username
     * @param password
     * @param requestCode
     */
    public void register(String mobile , String username, String password, int requestCode) {
        RetrofitManager.getDefault().register(mobile, password , username)
                .compose(RxHelper.<BaseModel>bindToLifeCycle(context, ActivityEvent.PAUSE))
                .subscribe(new RxSubscriber(context) {
                    @Override
                    public void onSuccess(Object o) {
                        view.onNext(o, requestCode);
                    }

                    @Override
                    public void onFail(Throwable e) {
                        view.onError(e, requestCode);
                    }
                });
    }


    /**
     * 修改密码
     * @param id
     * @param username
     * @param password
     * @param requestCode
     */
    public void changePassword(String id , String username , String password , int requestCode){
        RetrofitManager.getDefault().changePassword(id , username , password)
                .compose(RxHelper.<BaseModel>bindToLifeCycle(context , ActivityEvent.PAUSE))
                .subscribe(new RxSubscriber(context) {
                    @Override
                    public void onSuccess(Object o) {
                        view.onNext(o , requestCode);
                    }

                    @Override
                    public void onFail(Throwable e) {
                        view.onError(e , requestCode);
                    }
                });
    }


    /**
     * 获取当前用户的所有领域
     * @param userId
     * @param requestCode
     */
    public void getAllField(String userId , int requestCode){
        RetrofitManager.getDefault().getAllField(userId)
                .compose(RxHelper.<BaseModel<List<FieldModel>>>bindToLifeCycle(context , ActivityEvent.PAUSE))
                .subscribe(new RxSubscriber<List<FieldModel>>(context , false) {
                    @Override
                    public void onSuccess(List<FieldModel> fieldModels) {
                        view.onNext(fieldModels , requestCode);
                    }

                    @Override
                    public void onFail(Throwable e) {
                        view.onError(e , requestCode);
                    }
                });
    }


    /**
     * 添加新的领域
     * @param field_name
     * @param user_id
     * @param requestCode
     */
    public void addNewField(String field_name , String user_id , int requestCode){
        RetrofitManager.getDefault().addNewField(field_name , user_id)
                .compose(RxHelper.<BaseModel>bindToLifeCycle(context , ActivityEvent.PAUSE))
                .subscribe(new RxSubscriber(context) {
                    @Override
                    public void onSuccess(Object o) {
                        view.onNext(o , requestCode);
                    }

                    @Override
                    public void onFail(Throwable e) {
                        view.onError(e , requestCode);
                    }
                });
    }


    /**
     * 获取领域下的所有文章
     * @param fieldId
     * @param requestCode
     */
    public void getArticleByField(String fieldId , int requestCode){

        RetrofitManager.getDefault().getArticleByField(fieldId)
                .compose(RxHelper.<BaseModel<List<ArticleModel>>>bindToLifeCycle(context , ActivityEvent.PAUSE))
                .subscribe(new RxSubscriber<List<ArticleModel>>(context , false) {
                    @Override
                    public void onSuccess(List<ArticleModel> articleModels) {
                        view.onNext(articleModels , requestCode);
                    }

                    @Override
                    public void onFail(Throwable e) {
                        view.onError(e , requestCode);
                    }
                });
    }


    public void getNews(String type , int requestCode){
        Call<NewsModel> newsCall = RetrofitManager.getDefault("http://topnews.market.alicloudapi.com").getNews(type);
        newsCall.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                view.onNext(response.body() , requestCode);
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                view.onError(t , requestCode);
            }
        });
    }
}
