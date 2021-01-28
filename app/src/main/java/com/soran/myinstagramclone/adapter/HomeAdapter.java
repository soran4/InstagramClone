package com.soran.myinstagramclone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.soran.myinstagramclone.R;
import com.soran.myinstagramclone.model.Post;
import com.soran.myinstagramclone.view.HomeFragment;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    List<Post> postList;
    Context context;

    public HomeAdapter(List<Post> postList, Context context) {
        this.postList = postList;
        this.context = context;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_home, parent, false);
        HomeViewHolder homeViewHolder = new HomeViewHolder(view);
        return homeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Post post = postList.get(position);

        holder.textViewComments.setText(post.getComments() + "");
        holder.textViewLikes.setText(post.getLikes() + "");
        holder.textViewViews.setText(post.getViews() + "");
        holder.textViewUsername.setText(post.getUsername());

        Glide.with(context)
                .load(post.getUserImageURL())
                .circleCrop()
                .into(holder.imageViewUserProfile);
        Glide.with(context).load(post.getWebformatURL()).into(holder.imageViewPostContent);

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    class HomeViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewUserProfile;
        ImageView imageViewPostContent;
        TextView textViewUsername;
        TextView textViewLikes;
        TextView textViewViews;
        TextView textViewComments;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPostContent = itemView.findViewById(R.id.imageViewPost);
            imageViewUserProfile = itemView.findViewById(R.id.imageViewUserProfile);
            textViewComments = itemView.findViewById(R.id.textViewcoment);
            textViewLikes = itemView.findViewById(R.id.textViewLikes);
            textViewUsername = itemView.findViewById(R.id.textViewUserName);
            textViewViews = itemView.findViewById(R.id.textViewViews);
        }
    }
}
