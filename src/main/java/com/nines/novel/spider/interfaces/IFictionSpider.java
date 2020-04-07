package com.nines.novel.spider.interfaces;

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
    public List<String> getFiction(String url);

}
