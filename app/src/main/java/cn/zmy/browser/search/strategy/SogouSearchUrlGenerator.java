package cn.zmy.browser.search.strategy;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import cn.zmy.common.utils.Util;

/**
 * Created by zmy on 2017/11/19.
 */

public class SogouSearchUrlGenerator implements SearchUrlGenerator
{
    private String mBase = "https://m.sogou.com/web/searchList.jsp?keyword=";

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
