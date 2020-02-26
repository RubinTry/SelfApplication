package cn.rubintry.self.common.http;


import cn.rubintry.self.model.BaseModel;
import cn.rubintry.self.model.LoginModel;
import io.reactivex.Observable;
import retrofit2.http.POST;
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
    Observable<BaseModel<LoginModel>> login(@Query("username") String username,
                                            @Query("password") String password);


    /**
     * 注册
      * @param username
     * @param password
     * @return
     */
    @POST("/userRegister/submit")
    Observable<BaseModel> register(@Query("username") String username,
                                   @Query("password") String password);


    /**
     * 修改密码
     * @param id
     * @param username
     * @param password
     * @return
     */
    @POST("/user/changePassword")
    Observable<BaseModel> changePassword(@Query("id") int id,
                                         @Query("username") String username,
                                         @Query("password") String password);
}
