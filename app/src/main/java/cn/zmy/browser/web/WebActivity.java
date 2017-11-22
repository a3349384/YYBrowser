package cn.zmy.browser.web;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cn.zmy.browser.R;
import cn.zmy.browser.common.IntentKeys;
import cn.zmy.browser.databinding.ActivityWebBinding;
import cn.zmy.browser.web.factory.WebViewFactory;
import cn.zmy.browser.web.manager.WebViewManager;
import cn.zmy.browser.web.viewmodel.WebTitleBarViewModel;
import cn.zmy.common.utils.ReflectUtil;

/**
 * Created by zmy on 2017/11/19.
 */

public class WebActivity extends AppCompatActivity
{
    private String mUrl;
    private WebView mWebView;
    private TheWebChromeClient mWebChromeClient;
    private TheWebViewClient mWebViewClient;
    private WebTitleBarViewModel mWebTitleBarViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getIntent() == null || TextUtils.isEmpty((mUrl = getIntent().getStringExtra(IntentKeys.KEY_URL))))
        {
            finish();
            return;
        }

        mWebChromeClient = new TheWebChromeClient();
        mWebViewClient = new TheWebViewClient();

        ActivityWebBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_web);
        mWebView = WebViewFactory.getInstance().createWebView(this);
        WebViewManager.getInstance().setCurrent(mWebView);
        binding.webViewContainer.addView(mWebView);

        mWebTitleBarViewModel = new WebTitleBarViewModel();
        mWebTitleBarViewModel.getModel().setTitle(mUrl);
        binding.webTitleBar.setVm(mWebTitleBarViewModel);

        mWebView.setWebChromeClient(mWebChromeClient);
        mWebView.setWebViewClient(mWebViewClient);
        mWebView.loadUrl(mUrl);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        WebViewManager.getInstance().setCurrent(null);
        if (mWebView != null)
        {
            ViewParent parent = mWebView.getParent();
            if (parent != null)
            {
                ((ViewGroup) parent).removeView(mWebView);
            }
            mWebView.stopLoading();
            mWebView.getSettings().setJavaScriptEnabled(false);
            mWebView.clearView();
            mWebView.removeAllViews();
            try
            {
                mWebView.destroy();
            }
            catch (Throwable ex)
            {

            }
        }
        mWebChromeClient = null;
        mWebViewClient = null;
    }

    private class TheWebChromeClient extends WebChromeClient
    {
        @Override
        public void onReceivedTitle(WebView view, String title)
        {
            super.onReceivedTitle(view, title);
            if (view == mWebView)
            {
                mWebTitleBarViewModel.getModel().setTitle(title);
            }
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress)
        {
            super.onProgressChanged(view, newProgress);
            if (view == mWebView)
            {
                mWebTitleBarViewModel.getModel().setProgress(newProgress);
            }
        }

        @Override
        public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg)
        {
            return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
        }
    }

    private class TheWebViewClient extends WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon)
        {
            super.onPageStarted(view, url, favicon);
            mWebTitleBarViewModel.getModel().setProgressVisible(true);
        }

        @Override
        public void onPageFinished(WebView view, String url)
        {
            super.onPageFinished(view, url);
            mWebTitleBarViewModel.getModel().setProgressVisible(false);
        }
    }
}
