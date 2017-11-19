package cn.zmy.browser.search.strategy;

/**
 * Created by zmy on 2017/11/19.
 * 根据搜索关键词获得具体的网址的接口
 */

public interface SearchUrlGenerator
{
    String generate(String searchWords);
}
