package cn.zmy.browser.setting.data;

import android.content.Context;

import cn.zmy.browser.R;
import cn.zmy.browser.common.IReader;
import cn.zmy.browser.common.IWriter;
import cn.zmy.browser.common.manager.ContextManager;
import cn.zmy.browser.search.model.SearchEngine;
import cn.zmy.common.utils.ResUtil;

/**
 * Created by zmy on 2017/11/18.
 */

public class SearchEngineManager
{
    public static SearchEngineManager getInstance()
    {
        return SearchEngineManager.SingletonInner.instance;
    }

    private Context mContext;
    private SearchEngine[] mSearchEngines;
    private IReader<Integer> mSearchEngineReader;
    private IWriter<Integer> mSearchEngineWriter;

    private SearchEngineManager()
    {
        mContext = ContextManager.getInstance().getAppContext();
        mSearchEngines = new SearchEngine[3];
        SearchEngine searchEngineSogou = new SearchEngine(1, ResUtil.getString(mContext, R.string.str_sogou), ResUtil.getDrawable(mContext, R.drawable.ic_sogou));
        SearchEngine searchEngineBaidu = new SearchEngine(2, ResUtil.getString(mContext, R.string.str_baidu), ResUtil.getDrawable(mContext, R.drawable.ic_baidu));
        SearchEngine searchEngineGoogle = new SearchEngine(3, ResUtil.getString(mContext, R.string.str_google), ResUtil.getDrawable(mContext, R.drawable.ic_google));
        mSearchEngines[0] = searchEngineSogou;
        mSearchEngines[1] = searchEngineBaidu;
        mSearchEngines[2] = searchEngineGoogle;

        mSearchEngineReader = new SharedPreferenceSearchEngineReader();
        mSearchEngineWriter = new SharedPreferenceSearchEngineWriter();
    }

    public SearchEngine[] getAllSearchEngines()
    {
        return mSearchEngines;
    }

    public SearchEngine getCurrentSearchEngine()
    {
        Integer id = mSearchEngineReader.read();
        SearchEngine searchEngine = null;
        if (id == null)
        {
            //使用第一个作为默认搜索引擎
            searchEngine = mSearchEngines[0];
            mSearchEngineWriter.write(searchEngine.getId());
        }
        else
        {
            try
            {
                searchEngine = mSearchEngines[id - 1];
            }
            catch (Exception ex)
            {
                //数据出现错误，需要重置
                searchEngine = mSearchEngines[0];
                mSearchEngineWriter.write(searchEngine.getId());
            }
        }
        return searchEngine;
    }

    public void setCurrentSearchEngine(SearchEngine searchEngine)
    {
        mSearchEngineWriter.write(searchEngine.getId());
    }

    private static class SingletonInner
    {
        private static SearchEngineManager instance = new SearchEngineManager();
    }
}
