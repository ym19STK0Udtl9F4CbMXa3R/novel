package com.nines.novel.spider.interfaces.abs;

import com.nines.novel.util.NovelSiteEnum;
import com.nines.novel.util.SpiderSiteUtil;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;

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
             CloseableHttpResponse response = httpClient.execute(new NovelSpiderHttpGet(url))){
            return EntityUtils.toString(response.getEntity(), SpiderSiteUtil.getContext(NovelSiteEnum.getSiteByUrl(url)).get("charset"));
        } catch (IOException e) {
            throw new RuntimeException("连接超时或爬取失败");
        }
    }

}

class NovelSpiderHttpGet extends HttpGet{

    public NovelSpiderHttpGet() {
    }

    public NovelSpiderHttpGet(URI uri) {
        super(uri);
    }

    public NovelSpiderHttpGet(String uri) {
        super(uri);
        setDefaultConfig();
    }

    /**
     * 设置默认的请求参数
     */
    private void setDefaultConfig(){
        this.setConfig(RequestConfig.custom()
                // 连接服务器超时时间
                .setConnectTimeout(10000)
                // 通过socket发送请求，打开socket超时 一般1s 2s
                .setSocketTimeout(2000)
                // 从服务器读取数据超时时间
                .setConnectionRequestTimeout(10000)
                .build());

        // 设置请求头
        this.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3741.400 QQBrowser/10.5.3863.400");
    }

}