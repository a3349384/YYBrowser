package cn.zmy.browser;

import android.content.Context;
import android.content.Intent;

import cn.zmy.browser.common.IntentKeys;
import cn.zmy.browser.search.SearchActivity;
import cn.zmy.browser.web.WebActivity;
import cn.zmy.common.utils.Util;

/**
 * Created by zmy on 2017/11/15.
 */

public class Navigator
{
    public static void launcherToSearchActivity(Context context)
    {
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }

    public static void launcherToWebActivity(Context context, String url)
    {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(IntentKeys.KEY_URL, Util.nullToDefault(url));
        context.startActivity(intent);
    }
}
