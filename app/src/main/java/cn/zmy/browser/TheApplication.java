package cn.zmy.browser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.RePluginApplication;
import com.qihoo360.replugin.RePluginCallbacks;
import com.qihoo360.replugin.RePluginConfig;
import com.qihoo360.replugin.RePluginEventCallbacks;
import com.squareup.leakcanary.LeakCanary;

import cn.zmy.browser.common.manager.ContextManager;

/**
 * Created by zmy on 2017/11/14.
 */

public class TheApplication extends RePluginApplication
{
    @Override
    protected void attachBaseContext(Context base)
    {
        super.attachBaseContext(base);
        // FIXME 允许接收rpRunPlugin等Gradle Task，发布时请务必关掉，以免出现问题
        RePlugin.enableDebugger(base, BuildConfig.DEBUG);
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        //初始化LeakCanary
        if (LeakCanary.isInAnalyzerProcess(this))
        {
            return;
        }
        LeakCanary.install(this);

        //初始化Context管理
        ContextManager.getInstance().setAppContext(this);
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks()
        {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState)
            {
                ContextManager.getInstance().setActivityContext(activity);
            }

            @Override
            public void onActivityStarted(Activity activity)
            {
                ContextManager.getInstance().setActivityContext(activity);
            }

            @Override
            public void onActivityResumed(Activity activity)
            {
                ContextManager.getInstance().setActivityContext(activity);
            }

            @Override
            public void onActivityPaused(Activity activity)
            {

            }

            @Override
            public void onActivityStopped(Activity activity)
            {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState)
            {

            }

            @Override
            public void onActivityDestroyed(Activity activity)
            {
                if (activity == ContextManager.getInstance().getActivityContext())
                {
                    ContextManager.getInstance().setActivityContext(null);
                }
            }
        });
    }

    @Override
    protected RePluginConfig createConfig()
    {
        RePluginConfig c = new RePluginConfig();
        // 允许“插件使用宿主类”。默认为“关闭”
        c.setUseHostClassIfNotFound(false);
        // RePlugin默认会对安装的外置插件进行签名校验，这里先关掉，避免调试时出现签名错误
        c.setVerifySign(!BuildConfig.DEBUG);
        // 针对“安装失败”等情况来做进一步的事件处理
        c.setEventCallbacks(new HostEventCallbacks(this));
        return c;
    }

    @Override
    protected RePluginCallbacks createCallbacks()
    {
        return new HostCallbacks(this);
    }

    /**
     * 宿主针对RePlugin的自定义行为
     */
    private class HostCallbacks extends RePluginCallbacks
    {
        private static final String TAG = "HostCallbacks";

        private HostCallbacks(Context context)
        {
            super(context);
        }

        @Override
        public boolean onPluginNotExistsForActivity(Context context, String plugin, Intent intent, int process)
        {
            // 当插件"没有安装"时触发此逻辑，可打开您的"下载对话框"并开始下载。
            // 其中"intent"可以传递到"对话框"内，这样可在下载完成后，打开这个插件的Activity
            if (BuildConfig.DEBUG)
            {
                Log.d(TAG, "onPluginNotExistsForActivity: Start download... p=" + plugin + "; i=" + intent);
            }
            return super.onPluginNotExistsForActivity(context, plugin, intent, process);
        }
    }

    private class HostEventCallbacks extends RePluginEventCallbacks
    {
        private static final String TAG = "HostEventCallbacks";

        public HostEventCallbacks(Context context)
        {
            super(context);
        }

        @Override
        public void onInstallPluginFailed(String path, InstallResult code)
        {
            // 当插件安装失败时触发此逻辑。您可以在此处做“打点统计”，也可以针对安装失败情况做“特殊处理”
            // 大部分可以通过RePlugin.install的返回值来判断是否成功
            if (BuildConfig.DEBUG)
            {
                Log.d(TAG, "onInstallPluginFailed: Failed! path=" + path + "; r=" + code);
            }
            super.onInstallPluginFailed(path, code);
        }

        @Override
        public void onStartActivityCompleted(String plugin, String activity, boolean result)
        {
            // 当打开Activity成功时触发此逻辑，可在这里做一些APM、打点统计等相关工作
            super.onStartActivityCompleted(plugin, activity, result);
        }
    }
}
