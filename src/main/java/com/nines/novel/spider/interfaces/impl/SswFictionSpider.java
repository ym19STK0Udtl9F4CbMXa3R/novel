package com.nines.novel.spider.interfaces.impl;

import com.nines.novel.util.NovelSiteEnum;
import com.nines.novel.util.SpiderSiteUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SswFictionSpider
 * @Description: 小说列表爬虫搜书网实现类
 * @author: Nines
 * @date: 2020年04月07日 19:19
 */
public class SswFictionSpider extends DefaultFictionSpider {

    @Override
    public List<String> getFiction(String url) {
        return super.getFiction(url);
    }
}
