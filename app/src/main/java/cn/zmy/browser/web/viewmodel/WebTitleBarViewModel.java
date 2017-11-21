package cn.zmy.browser.web.viewmodel;

import cn.zmy.browser.web.controller.WebViewController;
import cn.zmy.browser.web.model.WebTitleBarModel;

/**
 * Created by zmy on 2017/11/21.
 */

public class WebTitleBarViewModel
{
    private WebTitleBarModel mModel;

    public WebTitleBarViewModel()
    {
        mModel = new WebTitleBarModel();
    }

    public WebTitleBarModel getModel()
    {
        return mModel;
    }

    public void onRefreshClick()
    {
        WebViewController.refresh();
    }
}
