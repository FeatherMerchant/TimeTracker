package com.example.timetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.github.mikephil.charting.charts.PieChart;

public class MainActivity extends AppCompatActivity {

    /*Checks if FAB has been clicked already */
    private boolean isFabClicked;
    private final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        isFabClicked = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Handling for floating button
        final Animation rotate_left = AnimationUtils.loadAnimation(this, R.anim.rotate_left);
        final Animation rotate_right = AnimationUtils.loadAnimation(this, R.anim.rotate_right);
        final Animation fab2_expand = AnimationUtils.loadAnimation(this, R.anim.fab2_expand);
        final Animation fab2_retract = AnimationUtils.loadAnimation(this, R.anim.fab2_retract);
        final FloatingActionButton baseFab = findViewById(R.id.floatingActionButton1);
        final FloatingActionButton secondFab = findViewById(R.id.floatingActionButton2);
        secondFab.hide();
        secondFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Fab 2");
            }
        });
        baseFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //rotation animation
                if (isFabClicked) {
                    baseFab.startAnimation(rotate_right);
                    secondFab.startAnimation(fab2_retract);
                    secondFab.setClickable(false);
                } else {
                    baseFab.startAnimation(rotate_left);
                    secondFab.show();
                    secondFab.startAnimation(fab2_expand);
                    secondFab.setClickable(true);
                }
                isFabClicked = !isFabClicked;
            }
        });


    }


}
