package com.nines.novel.spider.config;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: DownloadConfig
 * @Description: 下载参数配置类
 * @author: Nines
 * @date: 2020年04月04日 23:16
 */
@Data
public class DownloadConfig implements Serializable {

    private static final long serialVersionUID = 7687078841970731269L;

    private static final int DEFAULT_SIZE = 100;

    /**
     * 每个线程下载多少章
     */
    private int size;

    /**
     * 下载后保存的路径
     */
    private String path;

    public DownloadConfig(String path) {
        this.path = path;
        this.size = DEFAULT_SIZE;
    }

    public DownloadConfig(int size, String path) {
        this.size = size;
        this.path = path;
    }
}
