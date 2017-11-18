package cn.zmy.browser.setting.data;

import cn.zmy.browser.common.IReader;
import cn.zmy.browser.common.manager.ContextManager;
import cn.zmy.common.utils.SharedPreferencesUtil;

/**
 * Created by zmy on 2017/11/18.
 * 使用SharedPreference读取当前用户选择的搜索引擎
 */

public class SharedPreferenceSearchEngineReader implements IReader<Integer>
{
    @Override
    public Integer read()
    {
        return SharedPreferencesUtil.getInt(ContextManager.getInstance().getAppContext(),
                "search_engine_setting",
                "current_search_engine", 0);
    }
}
