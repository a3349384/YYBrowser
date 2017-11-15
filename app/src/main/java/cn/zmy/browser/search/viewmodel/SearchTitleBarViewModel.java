package cn.zmy.browser.search.viewmodel;

import cn.zmy.browser.manager.ContextManager;

/**
 * Created by zmy on 2017/11/15.
 */

public class SearchTitleBarViewModel
{
    public void onCancelClick()
    {
        ContextManager.getInstance().getActivityContext().finish();
    }
}
