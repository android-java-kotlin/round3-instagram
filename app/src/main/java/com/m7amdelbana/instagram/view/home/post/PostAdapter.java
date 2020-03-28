package com.m7amdelbana.instagram.view.home.post;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.m7amdelbana.instagram.R;
import com.m7amdelbana.instagram.models.Post;
import com.m7amdelbana.instagram.utils.OnLikeClicked;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostHolder> {

    private List<Post> posts;
    private OnLikeClicked onLikeClicked;

    public PostAdapter(List<Post> posts, OnLikeClicked onLikeClicked) {
        this.posts = posts;
        this.onLikeClicked = onLikeClicked;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new PostHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, final int position) {
        holder.bindView(posts.get(position));
        holder.imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLikeClicked.onLikeClicked(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}
