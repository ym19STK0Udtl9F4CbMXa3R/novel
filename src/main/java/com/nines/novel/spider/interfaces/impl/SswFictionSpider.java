package com.nines.novel.spider.interfaces.impl;

import java.util.List;

/**
 * @ClassName: SswFictionSpider
 * @Description: 小说列表爬虫搜书网实现类
 * @author: Nines
 * @date: 2020年04月07日 19:19
 */
public class SswFictionSpider extends DefaultFictionSpider {

    @Override
    public List<String> getFiction(String url, int tryTimes) {
        return super.getFiction(url, tryTimes);
    }
}
