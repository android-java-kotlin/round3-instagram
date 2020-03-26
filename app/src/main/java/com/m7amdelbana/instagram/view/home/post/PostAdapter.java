package com.m7amdelbana.instagram.view.home.post;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.m7amdelbana.instagram.R;
import com.m7amdelbana.instagram.models.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostHolder> {

    private List<Post> posts;

    public PostAdapter(List<Post> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new PostHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        holder.bindView(posts.get(position));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}
