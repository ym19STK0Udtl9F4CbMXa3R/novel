package com.nines.novel.util;

import com.nines.novel.spider.interfaces.IFictionSpider;
import com.nines.novel.spider.interfaces.impl.BxwxFictionSpider;
import com.nines.novel.spider.interfaces.impl.DefaultFictionSpider;

/**
 * @ClassName: FictionSpiderFactory
 * @Description: 小说列表爬虫工厂
 * @author: Nines
 * @date: 2020年04月11日 14:49
 */
public class FictionSpiderFactory {

    public static IFictionSpider getFictionSpider(String url){
        NovelSiteEnum site = NovelSiteEnum.getSiteByUrl(url);
        IFictionSpider fictionSpider;
        switch (site){
            case BIXIAWENXUE:
                fictionSpider = new BxwxFictionSpider();
                break;
            case SOUSHUWANG:
            case DINGDIANXIAOSHUO:
            case XIAOSHUOWU:
                fictionSpider = new DefaultFictionSpider();
                break;
            default:
                throw new RuntimeException("不支持的小说网站");
        }
        return fictionSpider;
    }

}
