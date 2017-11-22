package cn.zmy.browser.common.base;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jaeger.library.StatusBarUtil;

/**
 * Created by zmy on 2017/11/22.
 */

public abstract class BaseActivity extends AppCompatActivity
{
    @Override
    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            //状态栏颜色变成黑色
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

            StatusBarUtil.setTransparent(this);
        }
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            StatusBarUtil.setTranslucent(this);
        }
    }
}
