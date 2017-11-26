package cn.zmy.browser.search.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by zmy on 2017/11/26.
 * 搜索记录模型
 */

@Entity
public class SearchRecord
{
    @Id
    private Long id;

    private String name;

    private String url;

    @Generated(hash = 1775834249)
    public SearchRecord(Long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    @Generated(hash = 839789598)
    public SearchRecord() {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
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
