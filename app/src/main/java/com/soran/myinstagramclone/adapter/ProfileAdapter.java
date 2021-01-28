package com.soran.myinstagramclone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.soran.myinstagramclone.R;
import com.soran.myinstagramclone.model.PixabayPosts;
import com.soran.myinstagramclone.model.Post;

import java.util.List;


public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {
List<Post> postsList;
Context context;

    public ProfileAdapter(List<Post> postsList, Context context) {
        this.postsList = postsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_profile,parent,false);
        ProfileViewHolder profileViewHolder = new ProfileViewHolder(view);
        return profileViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        Post post = postsList.get(position);
        Glide.with(context)
                .load(post.getWebformatURL())
                .placeholder(R.color.gray)
                .into(holder.imageViewProfilePosts);

    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    class ProfileViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewProfilePosts;
        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewProfilePosts = itemView.findViewById(R.id.imageviewProfilesPosts);
        }
    }
}
