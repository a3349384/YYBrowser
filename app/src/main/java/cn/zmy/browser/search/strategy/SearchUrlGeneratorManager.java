package cn.zmy.browser.search.strategy;

import cn.zmy.browser.setting.data.SearchEngineManager;

/**
 * Created by zmy on 2017/11/19.
 * 对各种搜索关键词->网址的策略进行管理
 */

public class SearchUrlGeneratorManager
{
    public static SearchUrlGeneratorManager getInstance()
    {
        return SearchUrlGeneratorManager.SingletonInner.instance;
    }

    private SearchUrlGenerator mSogouSearchUrlGenerator;
    private SearchUrlGenerator mBaiduSearchUrlGenerator;
    private SearchUrlGenerator mGoogleSearchUrlGenerator;

    private SearchUrlGeneratorManager()
    {
        //考虑到用户可能经常进行搜索，所以这里直接将实例化的对象保存起来
        mSogouSearchUrlGenerator = new SogouSearchUrlGenerator();
        mBaiduSearchUrlGenerator = new BaiduSearchUrlGenerator();
        mGoogleSearchUrlGenerator = new GoogleSearchUrlGenerator();
    }

    public SearchUrlGenerator from(int searchEngineId)
    {
        switch (searchEngineId)
        {
            case SearchEngineManager.SEARCH_ENGINE_SOGOU_ID:
            {
                return mSogouSearchUrlGenerator;
            }
            case SearchEngineManager.SEARCH_ENGINE_BAIDU_ID:
            {
                return mBaiduSearchUrlGenerator;
            }
            case SearchEngineManager.SEARCH_ENGINE_GOOGLE_ID:
            {
                return mGoogleSearchUrlGenerator;
            }
            default:
            {
                return null;
            }
        }
    }

    private static class SingletonInner
    {
        private static SearchUrlGeneratorManager instance = new SearchUrlGeneratorManager();
    }
}
