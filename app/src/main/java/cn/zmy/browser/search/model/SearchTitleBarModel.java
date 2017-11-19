package cn.zmy.browser.search.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;

import cn.zmy.browser.BR;
import cn.zmy.browser.R;
import cn.zmy.browser.common.manager.ContextManager;
import cn.zmy.browser.util.UrlUtil;
import cn.zmy.common.utils.Util;

/**
 * Created by zmy on 2017/11/19.
 * 网址输入页面顶部Title模型
 */

public class SearchTitleBarModel extends BaseObservable
{
    public static int LEVEL_VOICE = 0;
    public static int LEVEL_X = 10;
    public static int LEVEL_SEARCH = 0;
    public static int LEVEL_SITE = 10;

    /**
     * 用于标志左侧Icon的ImageLevel
     * */
    private int leftIconLevel;
    /**
     * 用于标志右侧Icon的ImageLevel
     * */
    private int rightIconLevel;
    /**
     * 用于标志输入的文本
     * */
    private String text;
    /**
     * 提示性文字（搜索or进入）
     * */
    private String searchTip;

    public SearchTitleBarModel()
    {
        leftIconLevel = LEVEL_SEARCH;
        rightIconLevel = LEVEL_VOICE;
        text = "";
        searchTip = ContextManager.getInstance().getAppContext().getResources().getString(R.string.str_search);
    }

    @Bindable
    public int getLeftIconLevel()
    {
        return leftIconLevel;
    }

    public void setLeftIconLevel(int leftIconLevel)
    {
        if (this.leftIconLevel != leftIconLevel)
        {
            this.leftIconLevel = leftIconLevel;
            notifyPropertyChanged(BR.leftIconLevel);
        }
    }

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
        text = Util.nullToDefault(text);
        if (!this.text.contentEquals(text))
        {
            this.text = text.trim();
            notifyPropertyChanged(BR.text);
            onTextChanged();
        }
    }

    @Bindable
    public String getSearchTip()
    {
        return searchTip;
    }

    public void setSearchTip(String searchTip)
    {
        searchTip = Util.nullToDefault(searchTip);
        if (!this.searchTip.contentEquals(searchTip))
        {
            this.searchTip = searchTip;
            notifyPropertyChanged(BR.searchTip);
        }
    }

    private void onTextChanged()
    {
        boolean isValidWebUrl = UrlUtil.isValidWebUrl(this.text);
        if (isValidWebUrl)
        {
            setLeftIconLevel(LEVEL_SITE);
            setSearchTip(ContextManager.getInstance().getAppContext().getResources().getString(R.string.str_enter));
        }
        else
        {
            setLeftIconLevel(LEVEL_SEARCH);
            setSearchTip(ContextManager.getInstance().getAppContext().getResources().getString(R.string.str_search));
        }
        setRightIconLevel(TextUtils.isEmpty(this.text) ? LEVEL_VOICE : LEVEL_X);
    }
}
