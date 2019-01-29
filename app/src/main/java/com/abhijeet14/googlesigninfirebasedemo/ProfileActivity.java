package com.abhijeet14.googlesigninfirebasedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    TextView t1,t2;

    ImageView userDP;

    FirebaseAuth mAuth;

    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        t1 = findViewById(R.id.textEmail);
        t2 = findViewById(R.id.textName);
        userDP = findViewById(R.id.dp);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        updateUI(user);

    }
    private void updateUI(FirebaseUser myUserObj) {
        t1.setText(myUserObj.getEmail());
        t2.setText(myUserObj.getDisplayName());

        Picasso.with(ProfileActivity.this).load(user.getPhotoUrl()).networkPolicy(NetworkPolicy.OFFLINE).placeholder(R.color.colorAccent).into(userDP, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {
                Picasso.with(ProfileActivity.this).load(user.getPhotoUrl()).placeholder(R.color.colorAccent).into(userDP);
            }
        });
    }
}