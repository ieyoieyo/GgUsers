package net.jo.ggusers;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class Model {

    private ApiService myApi;

    public Model() {
        // Creating an object of our api interface
        myApi = RetroClient.getApiService();
    }

    // Get user-list
    void getData(final ICallback<List<UserBean>> callback) {

        // Calling JSON
        Call<List<UserBean>> call = myApi.getUsers(0, 100);

        // Enqueue Callback will be call when get response...
        call.enqueue(new Callback<List<UserBean>>() {
            @Override
            public void onResponse(Call<List<UserBean>> call, retrofit2.Response<List<UserBean>> response) {

                if (response.isSuccessful()) {

                    callback.onSuccess(response.body());

                } else {

                    callback.onError("Something work wrong");
                }

            }

            @Override
            public void onFailure(Call<List<UserBean>> call, Throwable t) {

                Log.e("__", t.getMessage());
                callback.onFailure("On Failure, " + t.getMessage());
            }
        });

    }

    // Get user profile
    void getSingleUser(String login, final ICallback<UserName> callback) {

        // Calling JSON
        Call<UserName> call = myApi.getUserName(login);

        // Enqueue Callback will be call when get response...
        call.enqueue(new Callback<UserName>() {
            @Override
            public void onResponse(Call<UserName> call, retrofit2.Response<UserName> response) {

                if (response.isSuccessful()) {

                    callback.onSuccess(response.body());

                } else {

                    callback.onError("Something work wrong");
                }

            }

            @Override
            public void onFailure(Call<UserName> call, Throwable t) {

                Log.e("__", t.getMessage());
                callback.onFailure("On Failure, " + t.getMessage());
            }
        });
    }
}
