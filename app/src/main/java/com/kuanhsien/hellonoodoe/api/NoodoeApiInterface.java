package com.kuanhsien.hellonoodoe.api;

import com.kuanhsien.hellonoodoe.object.PutUpdateUser;
import com.kuanhsien.hellonoodoe.object.PutUpdateUserResponse;
import com.kuanhsien.hellonoodoe.object.UserInfo;

import io.reactivex.Observable;
import java.util.Map;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NoodoeApiInterface {

    @GET("/api/login/")
    Observable<UserInfo> login(
            @HeaderMap Map<String, String> headerMap,
            @Query("username") String username,
            @Query("password") String password
    );

    // Update User (Need token in header)
    @PUT("/api/users/{objectId}")
    Observable<PutUpdateUserResponse> putUser(
            @Path("objectId") String objectId,
            @HeaderMap Map<String, String> headerMap,
            @Body PutUpdateUser updateData);
}
