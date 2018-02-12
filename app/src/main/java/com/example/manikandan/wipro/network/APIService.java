package com.example.manikandan.wipro.network;


import com.example.manikandan.wipro.network.model.ArticleAPIResponse;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import timber.log.Timber;

/*Retrofit java interface api service*/
public interface APIService {

    String BASE_URL = "https://dl.dropboxusercontent.com/";
    String END_URL = "s/2iodh4vg0eortkl/facts.json";

    @GET(APIService.END_URL)
    Single<ArticleAPIResponse> getResponse();

    /*Create Http instance for webservice client*/
    class Creator {

        public static HttpLoggingInterceptor logging =
                new HttpLoggingInterceptor(message -> Timber.tag("OkHttp").i(message));

        public static APIService getApiService() {
            OkHttpClient client;
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            client = getDefaultBuilder().addInterceptor(logging).build();

            GsonConverterFactory factory = GsonConverterFactory.create(new Gson());

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(APIService.BASE_URL)
                    .addConverterFactory(factory)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();
            return retrofit.create(APIService.class);
        }

        /*create Http client builder*/
        public static OkHttpClient.Builder getDefaultBuilder() {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(60, TimeUnit.SECONDS);
            builder.readTimeout(60, TimeUnit.SECONDS);
            builder.writeTimeout(60, TimeUnit.SECONDS);
            return builder;
        }
    }
}
