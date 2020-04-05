package com.nines.novel.spider.interfaces;

import com.nines.novel.spider.config.DownloadConfig;

/**
 * @ClassName: INovelDownload
 * @Description: 小说下载接口
 * @author: Nines
 * @date: 2020年04月02日 22:04
 */
public interface INovelDownload {

    /**
     * 小说下载
     * @param url 章节列表URL
     * @return String
     */
    public String download(String url, DownloadConfig downloadConfig);

}
