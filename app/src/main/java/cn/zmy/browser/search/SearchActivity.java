package cn.zmy.browser.search;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.jaeger.library.StatusBarUtil;

import cn.zmy.browser.R;
import cn.zmy.browser.databinding.ActivitySearchBinding;
import cn.zmy.browser.search.viewmodel.SearchTitleBarViewModel;

/**
 * Created by zmy on 2017/11/15.
 */

public class SearchActivity extends AppCompatActivity
{
    private SearchTitleBarViewModel mSearchTitleBarViewModel;
    private EditText mEditTextSearchContent;
    private boolean mIsNeedAutoShowKeyboard;//标志是否需要自动显示软键盘

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mIsNeedAutoShowKeyboard = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        StatusBarUtil.setTransparent(this);
        ActivitySearchBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_search);

        mSearchTitleBarViewModel = new SearchTitleBarViewModel();
        mEditTextSearchContent = binding.includeSearchTitleBar.editTextSearchContent;
        binding.includeSearchTitleBar.setVm(mSearchTitleBarViewModel);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && mIsNeedAutoShowKeyboard)
        {
            mIsNeedAutoShowKeyboard = false;
            mEditTextSearchContent.requestFocus();
            mEditTextSearchContent.postDelayed(() -> {
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputManager != null && !isFinishing())
                {
                    inputManager.showSoftInput(mEditTextSearchContent, 0);
                }
            }, 100);
        }
    }

    @Override
    public void finish()
    {
        super.finish();
        overridePendingTransition(0,0);
    }
}
