package com.root.ram.chat;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.root.ram.R;
import com.root.ram.chat.Constantvalue.UserDetails;

import java.util.ArrayList;



public class ChatWithUserAdapter extends RecyclerView.Adapter<ChatWithUserAdapter.Chatholder> {


    ArrayList<ChatMessage> chatMessages;

    @Override
    public Chatholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatlistdata, parent, false);

        Chatholder chatWithLondaAdapter = new Chatholder(view);
        return chatWithLondaAdapter;
    }

    @Override
    public void onBindViewHolder(Chatholder holder, int position) {

        if (chatMessages.get(position).getUserName().equals(UserDetails.Username)) {
            holder.user2.setText(chatMessages.get(position).getText());
            holder.user1.setVisibility(View.GONE);
            holder.user1.setText("");
            holder.user2.setVisibility(View.VISIBLE);
        } else {
            holder.user1.setText(chatMessages.get(position).getText());
            holder.user1.setVisibility(View.VISIBLE);
            holder.user2.setText("");
            holder.user2.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    public ChatWithUserAdapter(ArrayList<ChatMessage> chatMessages) {
        super();
        this.chatMessages = chatMessages;
    }

    public class Chatholder extends RecyclerView.ViewHolder {
        private android.widget.TextView user1;
        private android.widget.TextView user2;

        public Chatholder(View view) {
            super(view);
            this.user2 = (TextView) view.findViewById(R.id.user2);
            this.user1 = (TextView) view.findViewById(R.id.user1);
        }
    }
}
