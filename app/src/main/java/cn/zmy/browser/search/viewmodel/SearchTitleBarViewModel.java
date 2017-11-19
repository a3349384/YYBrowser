package cn.zmy.browser.search.viewmodel;

import android.text.TextUtils;

import cn.zmy.browser.Navigator;
import cn.zmy.browser.common.manager.ContextManager;
import cn.zmy.browser.search.ChangeSearchEngineWindow;
import cn.zmy.browser.search.model.SearchTitleBarModel;
import cn.zmy.browser.search.strategy.SearchUrlGeneratorManager;
import cn.zmy.browser.setting.data.SearchEngineManager;

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

    public void onSearchClick()
    {
        String searchWords = mModel.getText();
        if (TextUtils.isEmpty(searchWords.trim()))
        {
            return;
        }
        if (mModel.getLeftIconLevel() == SearchTitleBarModel.LEVEL_SEARCH)
        {
            searchWords = SearchUrlGeneratorManager.getInstance().from(
                    SearchEngineManager.getInstance().getCurrentSearchEngine().getId()).generate(searchWords);
        }
        Navigator.launcherToWebActivity(ContextManager.getInstance().getActivityContext(), searchWords);
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
