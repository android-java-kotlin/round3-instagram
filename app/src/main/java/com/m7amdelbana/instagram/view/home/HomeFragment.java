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
        User user = new User();
        user.setName("Mohamed Elbana");
        user.setImage("https://s.alicdn.com/@sc01/kf/H759ef84a03c54347b78a3d435cca2abeP.png_220x220.png");

        Post post = new Post();
        post.setTitle("This is image");
        post.setDate("8:30 PM 27-3-2020");
        post.setImage("https://s.alicdn.com/@sc01/kf/HTB1CXigX9WD3KVjSZSgq6ACxVXaS.jpg_220x220.jpg");
        post.setUser(user);

        ArrayList<Post> posts = new ArrayList<>();
        posts.add(post);
        posts.add(post);

        postAdapter = new PostAdapter(posts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(postAdapter);
    }
}
