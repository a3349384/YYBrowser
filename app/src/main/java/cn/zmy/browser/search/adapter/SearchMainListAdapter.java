package cn.zmy.browser.search.adapter;

import android.databinding.ViewDataBinding;
import android.view.ViewGroup;

import cn.zmy.browser.R;
import cn.zmy.browser.search.model.SearchRecord;
import cn.zmy.common.binding.adapter.HeaderFooterBindingAdapter;

/**
 * Created by zmy on 2017/11/26.
 */

public class SearchMainListAdapter extends HeaderFooterBindingAdapter<SearchRecord>
{
    @Override
    public int getHeaderCount()
    {
        return 1;
    }

    @Override
    public int getFooterCount()
    {
        return 1;
    }

    @Override
    public int getHeaderLayout(int position)
    {
        return R.layout.item_search_record_header;
    }

    @Override
    public int getFooterLayout(int position)
    {
        return R.layout.item_search_record_footer;
    }

    @Override
    public int getItemLayout(int viewType)
    {
        return R.layout.item_search_record;
    }

    @Override
    public void onBindHeader(ViewDataBinding binding, int position)
    {

    }

    @Override
    public void onBindFooter(ViewDataBinding binding, int position)
    {

    }

    @Override
    public void onBindItem(ViewDataBinding binding, SearchRecord item)
    {

    }
}
