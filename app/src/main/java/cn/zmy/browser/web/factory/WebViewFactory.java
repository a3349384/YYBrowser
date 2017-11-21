package cn.zmy.browser.web.factory;

import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.io.File;

import cn.zmy.browser.setting.data.SearchEngineManager;

/**
 * Created by zmy on 2017/11/21.
 */

public class WebViewFactory
{
    public static WebViewFactory getInstance()
    {
        return WebViewFactory.SingletonInner.instance;
    }

    private WebViewFactory()
    {

    }

    public WebView getWebView(Context context)
    {
        File fileCacheRoot = context.getCacheDir();

        WebView webView = new WebView(context);
        WebSettings settings = webView.getSettings();

        //js相关
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(false);

        settings.setAllowContentAccess(true);
        settings.setAllowFileAccess(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
        {
            settings.setAllowFileAccessFromFileURLs(true);
            settings.setAllowUniversalAccessFromFileURLs(true);
        }
        settings.setAppCacheEnabled(true);
        File fileAppCache = new File(fileCacheRoot, "webAppCache");
        if (!fileAppCache.exists())
        {
            fileAppCache.mkdir();
        }
        settings.setAppCachePath(fileAppCache.getAbsolutePath());

        //图片加载相关
        settings.setBlockNetworkImage(false);
        settings.setLoadsImagesAutomatically(true);

        settings.setBlockNetworkLoads(false);

        //缩放相关
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);

        settings.setCacheMode(WebSettings.LOAD_DEFAULT);

        //这个设置影响所有WebView实例
        settings.setDatabaseEnabled(true);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT)
        {
            File fileDataBase = new File(fileCacheRoot, "webDataBase");
            if (!fileDataBase.exists())
            {
                fileCacheRoot.mkdir();
            }
            settings.setDatabasePath(fileDataBase.getAbsolutePath());
        }

        //字体设置相关
        settings.setDefaultFixedFontSize(16);
        settings.setDefaultFontSize(16);
        settings.setMinimumFontSize(8);
        settings.setMinimumLogicalFontSize(8);
        settings.setDefaultTextEncodingName("UTF-8");
//        settings.setDisabledActionModeMenuItems();

        settings.setDomStorageEnabled(true);
        //地理位置相关
        settings.setGeolocationEnabled(true);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT)
        {
            File fileGeolocationDatabase = new File(fileCacheRoot, "geolocationDatabase");
            if (!fileGeolocationDatabase.exists())
            {
                fileGeolocationDatabase.mkdir();
            }
            settings.setGeolocationDatabasePath(fileGeolocationDatabase.getAbsolutePath());
        }

        settings.setLoadWithOverviewMode(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
        {
            settings.setMediaPlaybackRequiresUserGesture(true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            //大概就是https网页中允许访问http链接
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        settings.setNeedInitialFocus(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            settings.setOffscreenPreRaster(false);
        }
        settings.setSupportMultipleWindows(false);
//        settings.setUserAgentString("");
        return webView;
    }

    private static class SingletonInner
    {
        private static WebViewFactory instance = new WebViewFactory();
    }
}
