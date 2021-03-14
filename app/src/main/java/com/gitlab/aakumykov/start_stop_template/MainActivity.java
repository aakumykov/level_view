package com.gitlab.aakumykov.start_stop_template;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gitlab.aakumykov.start_stop_template.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mViewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mViewBinding.getRoot());

        mViewBinding.startButton.setOnClickListener(this::onStartButtonClicked);
        mViewBinding.stopButton.setOnClickListener(this::onStopButtonClicked);
    }

    private void onStartButtonClicked(View view) {

    }

    private void onStopButtonClicked(View view) {

    }



    private void showProgressMessage(String msg) {
        mViewBinding.textView.post(() -> {
            showProgressBar();
            showInfo(msg);
        });
    }

    private void hideProgressMessage() {
        hideInfoAndError();
        hideProgressBar();
    }

    private void showInfo(String text) {
        mViewBinding.textView.setText(text);
        mViewBinding.textView.setTextColor(getResources().getColor(R.color.info));
    }

    private void showError(String text) {
        showInfo(text);
        mViewBinding.textView.setTextColor(getResources().getColor(R.color.error));
    }

    private void hideInfoAndError() {
        ViewUtils.hide(mViewBinding.textView);
    }

    private void showProgressBar() {
        ViewUtils.show(mViewBinding.progressBar);
    }

    private void hideProgressBar() {
        ViewUtils.hide(mViewBinding.progressBar);
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

}