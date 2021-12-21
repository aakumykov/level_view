package com.gitlab.aakumykov.start_stop_template;

import android.os.Bundle;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.gitlab.aakumykov.start_stop_template.databinding.ActivityDemoBinding;


public class DemoActivity extends AppCompatActivity {

    private static final String TAG = DemoActivity.class.getSimpleName();
    private ActivityDemoBinding mViewBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewBinding = ActivityDemoBinding.inflate(getLayoutInflater());
        setContentView(mViewBinding.getRoot());

        mViewBinding.seekBar.setProgress(50);

        mViewBinding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mViewBinding.levelView.setLevel(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mViewBinding.levelView.setMaxLevel(
                mViewBinding.seekBar.getMax()
        );

        mViewBinding.levelView.setLevel(
                mViewBinding.seekBar.getProgress()
        );
    }
}