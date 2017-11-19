package cn.zmy.browser.search.viewmodel;

import cn.zmy.browser.R;
import cn.zmy.browser.common.manager.ContextManager;
import cn.zmy.browser.search.adapter.SearchEngineAdapter;
import cn.zmy.browser.search.model.SearchEngine;
import cn.zmy.browser.search.model.SearchTitleBarModel;
import cn.zmy.browser.setting.data.SearchEngineManager;
import cn.zmy.browser.widget.ItemSelectWindow;

/**
 * Created by zmy on 2017/11/15.
 */

public class SearchTitleBarViewModel
{
    private SearchTitleBarModel mModel;
    private SearchEngineAdapter mAdapter;
    private ItemSelectWindow.OnSelectedChangedListener mOnSelectedChangedListener;

    public SearchTitleBarViewModel()
    {
        mModel = new SearchTitleBarModel();
    }

    public SearchTitleBarModel getModel()
    {
        return mModel;
    }

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

    public void onRightIconClick()
    {
        if (mModel.getRightIconLevel() == SearchTitleBarModel.LEVEL_X)
        {
            mModel.setText("");
        }
        else
        {
            //todo 响应声音输入点击事件
        }
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
