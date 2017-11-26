package cn.zmy.browser.search.model;

/**
 * Created by zmy on 2017/11/26.
 * 搜索记录模型
 */

public class SearchRecord
{
    private long id;

    private String name;

    private String url;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
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

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }
}
