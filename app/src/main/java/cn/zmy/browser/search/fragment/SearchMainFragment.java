package cn.zmy.browser.search.fragment;

import java.util.ArrayList;
import java.util.List;

import cn.zmy.browser.search.adapter.SearchMainListAdapter;
import cn.zmy.browser.search.model.SearchRecord;
import cn.zmy.common.binding.adapter.BaseBindingAdapter;
import cn.zmy.common.binding.fragment.BaseBindListFragment;

/**
 * Created by zmy on 2017/11/26.
 */

public class SearchMainFragment extends BaseBindListFragment<SearchRecord>
{
    public static final String TAG = SearchMainFragment.class.getName();

    private SearchMainListAdapter mAdapter;

    public static SearchMainFragment newInstance()
    {
        return new SearchMainFragment();
    }

    @Override
    protected BaseBindingAdapter onCreateBindingAdapter()
    {
        if (mAdapter == null)
        {
            mAdapter = new SearchMainListAdapter();
        }
        return mAdapter;
    }

    @Override
    protected List<SearchRecord> getItems(int pageIndex)
    {
//        SearchRecord record = new SearchRecord();
//        record.setName("Test");
//
//        ArrayList<SearchRecord> searchRecords = new ArrayList<>();
//        searchRecords.add(record);
//        return searchRecords;
        return new ArrayList<>();
    }
}
