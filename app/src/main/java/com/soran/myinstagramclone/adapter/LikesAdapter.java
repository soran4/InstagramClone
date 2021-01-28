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

import java.util.List;


public class LikesAdapter extends RecyclerView.Adapter<LikesAdapter.likesViewHolder>{
    List<Post> postList;
    Context context;

    public LikesAdapter(List<Post> postList, Context context) {
        this.postList = postList;
        this.context = context;
    }

    @NonNull
    @Override
    public likesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_likes,parent,false);

        return new likesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull likesViewHolder holder, int position) {
        Glide.with(context)
                .load(postList.get(position).getUserImageURL())
                .circleCrop()
                .into(holder.imageViewprofile);
        holder.textViewUserName.setText(postList.get(position).getUsername());
        holder.textViewActivity.setText(" Liked Your Post ");
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    class likesViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewprofile;
        TextView textViewUserName;
        TextView textViewActivity;
        public likesViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewprofile = itemView.findViewById(R.id.imageViewUserLike);
            textViewUserName = itemView.findViewById(R.id.textViewuserNameLike);
            textViewActivity = itemView.findViewById(R.id.textViewActivity);
        }
    }
}
