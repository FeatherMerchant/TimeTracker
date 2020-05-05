package com.example.timetracker;


import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class MainActivity extends FragmentActivity {

    /*Checks if FAB has been clicked already */
    private boolean isFabClicked;

    private final String TAG = "MainActivity";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        isFabClicked = false;

        ViewPager2 viewPager = findViewById(R.id.pager);
        FragmentStateAdapter pageAdapter = new PageAdapter(this);
        viewPager.setAdapter(pageAdapter);
        TabLayout tabs = findViewById(R.id.tabs);

        new TabLayoutMediator(tabs, viewPager, (tab, position) -> {
            if (position == 0) {
                tab.setText("Activities");
            } else {
                tab.setText("Statistics");
            }
        }).attach();

        // Load animations

        final Animation rotate_left = AnimationUtils.loadAnimation(this, R.anim.rotate_left);
        final Animation rotate_right = AnimationUtils.loadAnimation(this, R.anim.rotate_right);
        final Animation fab2_rotate_left = AnimationUtils.loadAnimation(this, R.anim.fab2_rotate_left);
        final Animation fab2_rotate_right = AnimationUtils.loadAnimation(this, R.anim.fab2_rotate_right);
        final Animation fab3_rotate_left = AnimationUtils.loadAnimation(this, R.anim.fab3_rotate_left);
        final Animation fab3_rotate_right = AnimationUtils.loadAnimation(this, R.anim.fab3_rotate_right);
        final Animation fab4_rotate_left = AnimationUtils.loadAnimation(this, R.anim.fab4_rotate_left);
        final Animation fab4_rotate_right = AnimationUtils.loadAnimation(this, R.anim.fab4_rotate_right);
        // Floating buttons handling
        final FloatingActionButton baseFab = findViewById(R.id.floatingActionButton1);
        final FloatingActionButton addFab = findViewById(R.id.floatingActionButton2);
        final FloatingActionButton editFab = findViewById(R.id.floatingActionButton3);
        final FloatingActionButton playFab = findViewById(R.id.floatingActionButton4);

        addFab.hide();
        editFab.hide();
        playFab.hide();

        addFab.setOnClickListener(v -> {
                Log.i(TAG, "add FAB");
        });

        editFab.setOnClickListener(v -> {
                Log.i(TAG, "edit FAB");
        });

        baseFab.setOnClickListener(v -> {
            //button animations
            if (isFabClicked) {
                baseFab.startAnimation(rotate_right);
                addFab.startAnimation(fab2_rotate_right);
                addFab.hide();
                editFab.startAnimation(fab3_rotate_right);
                editFab.hide();
                playFab.startAnimation(fab4_rotate_right);
                playFab.hide();
                addFab.setClickable(false);
                editFab.setClickable(false);
                playFab.setClickable(false);
            } else {
                baseFab.startAnimation(rotate_left);
                addFab.show();
                addFab.startAnimation(fab2_rotate_left);
                editFab.show();
                editFab.startAnimation(fab3_rotate_left);
                playFab.show();
                playFab.startAnimation(fab4_rotate_left);
                addFab.setClickable(true);
                editFab.setClickable(true);
                playFab.setClickable(true);
            }
            isFabClicked = !isFabClicked;
        });
    }

    public ActivityLog loadActivities() {
        return null;
    }
}
