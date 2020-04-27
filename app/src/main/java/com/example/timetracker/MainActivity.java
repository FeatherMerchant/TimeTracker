package com.example.timetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.github.mikephil.charting.charts.PieChart;

public class MainActivity extends AppCompatActivity {

    /*Checks if FAB has been clicked already */
    private boolean isFabClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        isFabClicked = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Handling for floating button
        Animation rotate_left = AnimationUtils.loadAnimation(this, R.anim.rotate_left);
        final Animation rotate_right = AnimationUtils.loadAnimation(this, R.anim.rotate_right);
        final FloatingActionButton baseFab = findViewById(R.id.floatingActionButton1);
        baseFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFabClicked) {
                    baseFab.startAnimation(rotate_right);
                } else {
                    baseFab.startAnimation(rotate_right);
                }
                isFabClicked = !isFabClicked;
            }
        });


    }


}
