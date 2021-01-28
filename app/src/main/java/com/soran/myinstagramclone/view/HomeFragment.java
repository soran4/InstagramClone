package com.soran.myinstagramclone.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.soran.myinstagramclone.R;
import com.soran.myinstagramclone.WebService.Service;
import com.soran.myinstagramclone.adapter.HomeAdapter;
import com.soran.myinstagramclone.model.PixabayPosts;
import com.soran.myinstagramclone.model.Post;

import java.util.List;
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerViewHome = view.findViewById(R.id.recyclearViewHome);
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
                HomeAdapter homeAdapter = new HomeAdapter(postList,getActivity());
                recyclerViewHome.setAdapter(homeAdapter);
                recyclerViewHome.setLayoutManager(new LinearLayoutManager(getActivity()));



            }

            @Override
            public void onFailure(Call<PixabayPosts> call, Throwable t) {

            }
        });

    }
}
