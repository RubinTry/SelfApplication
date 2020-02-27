package cn.rubintry.self.common.http;


import java.util.List;

import cn.rubintry.self.model.ArticleModel;
import cn.rubintry.self.model.BaseModel;
import cn.rubintry.self.model.FieldModel;
import cn.rubintry.self.model.LoginModel;
import cn.rubintry.self.model.NewsModel;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author logcat
 */
public interface ApiService {

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @POST("/userLogin/submit")
    Observable<BaseModel<LoginModel>> login(@Query("mobile") String username,
                                            @Query("password") String password);


    /**
     * 注册
      * @param username
     * @param password
     * @return
     */
    @POST("/userRegister/submit")
    Observable<BaseModel> register(@Query("mobile") String mobile,
                                   @Query("password") String password,
                                   @Query("username") String username);


    /**
     * 修改密码
     * @param id
     * @param username
     * @param password
     * @return
     */
    @POST("/user/changePassword")
    Observable<BaseModel> changePassword(@Query("id") String id,
                                         @Query("username") String username,
                                         @Query("password") String password);


    /**
     * 获取所有领域
     * @param user_id
     * @return
     */
    @POST("/field/findFieldById")
    Observable<BaseModel<List<FieldModel>>> getAllField(@Query("user_id") String user_id);


    /**
     * 新增领域
     * @param field_name
     * @param user_id
     * @return
     */
    @POST("/field/addField")
    Observable<BaseModel> addNewField(@Query("field_name") String field_name,
                                      @Query("user_id") String user_id);


    @POST("/article/getArticle")
    Observable<BaseModel<List<ArticleModel>>> getArticleByField(@Query("fieldId") String fieldId);



    @GET("/toutiao/index")
    Call<NewsModel> getNews(@Query("type") String type);

}
