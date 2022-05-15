package net.jo.ggusers;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class UserActivity extends AppCompatActivity implements IView{

    private ProgressDialog progressDialog;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_name);

        String login = getIntent().getStringExtra("login");

        // Close Button
        ((ImageView)findViewById(R.id.closeBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        progressDialog = new ProgressDialog(this);

        presenter = new Presenter(this);

        if (checkConnection()) {

            // Show dialog
            presenter.showLoading();

            presenter.getSingleUser(login);

        } else {

            showToast("Check Your Internet Connection");

        }
    }

    @Override
    public void showUser(UserName userName) {

        Picasso.with(this).load(userName.getAvatar_url())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into((ImageView)findViewById(R.id.avatar));

        ((TextView)findViewById(R.id.userName)).setText(userName.getName());
        ((TextView)findViewById(R.id.bio)).setText(userName.getBio());
        ((TextView)findViewById(R.id.login)).setText(userName.getLogin());

        // Is STAFF?
        boolean isSite_admin = userName.isSite_admin();
        Log.v("__", userName.getLogin() + " is " + (isSite_admin ? "admin" : "not admin"));
        if (isSite_admin)
            ((TextView)findViewById(R.id.admin)).setVisibility(View.VISIBLE);

        ((TextView)findViewById(R.id.location)).setText(userName.getLocation());
        ((TextView)findViewById(R.id.blog)).setText(userName.getBlog());
    }


    // Check internet Connection
    public boolean checkConnection() {

        ConnectivityManager conMgr = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);

        return conMgr.getActiveNetworkInfo() != null

                && conMgr.getActiveNetworkInfo().isAvailable()

                && conMgr.getActiveNetworkInfo().isConnected();

    }

    @Override
    public void showDialog() {

        progressDialog.setTitle("Connecting");
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
    }

    @Override
    public void hideDialog() {

        progressDialog.dismiss();
    }

    @Override
    public void showList(List<UserBean> data) {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}
