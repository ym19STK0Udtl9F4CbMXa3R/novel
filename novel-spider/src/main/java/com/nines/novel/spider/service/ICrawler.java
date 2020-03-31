package com.nines.novel.spider.service;

/**
 * @ClassName: ICrawler
 * @Description: 爬虫接口
 * @author: Nines
 * @date: 2020年03月31日 16:05
 */
public interface ICrawler {

    /**
     * 获取章节
     * @param url 链接
     * @return 小说章节
     */
    public String getChapters(String url);

}
