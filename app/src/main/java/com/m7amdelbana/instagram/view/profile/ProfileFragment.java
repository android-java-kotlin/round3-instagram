package com.m7amdelbana.instagram.view.profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.m7amdelbana.instagram.R;
import com.m7amdelbana.instagram.models.Post;
import com.m7amdelbana.instagram.models.User;
import com.m7amdelbana.instagram.view.home.post.PostAdapter;
import com.m7amdelbana.instagram.view.splash.SplashActivity;

import java.util.Objects;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private ImageView imgProfile;
    private TextView tvName;
    private TextView tvEmail;
    private Button btnLogOut;

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    public ProfileFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        setupView(view);
        return view;
    }

    private void setupView(View view) {
        imgProfile = view.findViewById(R.id.profile_imageView);
        tvName = view.findViewById(R.id.profile_name_textView);
        tvEmail = view.findViewById(R.id.profile_email_textView);
        btnLogOut = view.findViewById(R.id.profile_sign_out_button);
        btnLogOut.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users").child(user.getUid());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User userObject = dataSnapshot.getValue(User.class);
                tvName.setText(userObject.getName());
                tvEmail.setText(userObject.getEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "" + error.toException(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profile_sign_out_button:
                mAuth.signOut();
                Objects.requireNonNull(getActivity()).startActivity(new Intent(getActivity(), SplashActivity.class));
                break;
        }
    }
}
