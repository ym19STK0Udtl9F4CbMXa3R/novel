package com.nines.novel.spider.interfaces.abs;

import com.nines.novel.spider.interfaces.IChapterDetailSpider;
import com.nines.novel.util.NovelSiteEnum;
import com.nines.novel.util.SpiderSiteUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: AbstractChapterDetailSpider
 * @Description: 解析章节信息
 * @author: Nines
 * @date: 2020年04月02日 15:30
 */
public abstract class AbstractChapterDetailSpider extends AbstractSpider implements IChapterDetailSpider {

    @Override
    public Map<String, String> getChapterDetails(String url, int tryTimes) {
        Map<String, String> xmlMap = SpiderSiteUtil.getContext(NovelSiteEnum.getSiteByUrl(url));
        String html = crawl(url, tryTimes);
        // html获取失败，直接返回null
        if (html == null){
            return null;
        }
        // 将正文中的html类型的空格符和换行符替换为JAVA的
        Document document = Jsoup.parse(html
                .replace("&nbsp;","  ")
                .replace("<br />","\n")
                .replace("<br/>","\n")
        );
        // 设置uri
        document.setBaseUri(url);
        // 正文
        String content = document.select(xmlMap.get("chapter-content")).text();
        // 上一章
        String previous = document.select(xmlMap.get("chapter-previous")).get(0).absUrl("href");
        // 下一章
        String next = document.select(xmlMap.get("chapter-next")).get(0).absUrl("href");

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("content", content);
        resultMap.put("previous", previous);
        resultMap.put("next", next);
        return resultMap;
    }

}
