package com.example.musicstore.rest;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestInstance {
    public static final String BASE_URL_API="https://itunes.apple.com/";
    public static Retrofit retrofit = null;

    public static IApiItunes getApi(){
        Log.e("getapi","okkkk");
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient.Builder hBuilder = new OkHttpClient.Builder();

        hBuilder.readTimeout(60, TimeUnit.SECONDS);
        hBuilder.connectTimeout(60,TimeUnit.SECONDS);
        hBuilder.writeTimeout(60,TimeUnit.SECONDS);
        ConnectionPool pool = new ConnectionPool(5, 1, TimeUnit.MINUTES);

        hBuilder.connectionPool(pool);

        hBuilder.addInterceptor(new Interceptor(){
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                okhttp3.Response response = chain.proceed(request);

                if(response.code()==500){

                }

                if(response.code()== 502 ){
                    Log.e("entro","502");
                    JSONObject o= new JSONObject();
                    try {
                        o.put("estado","502");
                        o.put("error","Bad Gateway");
                        o.put("mensaje","SERVICIO NO DISPONIBLE");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    MediaType contentType = response.body().contentType();
                    ResponseBody body = ResponseBody.create(contentType, o.toString());
                    return response.newBuilder().body(body).build();
                }


                return response;
            }
        });


        OkHttpClient client = hBuilder.build();



        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL_API)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            Log.d("getClient",retrofit.toString());
        }

        return retrofit.create(IApiItunes.class);
    }
}
