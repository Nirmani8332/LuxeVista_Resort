package com.example.luxevistaresort;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private BannerAdapter adapter;
    private Handler handler = new Handler();
    private Runnable runnable;
    private static final long SWIPE_DELAY = 3000; // 3 seconds. Change to 30000 for 30 seconds.

    private CardView cardBookStay, cardRestaurants, cardSpa, cardActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewPager = findViewById(R.id.viewPager);
        cardBookStay = findViewById(R.id.cardBookStay);
        cardRestaurants = findViewById(R.id.cardRestaurants);
        cardSpa = findViewById(R.id.cardSpa);
        cardActivities = findViewById(R.id.cardActivities);

        List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.banner_one);
        imageList.add(R.drawable.banner_two);
        imageList.add(R.drawable.banner_three);

        adapter = new BannerAdapter(imageList);
        viewPager.setAdapter(adapter);

        runnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = viewPager.getCurrentItem();
                int totalItems = adapter.getItemCount();
                if (totalItems > 0) {
                    int nextItem = (currentItem + 1) % totalItems;
                    viewPager.setCurrentItem(nextItem);
                }
                handler.postDelayed(this, SWIPE_DELAY);
            }
        };

        cardBookStay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, BookingActivity.class));
            }
        });

        cardRestaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, RestaurantActivity.class));
            }
        });

        cardSpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, SpaActivity.class));
            }
        });

        cardActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ActivitiesActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, SWIPE_DELAY);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }
}
