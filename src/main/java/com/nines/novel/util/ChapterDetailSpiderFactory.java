package com.nines.novel.util;

import com.nines.novel.spider.interfaces.impl.DdxsChapterDetailSpider;
import com.nines.novel.spider.interfaces.impl.DefaultChapterDetailSpider;
import com.nines.novel.spider.interfaces.impl.SswChapterDetailSpider;
import com.nines.novel.spider.interfaces.IChapterDetailSpider;
import com.nines.novel.spider.interfaces.impl.XswChapterDetailSpider;

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
                chapterDetailSpider = new DdxsChapterDetailSpider();
                break;
            case SOUSHUWANG:
                chapterDetailSpider = new SswChapterDetailSpider();
                break;
            case XIAOSHUOWU:
                chapterDetailSpider = new XswChapterDetailSpider();
                break;
            default:
                throw new RuntimeException("不支持的小说网站");
        }
        return chapterDetailSpider;
    }

}
