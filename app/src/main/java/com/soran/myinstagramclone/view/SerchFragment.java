package com.soran.myinstagramclone.view;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.soran.myinstagramclone.R;
import com.soran.myinstagramclone.WebService.Service;
import com.soran.myinstagramclone.adapter.HomeAdapter;
import com.soran.myinstagramclone.model.PixabayPosts;
import com.soran.myinstagramclone.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.core.content.ContextCompat.getSystemService;

public class SerchFragment extends Fragment {
    RecyclerView recyclerView;
    ProgressBar progressBarSearch;
    TextView textViewSearching;
    TextView textViewResult;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText editTextSearchPost = view.findViewById(R.id.editTextSearchPost);
        recyclerView = view.findViewById(R.id.recyclearViewSearch);
        progressBarSearch = view.findViewById(R.id.progressBarSearch);
        textViewSearching = view.findViewById(R.id.textViewSearching);
        textViewResult = view.findViewById(R.id.textViewSearchResult);
        progressBarSearch.setVisibility(View.INVISIBLE);
        textViewSearching.setVisibility(View.INVISIBLE);
        textViewResult.setVisibility(View.INVISIBLE);


        editTextSearchPost.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String searchkeyWord = editTextSearchPost.getText().toString();
                    searchInPixaBay(searchkeyWord);
                    progressBarSearch.setVisibility(View.VISIBLE);
                    textViewSearching.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.INVISIBLE);
                    textViewResult.setVisibility(View.INVISIBLE);
                    return true;
                }
                return false;
            }
        });
    }

    public void searchInPixaBay(String keyWord) {
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pixabay.com/api/")
                .addConverterFactory(gsonConverterFactory)
                .build();

        Service service = retrofit.create(Service.class);
        service.getPostsBySearch(keyWord).enqueue(new Callback<PixabayPosts>() {
            @Override
            public void onResponse(Call<PixabayPosts> call, Response<PixabayPosts> response) {
                progressBarSearch.setVisibility(View.INVISIBLE);
                textViewSearching.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                List<Post> postList = response.body().getHits();
                if (postList.isEmpty()){
                    textViewResult.setVisibility(View.VISIBLE);
                }
                HomeAdapter homeAdapter = new HomeAdapter(postList, getActivity());
                recyclerView.setAdapter(homeAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            }

            @Override
            public void onFailure(Call<PixabayPosts> call, Throwable t) {

            }
        });


    }
}
