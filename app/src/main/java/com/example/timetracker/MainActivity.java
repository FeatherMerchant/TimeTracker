package com.example.timetracker;


import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends FragmentActivity {

    /*Checks if FAB has been clicked already */
    private boolean isFabClicked;

    private final String TAG = "MainActivity";

    /*Handles tabs*/
    private ViewPager2 pager;

    private PageAdapter pageAdapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isFabClicked = false;
        //Setting up adapter for fragments
        pager = findViewById(R.id.pager);
        pageAdapter = new PageAdapter(this);
        pager.setAdapter(pageAdapter);

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

        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "add FAB");
            }
        });

        editFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "edit FAB");
            }
        });
        baseFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });
    }

}
