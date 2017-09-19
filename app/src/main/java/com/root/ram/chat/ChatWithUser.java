package com.root.ram.chat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.root.ram.R;
import com.root.ram.chat.Constantvalue.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class ChatWithUser extends AppCompatActivity {

    private android.support.v7.widget.RecyclerView chatListData;

    DatabaseReference databaseReference1, databaseReference2, databaseReference;
    FirebaseDatabase firebaseDatabase;
    private android.widget.EditText message;
    private android.widget.Button send;
    LinearLayoutManager linearLayoutManager;
    ChatWithUserAdapter chatWithUserAdapter;
    ArrayList<ChatMessage> chatMessages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_with_londa);

        this.send = (Button) findViewById(R.id.send);
        this.message = (EditText) findViewById(R.id.message);
        this.chatListData = (RecyclerView) findViewById(R.id.chatListData);

        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        chatListData.setLayoutManager(linearLayoutManager);
        chatListData.setHasFixedSize(true);
        chatWithUserAdapter = new ChatWithUserAdapter(chatMessages);
        chatListData.setAdapter(chatWithUserAdapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });


        String table=createChatId(createChatId(UserDetails.UserID+UserDetails.Second_user_id));
        Log.d("getValue====",table);

        databaseReference = FirebaseDatabase.getInstance().getReference("Message").child(table);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatMessage chatMessage = dataSnapshot.getValue(ChatMessage.class);
                chatMessages.add(chatMessage);
                chatListData.scrollToPosition(chatWithUserAdapter.getItemCount() - 1);
                chatWithUserAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void sendMessage() {
        HashMap<String, String> user1 = new HashMap<>();
        user1.put("UserName", UserDetails.Username);
        user1.put("Text", message.getText().toString());
        user1.put("SenderId", UserDetails.UserID);



       /* HashMap<String, String> user2 = new HashMap<>();
        user2.put("UserName", UserDetails.Username);
        user2.put("Chat", message.getText().toString());*/
        String table=createChatId(createChatId(UserDetails.UserID+UserDetails.Second_user_id));
        Log.d("table====",table);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference1 = firebaseDatabase.getReference().child("Message").child(table);
        ///databaseReference2 = firebaseDatabase.getReference().child("Message").child(UserDetails.Second_User + "_" + UserDetails.Username);

        databaseReference1.push().setValue(user1);
       // databaseReference2.push().setValue(user2);
        message.setText("");
    }

    public String createChatId(String U_id)
    {
        char []arr=U_id.toCharArray();
         Arrays.sort(arr);
        String id=String.valueOf(arr);

        return id;
    }



}
