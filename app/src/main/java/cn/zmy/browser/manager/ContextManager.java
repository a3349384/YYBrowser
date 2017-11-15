package cn.zmy.browser.manager;

import android.app.Activity;
import android.content.Context;

/**
 * Created by zmy on 2017/11/15.
 */

public class ContextManager
{
    public static ContextManager getInstance()
    {
        return SingletonInner.instance;
    }

    private Context mAppContext;
    private Activity mActivityContext;

    private ContextManager()
    {
    }

    public Context getAppContext()
    {
        return mAppContext;
    }

    public Activity getActivityContext()
    {
        return mActivityContext;
    }

    public void setAppContext(Context context)
    {
        this.mAppContext = context.getApplicationContext();
    }

    public void setActivityContext(Activity activity)
    {
        this.mActivityContext = activity;
    }

    private static class SingletonInner
    {
        private static ContextManager instance = new ContextManager();
    }
}
