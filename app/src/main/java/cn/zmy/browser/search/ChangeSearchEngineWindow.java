package cn.zmy.browser.search;

import cn.zmy.browser.R;
import cn.zmy.browser.common.manager.ContextManager;
import cn.zmy.browser.search.adapter.SearchEngineAdapter;
import cn.zmy.browser.search.model.SearchEngine;
import cn.zmy.browser.setting.data.SearchEngineManager;
import cn.zmy.browser.widget.ItemSelectWindow;

/**
 * Created by zmy on 2017/11/19.
 * 修改搜索引擎Window
 */

public class ChangeSearchEngineWindow
{
    private SearchEngineAdapter mAdapter;
    private ItemSelectWindow.OnSelectedChangedListener mOnSelectedChangedListener;

    public void show()
    {
        if (mAdapter == null)
        {
            mAdapter = new SearchEngineAdapter();
        }
        if (mOnSelectedChangedListener == null)
        {
            mOnSelectedChangedListener = position ->
            {
                SearchEngine[] searchEngines = SearchEngineManager.getInstance().getAllSearchEngines();
                SearchEngineManager.getInstance().setCurrentSearchEngine(searchEngines[position]);
            };
        }
        new ItemSelectWindow.Builder(ContextManager.getInstance().getActivityContext())
                .setAdapter(mAdapter)
                .setTitle(R.string.str_select_search_engine_tip)
                .setPreSelectedPosition(getPreSelectedPosition())
                .setOnSelectedChangedListener(mOnSelectedChangedListener)
                .build()
                .show();
    }

    private int getPreSelectedPosition()
    {
        SearchEngine searchEngineCurrent = SearchEngineManager.getInstance().getCurrentSearchEngine();
        if (searchEngineCurrent == null)
        {
            return 0;
        }
        SearchEngine[] searchEngines = SearchEngineManager.getInstance().getAllSearchEngines();
        for (int i = 0; i < searchEngines.length; i++)
        {
            if (searchEngines[i].getId() == searchEngineCurrent.getId())
            {
                return i;
            }
        }
        return 0;
    }
}
