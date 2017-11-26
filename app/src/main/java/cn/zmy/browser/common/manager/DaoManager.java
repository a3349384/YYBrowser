package cn.zmy.browser.common.manager;

import cn.zmy.browser.dao.DaoMaster;
import cn.zmy.browser.dao.DaoSession;
import cn.zmy.browser.dao.TheDBOpenHelper;

/**
 * Created by zmy on 2017/11/26.
 */

public class DaoManager
{
    public static DaoManager getInstance()
    {
        return DaoManager.SingletonInner.instance;
    }

    private DaoSession mReadableSession;
    private DaoSession mWritableSession;

    private DaoManager()
    {
        TheDBOpenHelper helper = new TheDBOpenHelper(ContextManager.getInstance().getAppContext(), "data");

        mReadableSession = new DaoMaster(helper.getReadableDb()).newSession();
        mWritableSession = new DaoMaster(helper.getWritableDb()).newSession();
    }

    private static class SingletonInner
    {
        private static DaoManager instance = new DaoManager();
    }
}
