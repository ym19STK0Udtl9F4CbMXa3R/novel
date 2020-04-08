package com.nines.novel.spider.interfaces;

import java.util.Map;

/**
 * @ClassName: IChapterSpider
 * @Description: 爬取小说信息和章节列表接口
 * @author: Nines
 * @date: 2020年03月31日 16:05
 */
public interface IChapterSpider {

    /**
     * 获取小说信息和章节列表
     * @param url 小说链接
     */
    public Map<String, Object> getChapters(String url, int tryTimes);

}
