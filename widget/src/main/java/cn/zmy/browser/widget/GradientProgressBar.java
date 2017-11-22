package cn.zmy.browser.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by zmy on 2017/11/21.
 */

public class GradientProgressBar extends View
{
    private @ColorInt
    int mColorStart;
    private @ColorInt
    int mColorEnd;
    private int mProgress;

    private GradientDrawable mGradientDrawable;
    private int[] mGradientColors;
    private int mPerSize;

    public GradientProgressBar(Context context)
    {
        this(context, null);
    }

    public GradientProgressBar(Context context, @Nullable AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public GradientProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.GradientProgressBar, defStyleAttr, 0);
        mColorStart = array.getColor(R.styleable.GradientProgressBar_gpb_startColor, Color.WHITE);
        mColorEnd = array.getColor(R.styleable.GradientProgressBar_gpb_endColor, Color.BLACK);
        mProgress = array.getInt(R.styleable.GradientProgressBar_gqb_progress, 0);
        array.recycle();

        mGradientColors = new int[]{mColorStart, mColorEnd};
        mGradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, mGradientColors);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        mPerSize = w / 100;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        mGradientDrawable.draw(canvas);
    }

    public int getColorStart()
    {
        return mColorStart;
    }

    public void setColorStart(int colorStart)
    {
        this.mColorStart = colorStart;
        mGradientColors[0] = colorStart;

        invalidate();
    }

    public int getColorEnd()
    {
        return mColorEnd;
    }

    public void setColorEnd(int colorEnd)
    {
        this.mColorEnd = colorEnd;
        mGradientColors[1] = colorEnd;

        invalidate();
    }

    public int getProgress()
    {
        return mProgress;
    }

    public void setProgress(int progress)
    {
        if (progress > 100)
        {
            progress = 100;
        }
        if (progress < 0)
        {
            progress = 0;
        }
        this.mProgress = progress;

        int right = mProgress * mPerSize;
        mGradientDrawable.setBounds(0,0, right, getMeasuredHeight());
        invalidate();
    }
}
