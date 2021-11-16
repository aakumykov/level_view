package com.gitlab.aakumykov.start_stop_template;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

public class CustomView extends View {

    private static final String TAG = CustomView.class.getSimpleName();
    @ColorInt private int mBgColor;
    @ColorInt private int mBgColorInitial;
    @ColorInt private final int mBgColorDefault = Color.LTGRAY;


    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        processAttributes(attrs);
    }


    // Системные методы
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(mBgColor);
    }


    // Вспомогательнеые методы
    private void processAttributes(AttributeSet attrs) {

        TypedArray typedArray = getContext().getTheme()
                .obtainStyledAttributes(
                    attrs,
                    R.styleable.CustomView,
                    0,
                    0
                );

        try {
            mBgColorInitial = typedArray.getColor(
                    R.styleable.CustomView_cv_bg_color,
                    mBgColorDefault
            );

            mBgColor = mBgColorInitial;
        }
        catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
        finally {
            typedArray.recycle();
        }
    }

    public void resetColor() {
        mBgColor = mBgColorInitial;
        refreshView();
    }


    // Внешние методы
    public void setBgColor(@ColorInt int bgColor) {
        mBgColor = bgColor;
        refreshView();
    }

    private void refreshView() {
        invalidate();
        requestLayout();
    }

}
