package com.nines.novel.spider.interfaces.abs;

import com.nines.novel.spider.interfaces.IChapterSpider;
import com.nines.novel.entity.Chapter;
import com.nines.novel.entity.Fiction;
import com.nines.novel.util.IdWorker;
import com.nines.novel.util.NovelSiteEnum;
import com.nines.novel.util.SpiderSiteUtil;
import com.nines.novel.util.NovelSpiderUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
    public Map<String, Object> getChapters(String url, int tryTimes) {
        Map<String, String> xmlMap = SpiderSiteUtil.getContext(NovelSiteEnum.getSiteByUrl(url));
        String html = crawl(url, tryTimes);
        // 获取资源失败
        if (html == null){
            throw new RuntimeException("下载小说失败！");
        }
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

        Element lastUpdated = document.select(xmlMap.get("last-updated")).first();
        // 最新章节名称
        String lastUpdatedTitle = lastUpdated.text();
        // 最新章节链接
        String lastUpdatedUrl = lastUpdated.absUrl("href");
        // 最后更新日期
        String lastUpdatedTime = document.select(xmlMap.get("last-updated-time")).text();

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
                .setAuthor(NovelSpiderUtil.splitLast(author, "："))
                .setIntro(intro)
                .setTotal(list.size())
                .setLastUpdatedTitle(lastUpdatedTitle)
                .setLastUpdatedUrl(lastUpdatedUrl)
                .setLastUpdatedTime(LocalDateTime.parse(NovelSpiderUtil.splitLast(lastUpdatedTime, "："), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .setOrigin(NovelSiteEnum.getSiteByUrl(url).getId())
                .setUrl(url)
                .setCreateTime(LocalDateTime.now())
                .setUpdateTime(LocalDateTime.now());

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("fiction", fiction);
        resultMap.put("chapters", list);
        return resultMap;
    }

}
