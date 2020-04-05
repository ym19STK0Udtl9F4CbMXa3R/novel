package com.nines.novel.util;

import com.nines.novel.spider.DefaultChapterDetailSpider;
import com.nines.novel.spider.SswChapterDetailSpider;
import com.nines.novel.spider.interfaces.IChapterDetailSpider;

/**
 * @ClassName: ChapterSpiderFactory
 * @Description: 章节信息爬虫工厂
 * @author: Nines
 * @date: 2020年04月02日 22:11
 */
public final class ChapterDetailSpiderFactory {

    private ChapterDetailSpiderFactory(){}
    
    public static IChapterDetailSpider getChapterDetailSpider(String url){
        IChapterDetailSpider chapterDetailSpider;
        NovelSiteEnum site = NovelSiteEnum.getSiteByUrl(url);
        switch (site){
            case DINGDIANXIAOSHUO:
                chapterDetailSpider = new DefaultChapterDetailSpider();
                break;
            case SOUSHUWANG:
                chapterDetailSpider = new SswChapterDetailSpider();
                break;
            default:
                throw new RuntimeException("不支持的小说网站");
        }
        return chapterDetailSpider;
    }

}