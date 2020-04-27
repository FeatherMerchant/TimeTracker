package com.example.timetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.github.mikephil.charting.charts.PieChart;

public class MainActivity extends AppCompatActivity {

    /*Checks if FAB has been clicked already */
    private boolean isFabClicked;
    private final String TAG = "MainActivity";

    private boolean activityRunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        isFabClicked = false;
        //TODO Change this
        activityRunning = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Load animations
        TextView activityText = findViewById(R.id.activityText);
        if (!activityRunning) {
            activityText.setText("No Activity");
        }
        final Animation rotate_left = AnimationUtils.loadAnimation(this, R.anim.rotate_left);
        final Animation rotate_right = AnimationUtils.loadAnimation(this, R.anim.rotate_right);
        final Animation fab2_rotate_left = AnimationUtils.loadAnimation(this, R.anim.fab2_rotate_left);
        final Animation fab2_rotate_right = AnimationUtils.loadAnimation(this, R.anim.fab2_rotate_right);
        // Floating buttons handling
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
                //button animations
                if (isFabClicked) {
                    baseFab.startAnimation(rotate_right);
                    secondFab.startAnimation(fab2_rotate_right);
                    secondFab.hide();
                    secondFab.setClickable(false);
                } else {
                    baseFab.startAnimation(rotate_left);
                    secondFab.show();
                    secondFab.startAnimation(fab2_rotate_left);
                    secondFab.setClickable(true);
                }
                isFabClicked = !isFabClicked;
            }
        });


    }


}
