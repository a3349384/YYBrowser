package cn.zmy.browser.home.viewmodel;

import cn.zmy.browser.Navigator;
import cn.zmy.browser.common.manager.ContextManager;

/**
 * Created by zmy on 2017/11/15.
 */

public class HomeInputUrlViewModel
{
    public void onInputClick()
    {
        Navigator.launcherToSearchActivity(ContextManager.getInstance().getActivityContext());
    }
}
