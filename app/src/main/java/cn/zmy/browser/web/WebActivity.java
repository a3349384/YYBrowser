package cn.zmy.browser.web;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.webkit.WebView;

import cn.zmy.browser.R;
import cn.zmy.browser.common.IntentKeys;
import cn.zmy.browser.databinding.ActivityWebBinding;
import cn.zmy.browser.web.factory.WebViewFactory;
import cn.zmy.common.utils.ScreenUtil;

/**
 * Created by zmy on 2017/11/19.
 */

public class WebActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        String url = null;
        if (getIntent() == null || TextUtils.isEmpty((url = getIntent().getStringExtra(IntentKeys.KEY_URL))))
        {
            finish();
            return;
        }

        ActivityWebBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_web);
        WebView webView = WebViewFactory.getInstance().getWebView(this);
        CoordinatorLayout root = (CoordinatorLayout) binding.getRoot();
        CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(0, ScreenUtil.dip2Px(48), 0, ScreenUtil.dip2Px(48));
        root.addView(webView, layoutParams);
        webView.loadUrl(url);
    }
}
