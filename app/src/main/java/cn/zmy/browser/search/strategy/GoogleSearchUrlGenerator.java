package cn.zmy.browser.search.strategy;

import java.net.URLEncoder;

import cn.zmy.common.utils.Util;

/**
 * Created by zmy on 2017/11/19.
 */

public class GoogleSearchUrlGenerator implements SearchUrlGenerator
{
    private String mBase = "http://www.google.com.hk/m/search?q=";

    @Override
    public String generate(String searchWords)
    {
        try
        {
            return mBase + URLEncoder.encode(Util.nullToDefault(searchWords), "utf-8");
        }
        catch (Exception e)
        {
            return mBase;
        }
    }
}
