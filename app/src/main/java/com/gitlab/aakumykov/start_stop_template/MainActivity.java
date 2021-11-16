package com.gitlab.aakumykov.start_stop_template;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gitlab.aakumykov.start_stop_template.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding mViewBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mViewBinding.getRoot());

        mViewBinding.button1.setOnClickListener(view -> {
            mViewBinding.customView.setBgColor(
                    getResources().getColor(R.color.teal_700)
            );
        });

        mViewBinding.button2.setOnClickListener(view -> {
            mViewBinding.customView.resetColor();
        });
    }


}