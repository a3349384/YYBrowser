package cn.zmy.browser.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zmy on 2017/11/13.
 * 支持显示温度值，也就是一个数字后面跟了一个小圆圈 <br/>
 * 此View的绘制模式写死了为wrap_content模式
 */

public class TemperatureView extends View
{
    private int mTemperature;
    private @ColorInt int mColor;
    private int mTextSize;
    private int mCircleRadius;
    private int mSpace;

    private Paint mPaint;
    private float mDensity;//用于计算dp -> px
    private int mTextStrokeWidth;//保存文字的线条宽度，因为要保证圆圈的线条宽度和文字一致
    private Rect mTextRect; //用于计算温度文字的实际高度

    public TemperatureView(Context context)
    {
        this(context, null);
    }

    public TemperatureView(Context context, @Nullable AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public TemperatureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        //解析attr
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TemperatureView, defStyleAttr, 0);
        mColor = typedArray.getColor(R.styleable.TemperatureView_tempV_color, Color.WHITE);
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.TemperatureView_tempV_textSize, dip2px(16));
        mCircleRadius = typedArray.getDimensionPixelSize(R.styleable.TemperatureView_tempV_circleRadius, dip2px(2));
        mSpace = typedArray.getDimensionPixelSize(R.styleable.TemperatureView_tempV_space, dip2px(5));
        typedArray.recycle();

        this.init();
    }

    private void init()
    {
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mColor);
        mPaint.setTextSize(mTextSize);
        mTextStrokeWidth = getTextStrokeWidth();
    }

    /**
     *
     * 获取温度值*/
    public int getTemperature()
    {
        return mTemperature;
    }

    /**
     * 设置温度值
     * */
    public void setTemperature(int mTemperature)
    {
        this.mTemperature = mTemperature;
        requestLayout();
    }

    /**
     * 获取颜色
     * */
    public int getColor()
    {
        return mColor;
    }

    /**
     * 设置颜色，同时影响温度和圆圈
     * */
    public void setColor(int mColor)
    {
        this.mColor = mColor;
        mPaint.setColor(mColor);
        invalidate();
    }

    /**
     * 获取温度文字的大小
     * */
    public int getTextSize()
    {
        return mTextSize;
    }

    /**
     * 设置温度文字的大小
     * */
    public void setTextSize(int textSize)
    {
        this.mTextSize = textSize;
        mPaint.setTextSize(textSize);
        mTextStrokeWidth = getTextStrokeWidth();
        requestLayout();
    }

    /**
     * 获取圆圈的半径
     * */
    public int getCircleRadius()
    {
        return mCircleRadius;
    }

    /**
     * 设置圆圈的半径
     * */
    public void setCircleRadius(int mCircleRadius)
    {
        this.mCircleRadius = mCircleRadius;
        requestLayout();
    }

    /**
     * 获取温度文字和圆圈的水平距离
     * */
    public int getSpace()
    {
        return mSpace;
    }

    /**
     * 设置温度文字和圆圈的水平距离
     * */
    public void setSpace(int mSpace)
    {
        this.mSpace = mSpace;
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        String s = Integer.toString(mTemperature);
        float temperatureWidth = mPaint.measureText(s);
        if (mTextRect == null)
        {
            mTextRect = new Rect();
        }
        mPaint.getTextBounds(s, 0, s.length(), mTextRect);
        setMeasuredDimension((int) (temperatureWidth + mSpace + 2 * mCircleRadius + mTextStrokeWidth), mTextRect.bottom - mTextRect.top);//rect.top是负数，所以这里用减操作
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawText(Integer.toString(mTemperature), 0, canvas.getHeight() - mTextRect.bottom, mPaint);
        mPaint.setStrokeWidth(mTextStrokeWidth);
        canvas.drawCircle(getWidth() - mCircleRadius - mTextStrokeWidth, mCircleRadius + mTextStrokeWidth/2, mCircleRadius, mPaint);
        mPaint.setStrokeWidth(0);
    }

    private int getTextStrokeWidth()
    {
        Rect rect = new Rect();
        mPaint.getTextBounds("|" , 0, 1, rect);
        return rect.right - rect.left;
    }

    private int dip2px(float dpValue)
    {
        if (mDensity == 0)
        {
            mDensity = getContext().getResources().getDisplayMetrics().density;
        }
        return (int)(dpValue * mDensity + 0.5f);
    }
}
