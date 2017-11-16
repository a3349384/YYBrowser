package cn.zmy.browser.search.viewmodel;

import cn.zmy.browser.R;
import cn.zmy.browser.manager.ContextManager;
import cn.zmy.browser.search.adapter.SearchEngineAdapter;
import cn.zmy.browser.widget.ItemSelectWindow;

/**
 * Created by zmy on 2017/11/15.
 */

public class SearchTitleBarViewModel
{
    private SearchEngineAdapter mAdapter;

    public void onCancelClick()
    {
        ContextManager.getInstance().getActivityContext().finish();
    }

    public void onChangeSearchEngineClick()
    {
        if (mAdapter == null)
        {
            mAdapter = new SearchEngineAdapter();
        }
        new ItemSelectWindow.Builder(ContextManager.getInstance().getActivityContext())
                .setAdapter(mAdapter)
                .setTitle(R.string.str_select_search_engine_tip)
                .setPreSelectedPosition(0)
                .build()
                .show();
    }
}
