package com.gitlab.aakumykov.start_stop_template;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gitlab.aakumykov.start_stop_template.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding mViewBinding;
    private final StringBuilder mStringBuilder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mViewBinding.getRoot());

        mViewBinding.startButton.setOnClickListener(this::onStartButtonClicked);
        mViewBinding.stopButton.setOnClickListener(this::onStopButtonClicked);
    }

    private void onStartButtonClicked(View view) {
        log("onStartButtonClicked()");
        showProgressMessage("Работаю");

        getWindow().getDecorView().postDelayed(() -> {
            hideProgressMessage();
            log("Поработал");
        }, 1000);
    }

    private void onStopButtonClicked(View view) {
        log("onStopButtonClicked()");
    }


    private void showProgressMessage(String msg) {
        mViewBinding.messageView.post(() -> {
            showProgressBar();
            showInfo(msg);
        });
    }

    private void hideProgressMessage() {
        hideInfoAndError();
        hideProgressBar();
    }

    private void showInfo(String text) {
        mViewBinding.messageView.setText(text);
        mViewBinding.messageView.setTextColor(getResources().getColor(R.color.info));
        ViewUtils.show(mViewBinding.messageView);
    }

    private void showError(String text) {
        showInfo(text);
        mViewBinding.messageView.setTextColor(getResources().getColor(R.color.error));
    }

    private void hideInfoAndError() {
        ViewUtils.hide(mViewBinding.messageView);
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

    private void log(String s) {
        Log.d(TAG, s);

        mStringBuilder.append(s);
        mStringBuilder.append("\n");

        mViewBinding.outputView.setText(mStringBuilder.toString());
    }
}