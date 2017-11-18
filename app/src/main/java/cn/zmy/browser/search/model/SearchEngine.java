package cn.zmy.browser.search.model;

import android.graphics.drawable.Drawable;

/**
 * Created by zmy on 2017/11/18.
 * 搜索引擎模型
 */

public class SearchEngine
{
    private int id;
    private String name;
    private Drawable icon;

    public SearchEngine(int id, String name, Drawable icon)
    {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Drawable getIcon()
    {
        return icon;
    }

    public void setIcon(Drawable icon)
    {
        this.icon = icon;
    }
}
