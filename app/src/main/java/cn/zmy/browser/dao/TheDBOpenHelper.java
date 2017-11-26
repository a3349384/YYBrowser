package cn.zmy.browser.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by zmy on 2017/11/26.
 */

public class TheDBOpenHelper extends DaoMaster.OpenHelper
{
    public TheDBOpenHelper(Context context, String name)
    {
        super(context, name);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        super.onUpgrade(db, oldVersion, newVersion);
    }
}

