package cn.zmy.browser.util;

/**
 * Created by zmy on 2017/11/19.
 */

public class UrlUtil
{
    /**
     * 判断文本是否是一个合法的网页url
     * */
    public static boolean isValidWebUrl(String text)
    {
        return text.matches("(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]");
    }
}
