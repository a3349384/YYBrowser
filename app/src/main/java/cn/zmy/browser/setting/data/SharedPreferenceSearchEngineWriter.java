package cn.zmy.browser.setting.data;

import cn.zmy.browser.common.IWriter;
import cn.zmy.browser.common.manager.ContextManager;
import cn.zmy.common.utils.SharedPreferencesUtil;

/**
 * Created by zmy on 2017/11/18.
 * 使用SharedPreference保存用户当前选择的搜索引擎
 */

public class SharedPreferenceSearchEngineWriter implements IWriter<Integer>
{
    @Override
    public boolean write(Integer integer)
    {
        SharedPreferencesUtil.setInt(ContextManager.getInstance().getAppContext(),
                "search_engine_setting",
                "current_search_engine", integer);
        return true;
    }
}
