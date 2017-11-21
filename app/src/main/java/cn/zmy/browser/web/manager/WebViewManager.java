package cn.zmy.browser.web.manager;

import android.webkit.WebView;

/**
 * Created by zmy on 2017/11/21.
 */

public class WebViewManager
{
    public static WebViewManager getInstance()
    {
        return SingletonInner.instance;
    }

    private WebViewManager()
    {

    }

    private WebView mWebViewCurrent;

    public WebView getCurrent()
    {
        return mWebViewCurrent;
    }

    public void setCurrent(WebView webView)
    {
        mWebViewCurrent = webView;
    }

    private static class SingletonInner
    {
        private static WebViewManager instance = new WebViewManager();
    }
}
