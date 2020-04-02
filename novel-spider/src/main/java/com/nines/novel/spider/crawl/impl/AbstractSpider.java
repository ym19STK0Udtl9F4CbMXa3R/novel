package com.nines.novel.spider.crawl.impl;

import com.nines.novel.spider.util.NovelSiteEnum;
import com.nines.novel.spider.util.SpiderSiteUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @ClassName: AbstractSpider
 * @Description: httpclient 爬取网页
 * @author: Nines
 * @date: 2020年03月31日 16:22
 */
public abstract class AbstractSpider {

    public String crawl(String url) throws IOException {

        // try with resource
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(new HttpGet(url));){
            return EntityUtils.toString(response.getEntity(), SpiderSiteUtil.getContext(NovelSiteEnum.getSiteByUrl(url)).get("charset"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("爬取失败");
    }

}
