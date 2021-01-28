package com.soran.myinstagramclone.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.qintong.library.InsLoadingView;
import com.soran.myinstagramclone.R;
import com.soran.myinstagramclone.WebService.Service;
import com.soran.myinstagramclone.adapter.ProfileAdapter;
import com.soran.myinstagramclone.model.PixabayPosts;
import com.soran.myinstagramclone.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends Fragment {
    RecyclerView recyclerViewProfile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmenr_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewProfile = view.findViewById(R.id.recyclearViewProfile);
        InsLoadingView insLoadingViewUser = view.findViewById(R.id.imageViewUser);

        Glide.with(getActivity()).load("https://scontent-lhr8-1.cdninstagram.com/v/t51.2885-19/s320x320/121354378_261122541936798_1930289441812477129_n.jpg?_nc_ht=scontent-lhr8-1.cdninstagram.com&_nc_ohc=9cMJ5hUIAEsAX_X6DeC&tp=1&oh=f4e89b1f6d826444444baae81191c7c3&oe=5FF68EBD")
                .circleCrop()
                .into(insLoadingViewUser);


        insLoadingViewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (insLoadingViewUser.getStatus() == InsLoadingView.Status.LOADING) {
                    insLoadingViewUser.setStatus(InsLoadingView.Status.UNCLICKED);
                } else {
                    insLoadingViewUser.setStatus(InsLoadingView.Status.LOADING);
                }
            }
        });
        getPhotos();
    }
    void  getPhotos(){
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pixabay.com/api/")
                .addConverterFactory(gsonConverterFactory)
                .build();

        Service service = retrofit.create(Service.class);
        service.getAllPosts().enqueue(new Callback<PixabayPosts>() {
            @Override
            public void onResponse(Call<PixabayPosts> call, Response<PixabayPosts> response) {
                List<Post> postList = response.body().getHits();
                ProfileAdapter profileAdapter = new ProfileAdapter(postList,getActivity());
                recyclerViewProfile.setAdapter(profileAdapter);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),3);
                recyclerViewProfile.setLayoutManager(gridLayoutManager);
            }

            @Override
            public void onFailure(Call<PixabayPosts> call, Throwable t) {

            }
        });



    }
}
