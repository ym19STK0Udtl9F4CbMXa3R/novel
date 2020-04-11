package com.nines.novel.spider.interfaces.impl;

import com.nines.novel.spider.interfaces.abs.AbstractFictionSpider;
import com.nines.novel.util.FictionCallable;
import com.nines.novel.util.NovelSiteEnum;
import com.nines.novel.util.SpiderSiteUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @ClassName: BxwxFictionSpider
 * @Description: 笔下文学小说列表实现类
 * @author: Nines
 * @date: 2020年04月11日 14:38
 */
public class BxwxFictionSpider extends AbstractFictionSpider {

    @Override
    public List<String> getFiction(String url, int tryTimes) {
        // 每个线程工作大小
        int size = 20;
        // 获取所有小说列表页面
        List<String> list = getTotalPage(url, tryTimes);
        // 任务分配
        Map<String, List<String>> downloadTaskAlloc = new HashMap<>();
        // 开启线程数量
        int maxDownloadThread = (int)Math.ceil(list.size() * 1.0 / size);
        for (int i = 0; i < maxDownloadThread; i++) {
            int startIndex = i * size;
            int endIndex = i + 1 == maxDownloadThread ? list.size() : (i + 1) * size;
            downloadTaskAlloc.put(startIndex + "-" + endIndex, list.subList(startIndex, endIndex));
        }
        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(maxDownloadThread);
        // 开启线程
        List<Future<List<String>>> tasks = new ArrayList<>();
        for (String key : downloadTaskAlloc.keySet()) {
            tasks.add(executorService.submit(new FictionCallable(downloadTaskAlloc.get(key), tryTimes)));
        }
        // 所有线程完成后关闭线程池
        executorService.shutdown();
        // 获取结果
        List<String> fictionList = new ArrayList<>();
        for (Future<List<String>> task : tasks) {
            try {
                fictionList.addAll(task.get());
            } catch (InterruptedException | ExecutionException e) {
                System.err.println(e);
            }
        }
        return fictionList;
    }

    @Override
    public List<String> getTotalPage(String url, int tryTimes) {
        Map<String, String> xmlMap = SpiderSiteUtil.getContext(NovelSiteEnum.getSiteByUrl(url));
        // 获取下一页的解析规则
        String nextUrl = xmlMap.get("fiction-last-page");
        // 解析规则为NULL 则表示不需要翻页获取
        if (nextUrl != null){
            String html = crawl(url, tryTimes);
            Document document = Jsoup.parse(html);
            String lastPage = document.select(nextUrl).attr("href");
            if (lastPage != null){
                int maxPage = Integer.parseInt(lastPage.substring(lastPage.lastIndexOf("/") + 1, lastPage.lastIndexOf(".")));
                List<String> list = new ArrayList<>();
                for (int i = 1; i <= maxPage; i++) {
                    list.add("https://www.bxwxorg.com/all/"+ i +".html");
                }
                return list;
            }
        }
        // 没有翻页操作，返回当前一页即可
        List<String> list = new ArrayList<>();
        list.add(url);
        return list;
    }

}

