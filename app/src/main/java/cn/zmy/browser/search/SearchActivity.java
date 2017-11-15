package cn.zmy.browser.search;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        StatusBarUtil.setTransparent(this);
        ActivitySearchBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_search);

        mSearchTitleBarViewModel = new SearchTitleBarViewModel();
        binding.includeSearchTitleBar.setSearchTitleBarViewModel(mSearchTitleBarViewModel);
    }

    @Override
    public void finish()
    {
        super.finish();
        overridePendingTransition(0,0);
    }
}
