package cn.zmy.browser.search.adapter;

import android.graphics.drawable.Drawable;

import cn.zmy.browser.R;
import cn.zmy.browser.manager.ContextManager;
import cn.zmy.browser.widget.ItemSelectWindow;

/**
 * Created by zmy on 2017/11/15.
 */

public class SearchEngineAdapter implements ItemSelectWindow.Adapter
{
    private String[] mContents;

    public SearchEngineAdapter()
    {
        mContents = ContextManager.getInstance().getAppContext().getResources().getStringArray(R.array.str_search_engines);
    }

    @Override
    public int getCount()
    {
        return mContents.length;
    }

    @Override
    public Drawable getIcon(int position)
    {
        switch (position)
        {
            case 0:
            {
                return ContextManager.getInstance().getAppContext().getResources().getDrawable(R.drawable.ic_sogou);
            }
            case 1:
            {
                return ContextManager.getInstance().getAppContext().getResources().getDrawable(R.drawable.ic_baidu);
            }
            case 2:
            {
                return ContextManager.getInstance().getAppContext().getResources().getDrawable(R.drawable.ic_google);
            }
            default:
            {
                return null;
            }
        }
    }

    @Override
    public String getContent(int position)
    {
        return mContents[position];
    }
}
