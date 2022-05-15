package net.jo.ggusers;

import android.content.Context;
//import android.support.v7.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Yusuf on 09.10.2016.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>{

    private List<UserBean> userBeanList;
    private Context mContext;

    // Constructor
    public UserAdapter(List<UserBean> userBeanList, Context context) {
        this.userBeanList = userBeanList;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.row_layout, parent, false);
        MyViewHolder vh = new MyViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        UserBean userBean = userBeanList.get(position);

        final String login = userBean.getLogin();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent userIntent = new Intent(mContext, UserActivity.class);
                userIntent.putExtra("login", login);
                mContext.startActivity(userIntent);
            }
        });

        holder.userName.setText(login);

        if (userBean.isSite_admin())
            holder.adminTV.setVisibility(View.VISIBLE);
        else
            holder.adminTV.setVisibility(View.GONE);

        String url = userBean.getAvatar_url();
        Picasso.with(mContext).load(url).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.profileImg);

    }

    @Override
    public int getItemCount() {
        return userBeanList.size();
    }

    // View Holder
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView userName, adminTV;
        public ImageView profileImg;

        public MyViewHolder(View itemView) {
            super(itemView);

            userName = (TextView) itemView.findViewById(R.id.textViewName);
            adminTV = (TextView) itemView.findViewById(R.id.adminTV);
            profileImg = (ImageView) itemView.findViewById(R.id.imageView);

        }
    }



}
