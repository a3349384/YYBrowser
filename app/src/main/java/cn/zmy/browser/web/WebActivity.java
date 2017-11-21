package cn.zmy.browser.web;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import cn.zmy.browser.R;
import cn.zmy.browser.common.IntentKeys;
import cn.zmy.browser.databinding.ActivityWebBinding;
import cn.zmy.browser.web.factory.WebViewFactory;
import cn.zmy.browser.web.manager.WebViewManager;
import cn.zmy.browser.web.viewmodel.WebTitleBarViewModel;

/**
 * Created by zmy on 2017/11/19.
 */

public class WebActivity extends AppCompatActivity
{
    private String mUrl;
    private WebView mWebView;
    private TheWebChromeClient mWebChromeClient;
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

        ActivityWebBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_web);
        mWebView = WebViewFactory.getInstance().createWebView(this);
        WebViewManager.getInstance().setCurrent(mWebView);
        binding.webViewContainer.addView(mWebView);

        mWebTitleBarViewModel= new WebTitleBarViewModel();
        mWebTitleBarViewModel.getModel().setTitle(mUrl);
        binding.webTitleBar.setVm(mWebTitleBarViewModel);

        mWebView.setWebChromeClient(mWebChromeClient);
        mWebView.loadUrl(mUrl);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        WebViewManager.getInstance().setCurrent(null);
        mWebView.removeAllViews();
        mWebView.destroy();
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
    }
}
