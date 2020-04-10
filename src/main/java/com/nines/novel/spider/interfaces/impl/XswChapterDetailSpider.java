package com.nines.novel.spider.interfaces.impl;

import com.nines.novel.spider.interfaces.abs.AbstractChapterDetailSpider;

import java.util.Map;

/**
 * @ClassName: XswChapterDetailSpider
 * @Description: 小说屋章节信息实现类
 * @author: Nines
 * @date: 2020年04月11日 0:05
 */
public class XswChapterDetailSpider extends AbstractChapterDetailSpider {

    private static final String CONTENTSTAR = "(小说屋 www.xiaoshuowu.com) ";
    private static final String CONTENTEND = "小说屋 www.xiaoshuowu.com 如果您中途有事离开，请按CTRL+D键保存当前页面至收藏夹，以便以后接着观看！";

    @Override
    public Map<String, String> getChapterDetails(String url, int tryTimes) {
        Map<String, String> map = super.getChapterDetails(url, tryTimes);
        String content = map.get("content");
        map.put("content", content.substring(content.indexOf(CONTENTSTAR) + CONTENTSTAR.length(), content.lastIndexOf(CONTENTEND)));
        return map;
    }
}
