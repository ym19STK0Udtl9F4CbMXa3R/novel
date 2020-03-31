package com.nines.novel.spider.service.impl;

import com.nines.novel.spider.service.ICrawler;
import com.nines.novel.spider.util.StringUtil;
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
import java.util.StringTokenizer;

/**
 * @ClassName: CrawlerImpl
 * @Description: 爬虫抽象类
 * @author: Nines
 * @date: 2020年03月31日 16:22
 */
public abstract class AbstractCrawler implements ICrawler {

    public String crawl(String url) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(new HttpGet(url));){
            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getChapters(String url) {
        try {
            String html = crawl(url);
            Document document = Jsoup.parse(html);
            String name = document.select("#info h1").text();
            System.out.println(name);
            String author = document.select("#info p:first-of-type").text();
            System.out.println(StringUtil.splitLast(author, "："));
            String lastUpdated = document.select("#info p:last-of-type").text();
            System.out.println(StringUtil.splitLast(lastUpdated, "："));
            String intro = document.select("#intro p").text();
            System.out.println(intro);
            Elements els = document.select("#list dt:nth-of-type(2) ~ dd a");
            for (Element el : els) {
                System.out.println(el);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
