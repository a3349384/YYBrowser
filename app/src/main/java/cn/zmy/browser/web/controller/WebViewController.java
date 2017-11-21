package cn.zmy.browser.web.controller;

import android.webkit.WebView;

import cn.zmy.browser.web.manager.WebViewManager;

/**
 * Created by zmy on 2017/11/21.
 */

public class WebViewController
{
    public static void refresh()
    {
        WebView webView = WebViewManager.getInstance().getCurrent();
        if (webView != null)
        {
            webView.reload();
        }
    }
}
