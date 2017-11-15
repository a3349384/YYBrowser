package cn.zmy.browser;

import android.content.Context;
import android.content.Intent;

import cn.zmy.browser.search.SearchActivity;

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
}
