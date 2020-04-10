package com.nines.novel.spider.interfaces;

import java.util.Map;

/**
 * @ClassName: IChapterDetailSpider
 * @Description: 爬取章节信息接口
 * @author: Nines
 * @date: 2020年04月02日 15:34
 */
public interface IChapterDetailSpider {

    /**
     * 获取章节信息
     * @param url 章节链接
     */
    Map<String, String> getChapterDetails(String url, int tryTimes);

}
