package com.root.ram.chat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.root.ram.R;
import com.root.ram.chat.Constantvalue.UserDetails;

import java.util.ArrayList;


public class UserList extends AppCompatActivity {


    UserListAdapter userListAdapter;
    ArrayList<UserDetailsData> userDetailsDatas = new ArrayList<>();

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    private android.support.v7.widget.RecyclerView userList;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        this.userList = (RecyclerView) findViewById(R.id.userList);

        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        userList.setLayoutManager(linearLayoutManager);
        userList.setHasFixedSize(true);

        userListAdapter = new UserListAdapter(userDetailsDatas);
        userList.setAdapter(userListAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("User");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                UserDetailsData userDetailsData = dataSnapshot.getValue(UserDetailsData.class);
                if (UserDetails.UserID.equals(userDetailsData.getUID())) {
                    UserDetails.Username = userDetailsData.getUserName();
                    getSupportActionBar().setTitle(userDetailsData.getUserName());
                }
                else {
                    userDetailsDatas.add(userDetailsData);

                }
                userListAdapter.notifyDataSetChanged();
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


}
