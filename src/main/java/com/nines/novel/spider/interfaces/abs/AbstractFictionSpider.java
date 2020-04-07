package com.nines.novel.spider.interfaces.abs;

import com.nines.novel.spider.interfaces.IFictionSpider;
import com.nines.novel.util.NovelSiteEnum;
import com.nines.novel.util.SpiderSiteUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: AbstractFictionSpider
 * @Description: 获取小说列表抽象类
 * @author: Nines
 * @date: 2020年04月07日 18:03
 */
public abstract class AbstractFictionSpider extends AbstractSpider implements IFictionSpider {

    @Override
    public List<String> getFiction(String url) {
        Map<String, String> xmlMap = SpiderSiteUtil.getContext(NovelSiteEnum.getSiteByUrl(url));
        try {
            String html = crawl(url);
            Document document = Jsoup.parse(html);
            document.setBaseUri(xmlMap.get("url"));
            Elements elements = document.select(xmlMap.get("fiction-list-selector"));
            List<String> list = new ArrayList<>();
            for (Element el : elements) {
                String fictionUrl = el.absUrl("href");
                list.add(fictionUrl);
            }
            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
