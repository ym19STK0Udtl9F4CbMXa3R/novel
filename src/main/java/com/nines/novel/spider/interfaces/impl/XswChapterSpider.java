package com.nines.novel.spider.interfaces.impl;

import com.nines.novel.entity.Fiction;
import com.nines.novel.spider.interfaces.abs.AbstractChapterSpider;

import java.util.Map;

/**
 * @ClassName: XswChapterSpider
 * @Description: 小说屋小说信息与章节列表实现类
 * @author: Nines
 * @date: 2020年04月10日 23:50
 */
public class XswChapterSpider extends AbstractChapterSpider {

    private static final String INTROSTAR = "：";
    private static final String INTROEND = "通过键盘左右方向键";

    @Override
    public Map<String, Object> getChapters(String url, int tryTimes) {
        Map<String, Object> map = super.getChapters(url, tryTimes);
        Fiction fiction = (Fiction) map.get("fiction");
        String name = fiction.getName();
        fiction.setName(name.substring(1, name.indexOf("》")));
        String intro = fiction.getIntro();
        fiction.setIntro(intro.substring(intro.indexOf(INTROSTAR) + 1, intro.lastIndexOf(INTROEND)));
        return map;
    }
}
