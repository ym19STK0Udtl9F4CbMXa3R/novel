package com.nines.novel.spider.crawl;

import com.nines.novel.spider.crawl.impl.AbstractChapterSpider;
import com.nines.novel.spider.entity.Fiction;
import com.nines.novel.spider.util.SpiderStringUtil;

import java.util.Map;

/**
 * @ClassName: SswChapterSpider
 * @Description: 搜书网小说信息和章节列表实现类
 * @author: Nines
 * @date: 2020年04月02日 18:46
 */
public class SswChapterSpider extends DefaultChapterSpider {

    @Override
    public Map<String, Object> getChapters(String url) {
        // 调用父级方法获取map
        Map<String, Object> map = super.getChapters(url);
        Fiction fiction = (Fiction) map.get("fiction");
        // 清理简介
        String intro = SpiderStringUtil.splitFirst(fiction.getIntro(), "Tags：");
        // 重新赋值
        fiction.setIntro(intro);
        map.put("fiction", fiction);
        return map;
    }
}
