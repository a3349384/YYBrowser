package cn.zmy.browser.web.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import cn.zmy.browser.BR;

/**
 * Created by zmy on 2017/11/21.
 * 浏览页TitleBar模型
 */

public class WebTitleBarModel extends BaseObservable
{
    private String title;

    @Bindable
    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }
}
