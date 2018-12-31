package com.kuanhsien.hellonoodoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.kuanhsien.hellonoodoe.api.NoodoeApi;
import com.kuanhsien.hellonoodoe.api.NoodoeApiInterface;
import com.kuanhsien.hellonoodoe.object.PutUpdateUser;
import com.kuanhsien.hellonoodoe.object.PutUpdateUserResponse;
import com.kuanhsien.hellonoodoe.object.UserInfo;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "NOODOE";
    private static final String MSG = "LoginActivity: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpLoggerClient =  new OkHttpClient.Builder();



        final NoodoeApiInterface service = new Retrofit.Builder()
                .baseUrl(NoodoeApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // <- RxJava2 的 Adapter
                .client(httpLoggerClient.addInterceptor(loggingInterceptor).build()) // 用 OkHttp 的攔截器輸出 Log
                .build()
                .create(NoodoeApiInterface.class);


        Map<String, String> headers = new HashMap<>();

        headers.put(NoodoeApi.HEADER_APP_ID_KEY, NoodoeApi.HEADER_APP_ID_VAL);
        headers.put(NoodoeApi.HEADER_REST_API_KEY_KEY, NoodoeApi.HEADER_REST_API_KEY_VAL);
        headers.put(NoodoeApi.HEADER_REVOCABLE_SESSION_KEY, NoodoeApi.HEADER_REVOCABLE_SESSION_VAL);

        service.login(headers, NoodoeApi.USERNAME, NoodoeApi.PASSWORD)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .flatMap(new Function<UserInfo, ObservableSource<PutUpdateUserResponse>>() {
                    @Override
                    public ObservableSource<PutUpdateUserResponse> apply(UserInfo userInfo) throws Exception {

                        Log.d(TAG, MSG + "flatMap(Function): ");
                        Log.d(TAG, MSG + "objectId = " + userInfo.getObjectId());
                        Log.d(TAG, MSG + "username: " + userInfo.getUsername());
                        Log.d(TAG, MSG + "sessionToken: " + userInfo.getSessionToken());


                        Map<String, String> headerMap = new HashMap<>();

                        headerMap.put(NoodoeApi.HEADER_APP_ID_KEY, NoodoeApi.HEADER_APP_ID_VAL);
                        headerMap.put(NoodoeApi.HEADER_REST_API_KEY_KEY, NoodoeApi.HEADER_REST_API_KEY_VAL);
                        headerMap.put(NoodoeApi.HEADER_SESSION_TOKEN_KEY, userInfo.getSessionToken());
                        headerMap.put(NoodoeApi.HEADER_CONTENT_TYPE_KEY, NoodoeApi.HEADER_CONTENT_TYPE_VAL);

                        PutUpdateUser updateUser = new PutUpdateUser();
                        updateUser.setTimezone(8);

                        return service.putUser(userInfo.getObjectId(), headerMap, updateUser);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PutUpdateUserResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, MSG + "onSubscribe: ");
                    }

                    @Override
                    public void onNext(PutUpdateUserResponse response) {
                        Log.d(TAG, MSG + "onNext: Response = " + response.getUpdatedAt());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, MSG + "onComplete: ");
                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.d(TAG, MSG + "onError: " + Log.getStackTraceString(error));
                    }
                });
    }
}
