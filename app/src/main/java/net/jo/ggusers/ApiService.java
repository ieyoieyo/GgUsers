package net.jo.ggusers;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Yusuf on 09.10.2016.
 */

public interface ApiService {

    @GET("users")
    Call<List<UserBean>> getUsers(@Query("since") int since, @Query("per_page") int per_page);

    @GET("users/{username}")
    Call<UserName> getUserName(@Path("username") String username);

//    @GET("search/users")
//    Call<User> getGithubUser(@Query("q") String name);

}
