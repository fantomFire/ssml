package zhonghuass.ssml.http;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServer {
    @GET("/Api/Register/register")
    Observable<BaseResponse<Void>> toRegist(@Query("mobile") String mPhone,
                                            @Query("password") String mPass, @Query("code") String mCode);
    //获取验证码
    @GET("/Api/Service/getyzm")
    Observable<BaseResponse<Void>> getCode(@Query("mobile") String mPhone,@Query("type") String type);
    @GET("/Api/Login/login")
    Observable<BaseResponse<Void>> toLogin(@Query("username")String mPhone, @Query("code")String mCode);
}