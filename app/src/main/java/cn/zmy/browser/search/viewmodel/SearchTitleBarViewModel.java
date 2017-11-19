package cn.zmy.browser.search.viewmodel;

import cn.zmy.browser.common.manager.ContextManager;
import cn.zmy.browser.search.ChangeSearchEngineWindow;
import cn.zmy.browser.search.model.SearchTitleBarModel;

/**
 * Created by zmy on 2017/11/15.
 */

public class SearchTitleBarViewModel
{
    private SearchTitleBarModel mModel;

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

    public void onLeftIconClick()
    {
        if (mModel.getLeftIconLevel() == SearchTitleBarModel.LEVEL_SEARCH)
        {
            new ChangeSearchEngineWindow().show();
        }
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
}
