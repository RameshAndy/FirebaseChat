package com.root.ram.chat;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.root.ram.R;
import com.root.ram.chat.Constantvalue.UserDetails;

import java.util.ArrayList;


public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {


    ArrayList<UserDetailsData> userDetailsDatas;

    public UserListAdapter(ArrayList<UserDetailsData> userDetailsDatas) {
        super();
        this.userDetailsDatas = userDetailsDatas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userlistitem, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.userName.setText(userDetailsDatas.get(position).getUserName());
        holder.userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserDetails.Second_User = userDetailsDatas.get(position).getUserName();
                UserDetails.Second_user_id = userDetailsDatas.get(position).getUID();
                v.getContext().startActivity(new Intent(v.getContext(), ChatWithUser.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return userDetailsDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private android.widget.TextView userName;

        public ViewHolder(View itemView) {
            super(itemView);
            this.userName = (TextView) itemView.findViewById(R.id.userName);
        }
    }


}
