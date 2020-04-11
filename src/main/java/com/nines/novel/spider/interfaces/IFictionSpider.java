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
     * @param tryTimes 重连次数
     * @return 列表
     */
    List<String> getFiction(String url, int tryTimes);

    /**
     * 获取所有页面
     * @param url 开始页面
     * @param tryTimes 重连次数
     * @return 所有页面URL
     */
    List<String> getTotalPage(String url, int tryTimes);

}
