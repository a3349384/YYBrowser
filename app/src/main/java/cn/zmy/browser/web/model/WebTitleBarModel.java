package cn.zmy.browser.web.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.widget.PopupWindow;

import cn.zmy.browser.BR;

/**
 * Created by zmy on 2017/11/21.
 * 浏览页TitleBar模型
 */

public class WebTitleBarModel extends BaseObservable
{
    private String title; //网页标题
    private int progress; //进度条进度值
    private boolean progressVisible; //进度条的可见性（通常在页面加载前以及页面加载完成时，进度条不可见）

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

    @Bindable
    public int getProgress()
    {
        return progress;
    }

    public void setProgress(int progress)
    {
        if (this.progress != progress)
        {
            this.progress = progress;
            notifyPropertyChanged(BR.progress);
        }
    }

    @Bindable
    public boolean isProgressVisible()
    {
        return progressVisible;
    }

    public void setProgressVisible(boolean progressVisible)
    {
        this.progressVisible = progressVisible;
        notifyPropertyChanged(BR.progressVisible);
    }
}
