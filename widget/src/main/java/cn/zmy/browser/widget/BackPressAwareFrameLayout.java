package cn.zmy.browser.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

import java.security.spec.ECField;

/**
 * Created by zmy on 2017/11/16.
 */

public class BackPressAwareFrameLayout extends FrameLayout
{
    private Runnable mBackPressedRunnable;

    public BackPressAwareFrameLayout(@NonNull Context context)
    {
        super(context);
    }

    public BackPressAwareFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    public BackPressAwareFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public void setOnBackPressed(Runnable runnable)
    {
        mBackPressedRunnable = runnable;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK)
        {
            if (mBackPressedRunnable != null)
            {
                mBackPressedRunnable.run();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}
