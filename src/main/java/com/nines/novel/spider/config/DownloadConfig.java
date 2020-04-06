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

    /**
     * 默认单个线程大小
     */
    private static final int DEFAULT_SIZE = 50;

    /**
     * 默认重试次数
     */
    private static final int DEFAULT_TRY_TIMES = 5;

    /**
     * 每个线程下载多少章
     */
    private int size;

    /**
     * 下次超时重试
     */
    private int tryTimes;

    /**
     * 下载后保存的路径
     */
    private String path;

    public DownloadConfig(String path) {
        this.path = path;
        this.size = DEFAULT_SIZE;
        this.tryTimes = DEFAULT_TRY_TIMES;
    }

    public DownloadConfig(int size, int tryTimes, String path) {
        this.size = size;
        this.tryTimes = tryTimes;
        this.path = path;
    }
}
