package com.nines.novel.spider.interfaces.abs;

import com.nines.novel.spider.interfaces.IChapterSpider;
import com.nines.novel.entity.Chapter;
import com.nines.novel.entity.Fiction;
import com.nines.novel.util.IdWorker;
import com.nines.novel.util.NovelSiteEnum;
import com.nines.novel.util.SpiderSiteUtil;
import com.nines.novel.util.SpiderStringUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: AbstractChapterSpider
 * @Description: 解析小说信息和章节列表
 * @author: Nines
 * @date: 2020年04月02日 15:25
 */
public abstract class AbstractChapterSpider extends AbstractSpider implements IChapterSpider {

    @Override
    public Map<String, Object> getChapters(String url) {
        Map<String, String> xmlMap = SpiderSiteUtil.getContext(NovelSiteEnum.getSiteByUrl(url));
        try {
            String html = crawl(url);
            Document document = Jsoup.parse(html);
            // 设置uri
            document.setBaseUri(url);
            // 小说ID
            Long id = IdWorker.getWorkerId();
            // 小说名
            String name = document.select(xmlMap.get("fiction-name")).text();
            // 作者
            String author = document.select(xmlMap.get("author")).text();
            // 简介
            String intro = document.select(xmlMap.get("intro")).text();
            // 最后更新日期
            String lastUpdated = document.select(xmlMap.get("last-updated")).text();

            Elements els = document.select(xmlMap.get("chapter-list-selector"));
            ArrayList<Chapter> list = new ArrayList<>();
            for (Element el : els) {
                Chapter chapter = new Chapter()
                        .setId(IdWorker.getWorkerId())
                        .setFictionId(id)
                        .setUrl(el.absUrl(xmlMap.get("chapter-url")))
                        .setTitle(el.select(xmlMap.get("chapter-title")).text());
                list.add(chapter);
            }

            Fiction fiction = new Fiction()
                    .setId(id)
                    .setName(name)
                    .setAuthor(SpiderStringUtil.splitLast(author, "："))
                    .setIntro(intro)
                    .setTotal(list.size())
                    .setLastUpdated(LocalDateTime.parse(SpiderStringUtil.splitLast(lastUpdated, "："), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .setOrigin(xmlMap.get("name"))
                    .setUrl(url)
                    .setCreateTime(LocalDateTime.now())
                    .setUpdateTime(LocalDateTime.now());
//            System.out.println(fiction.toString());

            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("fiction", fiction);
            resultMap.put("chapters", list);
            return resultMap;

        } catch (IOException e) {
//            e.printStackTrace();
            throw new RuntimeException("解析小说失败");
        }
    }

}
