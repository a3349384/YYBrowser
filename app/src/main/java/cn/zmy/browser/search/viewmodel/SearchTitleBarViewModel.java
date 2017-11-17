package cn.zmy.browser.search.viewmodel;

import android.util.Log;

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
    private ItemSelectWindow.OnSelectedChangedListener mOnSelectedChangedListener;

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
        if (mOnSelectedChangedListener == null)
        {
            mOnSelectedChangedListener = position ->
            {
                Log.d("search", "" + position);
            };
        }
        new ItemSelectWindow.Builder(ContextManager.getInstance().getActivityContext())
                .setAdapter(mAdapter)
                .setTitle(R.string.str_select_search_engine_tip)
                .setPreSelectedPosition(0)
                .setOnSelectedChangedListener(mOnSelectedChangedListener)
                .build()
                .show();
    }
}
