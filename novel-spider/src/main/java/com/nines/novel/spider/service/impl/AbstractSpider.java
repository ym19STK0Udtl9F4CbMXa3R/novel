package com.nines.novel.spider.service.impl;

import com.nines.novel.spider.entity.Chapter;
import com.nines.novel.spider.entity.Fiction;
import com.nines.novel.spider.service.ISpider;
import com.nines.novel.spider.util.IdWorker;
import com.nines.novel.spider.util.NovelSiteEnum;
import com.nines.novel.spider.util.SpiderSiteUtil;
import com.nines.novel.spider.util.SpiderStringUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

/**
 * @ClassName: AbstractSpider
 * @Description: 爬虫抽象类
 * @author: Nines
 * @date: 2020年03月31日 16:22
 */
public abstract class AbstractSpider implements ISpider {

    public String crawl(String url) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(new HttpGet(url));){
            return EntityUtils.toString(response.getEntity(), SpiderSiteUtil.getContext(NovelSiteEnum.getSiteByUrl(url)).get("charset"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getChapters(String url) {
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
//            System.out.println(name);
            // 作者
            String author = document.select(xmlMap.get("author")).text();
//            System.out.println(author);
            // 简介
            String intro = document.select(xmlMap.get("intro")).text();
            /*
             * 如果来源是搜书网，则简介需做特殊处理
             */
            if ("搜书网".equals(xmlMap.get("name"))){
                intro = SpiderStringUtil.splitFirst(intro, "Tags：");
            }
//            System.out.println(intro);
            // 最后更新日期
            String lastUpdated = document.select(xmlMap.get("last-updated")).text();
//            System.out.println(lastUpdated);

            Elements els = document.select(xmlMap.get("chapter-list-selector"));
            ArrayList<Chapter> list = new ArrayList<>();
            for (Element el : els) {
                Chapter chapter = new Chapter()
                        .setId(IdWorker.getWorkerId())
                        .setFictionId(id)
                        .setUrl(el.absUrl(xmlMap.get("chapter-url")))
                        .setTitle(el.select(xmlMap.get("chapter-title")).text());
//                System.out.println(el.absUrl(xmlMap.get("chapter-url")));
//                System.out.println(el.select(xmlMap.get("chapter-title")).text());
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
            System.out.println(fiction.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("解析小说失败");
    }
}
