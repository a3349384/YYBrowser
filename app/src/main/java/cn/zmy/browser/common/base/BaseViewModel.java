package cn.zmy.browser.common.base;

/**
 * Created by zmy on 2017/11/26.
 */

public abstract class BaseViewModel<M>
{
    protected M mModel;

    public BaseViewModel()
    {

    }

    public BaseViewModel(M model)
    {
        mModel = model;
    }

    public M getModel()
    {
        return mModel;
    }
}
