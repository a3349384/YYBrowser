package cn.zmy.browser.search.adapter;

import android.graphics.drawable.Drawable;

import cn.zmy.browser.R;
import cn.zmy.browser.common.manager.ContextManager;
import cn.zmy.browser.search.model.SearchEngine;
import cn.zmy.browser.setting.data.SearchEngineManager;
import cn.zmy.browser.widget.ItemSelectWindow;

/**
 * Created by zmy on 2017/11/15.
 */

public class SearchEngineAdapter implements ItemSelectWindow.Adapter
{
    private SearchEngine[] mSearchEngines;

    public SearchEngineAdapter()
    {
        mSearchEngines = SearchEngineManager.getInstance().getAllSearchEngines();
    }

    @Override
    public int getCount()
    {
        return mSearchEngines.length;
    }

    @Override
    public Drawable getIcon(int position)
    {
        return mSearchEngines[position].getIcon();
    }

    @Override
    public String getContent(int position)
    {
        return mSearchEngines[position].getName();
    }
}
