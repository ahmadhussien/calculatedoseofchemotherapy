package com.example.calculatedoseofchemotherapy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

            public void onClickv(View v) {
                // Do something in response to button click
                Intent i = new Intent(MainActivity.this, Patients.class);
                startActivity(i);
            }


    public void onClickc(View v) {
        // Do something in response to button click
        Intent i = new Intent(MainActivity.this, calculates.class);
        startActivity(i);
    }

}