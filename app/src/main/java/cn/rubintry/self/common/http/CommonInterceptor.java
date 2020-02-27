package cn.rubintry.self.common.http;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CommonInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();

        HttpUrl.Builder builder = request.url()
                .newBuilder()
                .scheme(request.url().scheme())
                .host(request.url().host());


        Request newRequest = request.newBuilder()
                .method(request.method(), request.body())
                .url(builder.build())
                .addHeader("Authorization" , "APPCODE dee5864c587f46c9a99236de4747b161")
                .build();
        return chain.proceed(newRequest);
    }
}
