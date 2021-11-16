package com.gitlab.aakumykov.start_stop_template;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

public class LevelView extends View {

    private static final String TAG = LevelView.class.getSimpleName();

    @ColorInt private final int INDICATOR_COLOR_DEFAULT = Color.CYAN;
    @ColorInt private final int BACKGROUND_COLOR_DEFAULT = Color.rgb(
            245, 245, 245);

    @ColorInt private int mIndicatorColor;
    @ColorInt private int mBackgroundColor;

    private int mCurrentLevel;
    private int mMaxLevel;

    private final Rect mRect;
    private final Paint mPaint;


    public LevelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mRect = new Rect();
        mPaint = new Paint();

        processAttributes(attrs);
    }

    private void processAttributes(AttributeSet attrs) {

        TypedArray typedArray = getContext().getTheme()
                .obtainStyledAttributes(
                        attrs,
                        R.styleable.CustomView,
                        0,
                        0
                );

        try {
            mIndicatorColor = typedArray.getColor(
                    R.styleable.CustomView_cv_indicator_color,
                    INDICATOR_COLOR_DEFAULT
            );

            mBackgroundColor = typedArray.getColor(
                    R.styleable.CustomView_cv_background_color,
                    BACKGROUND_COLOR_DEFAULT
            );

            mCurrentLevel = typedArray.getInteger(
                    R.styleable.CustomView_cv_level,
                    0
            ) ;

            mMaxLevel = typedArray.getInteger(
                    R.styleable.CustomView_cv_max_level,
                    0
            );
        }
        catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
        finally {
            typedArray.recycle();
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getViewWidth(widthMeasureSpec);
        int height = getViewHeight(heightMeasureSpec);

        setMeasuredDimension(width, height);

        calculateRectSize(width, height);
    }

    private int getViewWidth(int widthMeasureSpec) {
        int minW = getPaddingLeft() + getPaddingRight() + getSuggestedMinimumWidth();
        return resolveSizeAndState(minW, widthMeasureSpec, 1);
    }

    private int getViewHeight(int heightMeasureSpec) {
        int minH = getPaddingTop() + getPaddingBottom() + getSuggestedMinimumHeight();
        return resolveSizeAndState(minH, heightMeasureSpec, 1);
    }

    private void calculateRectSize(int widgetWidth, int widgetHeight) {
        Log.d(TAG, "============ calculateRectSize() ============");

        float levelFraction = mCurrentLevel * 1f / mMaxLevel;
        int levelHeight = (int) (widgetHeight * levelFraction);

        Log.d(TAG, "widget: "+widgetWidth+"x"+widgetHeight);
        Log.d(TAG, "levelFraction: "+levelFraction);
        Log.d(TAG, "levelHeight: "+levelHeight);

        int x1 = 0;
        int y1 = widgetHeight - levelHeight;
        int x2 = widgetWidth;
        int y2 = widgetHeight;

        Log.d(TAG, "rect: "+x1+","+y1+" - "+x2+","+y2);

        mRect.set(
            x1,
            y1,
            x2,
            y2
        );
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(mBackgroundColor);

        mPaint.setColor(mIndicatorColor);
        canvas.drawRect(mRect, mPaint);
    }


    // Публичные методы
    public void setIndicatorColor(@ColorInt int indicatorColor) {
        mIndicatorColor = indicatorColor;
        refreshView();
    }

    public void setBackgroundColor(@ColorInt int backgroundColor) {
        mBackgroundColor = backgroundColor;
        refreshView();
    }

    public void setLevel(int level) {
        mCurrentLevel = level;
        refreshView();
    }

    public int getLevel() {
        return mCurrentLevel;
    }

    public void setMaxLevel(int maxLevel) {
        mMaxLevel = maxLevel;
        refreshView();
    }

    public int getMaxLevel() {
        return mMaxLevel;
    }


    // Вспомогательнеые методы
    private void refreshView() {
        invalidate();
        requestLayout();
    }

}
