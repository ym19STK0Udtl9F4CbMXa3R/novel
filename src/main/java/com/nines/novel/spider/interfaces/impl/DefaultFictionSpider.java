package com.nines.novel.spider.interfaces.impl;

import com.nines.novel.spider.interfaces.abs.AbstractFictionSpider;

import java.util.List;

/**
 * @ClassName: DefaultFictionSpider
 * @Description: 小说列表爬虫默认实现类
 * @author: Nines
 * @date: 2020年04月07日 18:18
 */
public class DefaultFictionSpider extends AbstractFictionSpider {
    @Override
    public List<String> getTotalPage(String url, int tryTimes) {
        return null;
    }
}
