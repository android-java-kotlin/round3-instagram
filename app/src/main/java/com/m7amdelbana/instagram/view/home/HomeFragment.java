package com.m7amdelbana.instagram.view.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.m7amdelbana.instagram.R;
import com.m7amdelbana.instagram.models.Post;
import com.m7amdelbana.instagram.models.User;
import com.m7amdelbana.instagram.view.home.post.PostAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private PostAdapter postAdapter;

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        setupView(view);
        getPosts();
        return view;
    }

    private void setupView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
    }

    private void getPosts() {

    }
}
