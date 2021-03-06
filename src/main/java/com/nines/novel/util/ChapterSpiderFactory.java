package com.nines.novel.util;

import com.nines.novel.spider.interfaces.impl.DefaultChapterSpider;
import com.nines.novel.spider.interfaces.impl.SswChapterSpider;
import com.nines.novel.spider.interfaces.IChapterSpider;
import com.nines.novel.spider.interfaces.impl.XswChapterSpider;

/**
 * @ClassName: ChapterSpiderFactory
 * @Description: 小说信息，章节列表爬虫工厂
 * @author: Nines
 * @date: 2020年04月02日 22:11
 */
public final class ChapterSpiderFactory {

    private ChapterSpiderFactory(){}

    public static IChapterSpider getChapterSpider(String url){
        IChapterSpider chapterSpider;
        NovelSiteEnum site = NovelSiteEnum.getSiteByUrl(url);
        switch (site){
            case DINGDIANXIAOSHUO:
            case BIXIAWENXUE:
                chapterSpider = new DefaultChapterSpider();
                break;
            case SOUSHUWANG:
                chapterSpider = new SswChapterSpider();
                break;
            case XIAOSHUOWU:
                chapterSpider = new XswChapterSpider();
                break;
            default:
                throw new RuntimeException("不支持的小说网站");
        }
        return chapterSpider;
    }

}
