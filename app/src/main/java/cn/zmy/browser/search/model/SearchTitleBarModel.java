package cn.zmy.browser.search.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;

import cn.zmy.browser.BR;

/**
 * Created by zmy on 2017/11/19.
 * 网址输入页面顶部Title模型
 */

public class SearchTitleBarModel extends BaseObservable
{
    public static int LEVEL_VOICE = 0;
    public static int LEVEL_X = 10;

    /**
     * 用于标志右侧Icon的ImageLevel
     * */
    private int rightIconLevel;

    /**
     * 用于标志输入的文本
     * */
    private String text;

    @Bindable
    public int getRightIconLevel()
    {
        return rightIconLevel;
    }

    public void setRightIconLevel(int rightIconLevel)
    {
        if (this.rightIconLevel != rightIconLevel)
        {
            this.rightIconLevel = rightIconLevel;
            notifyPropertyChanged(BR.rightIconLevel);
        }
    }

    @Bindable
    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text.trim();
        setRightIconLevel(TextUtils.isEmpty(this.text) ? LEVEL_VOICE : LEVEL_X);
        notifyPropertyChanged(BR.text);
    }
}
