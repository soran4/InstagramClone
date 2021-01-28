package com.soran.myinstagramclone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.soran.myinstagramclone.adapter.MainFragmentPagerAdapter;

import java.security.PrivateKey;

public class MainActivity extends AppCompatActivity {
    private static final int RC_PIC_CODE = 101;
    ImageView imageViewCamera;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        ViewPager2 viewPagerMain = findViewById(R.id.viewPagerMain);
        Toolbar toolbarmain = findViewById(R.id.toolbar);
        setSupportActionBar(toolbarmain);
        imageViewCamera = findViewById(R.id.imageViewCamera);
        imageViewCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CameraActivity.class);
                startActivity(intent);
            }
        });

        viewPagerMain.setUserInputEnabled(false);
        MainFragmentPagerAdapter mainFragmentPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager(), this.getLifecycle());

        viewPagerMain.setAdapter(mainFragmentPagerAdapter);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.actionHome:
                        viewPagerMain.setCurrentItem(0);
                        break;
                    case R.id.actionSearch:
                        viewPagerMain.setCurrentItem(1);
                        break;
                    case R.id.actionLike:
                        viewPagerMain.setCurrentItem(2);
                        break;
                    case R.id.actionProfile:
                        viewPagerMain.setCurrentItem(3);
                }

                return true;
            }
        });
    }
}