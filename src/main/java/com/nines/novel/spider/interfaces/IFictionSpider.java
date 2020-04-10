package com.nines.novel.spider.interfaces;

import com.nines.novel.entity.Fiction;

import java.util.Iterator;
import java.util.List;

/**
 * @ClassName: IFictionSpider
 * @Description: 小说列表接口
 * @author: Nines
 * @date: 2020年04月07日 17:56
 */
public interface IFictionSpider {

    /**
     * 获取小说列表
     * @param url 小说网站连接
     * @return 列表
     */
    List<String> getFiction(String url, int tryTimes);

    /**
     * 是否有下一页
     * @return true/false
     */
    boolean hasNext();

    /**
     * 下一页
     * @return url
     */
    String next();

    /**
     * 自定义迭代器
     * @return 迭代器
     */
    Iterator<List<Fiction>> iterator();

}
