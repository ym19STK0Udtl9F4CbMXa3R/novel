package com.nines.novel.spider.interfaces.impl;

import java.util.Map;

/**
 * @ClassName: SswChapterDetailSpider
 * @Description: 顶点小说章节信息实现类
 * @author: Nines
 * @date: 2020年04月02日 19:07
 */
public class DdxsChapterDetailSpider extends DefaultChapterDetailSpider {

    private static final String STR = "请记住本书首发域名：booktxt.net。顶点小说手机版阅读网址：m.booktxt.net";

    @Override
    public Map<String, String> getChapterDetails(String url, int tryTimes) {
        Map<String, String> map = super.getChapterDetails(url, tryTimes);
        if (map == null){
            return map;
        }
        String content = map.get("content");
        map.put("content", content.substring(0, content.indexOf(STR)));
        return map;
    }
}
