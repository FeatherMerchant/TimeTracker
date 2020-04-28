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
