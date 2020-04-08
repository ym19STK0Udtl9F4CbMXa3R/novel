package com.nines.novel.spider.interfaces.impl;

import com.nines.novel.entity.Chapter;
import com.nines.novel.entity.Fiction;
import com.nines.novel.spider.config.DownloadConfig;
import com.nines.novel.spider.interfaces.IChapterDetailSpider;
import com.nines.novel.spider.interfaces.IChapterSpider;
import com.nines.novel.spider.interfaces.INovelDownload;
import com.nines.novel.util.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @ClassName: NovelDownload
 * @Description: 小说下载实现类
 * @author: Nines
 * @date: 2020年04月02日 22:06
 */
public class NovelDownload implements INovelDownload {

    @Override
    public String download(String url, DownloadConfig downloadConfig) {
        // 通过工厂获取小说信息爬虫实例
        IChapterSpider chapterSpider = ChapterSpiderFactory.getChapterSpider(url);
        // 获取小说信息和章节列表
        Map<String, Object> chapterMap = chapterSpider.getChapters(url,downloadConfig.getTryTimes());
        // 获取小说信息
        Fiction fiction = (Fiction) chapterMap.get("fiction");
        // 通过origin代号获取对应的小说网站名
        String origin = SpiderSiteUtil.getContext(NovelSiteEnum.getSiteById(fiction.getOrigin())).get("name");
        // 创建文件路径
        File file = new File(downloadConfig.getPath() + "/" + origin + "/" + fiction.getName());
        if (!file.exists()){
            if (!file.mkdirs()){
                throw new RuntimeException("创建路径失败");
            }
        }
        // 获取章节列表
        ArrayList<Chapter> chapterList = (ArrayList<Chapter>)chapterMap.get("chapters");
        // 单线程执行大小
        int size = downloadConfig.getSize();
        // 开启最大线程数
        int maxDownloadThread = (int) Math.ceil(chapterList.size() * 1.0 / size);
        // 任务分配
        Map<String, List<Chapter>> downloadTaskAlloc = new HashMap<>();
        /*
         *  i=0, 0-99
         *  i=1, 100-199
         *  i=2, 200-299
         *  ...
         *  i=19, 1900-1999
         *  i=20, 2000-2054
         *  总共2054章，分配每个线程的任务
         */
        for (int i = 0; i < maxDownloadThread; i++) {
            // 开始下标
            int startIndex = i * size;
            //结束下标 substring sublist 第一个参数包括，第二个不包括 chapterList.size() - 1 + 1，i * size + size - 1 + 1
            int endIndex = i == maxDownloadThread - 1 ? chapterList.size() : i * size + size;
            downloadTaskAlloc.put(startIndex + "-" + endIndex, chapterList.subList(startIndex, endIndex));
        }
        // 保存路径
        String savePath = downloadConfig.getPath() + "/" + origin + "/" + fiction.getName();
        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(maxDownloadThread);
        List<Future<String>> tasks = new ArrayList<>();
        // 开启线程
        for (String key : downloadTaskAlloc.keySet()) {
            tasks.add(executorService.submit(new DownloadCallable(downloadTaskAlloc.get(key),
                     downloadConfig.getTryTimes(),
                     savePath + "/" + key + ".txt")));
        }
        // 所有线程完成后关闭线程池
        executorService.shutdown();
        // task.get() 等待线程结束
        for (Future<String> task : tasks) {
            try {
                System.out.println(task.get() + ",下载完成！");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        // 合并小说章节
        NovelSpiderUtil.multiFileMerge(savePath, savePath + "/" + fiction.getName() + ".txt", true);
        System.out.println(fiction.getName() + ",合并完成！");
        // 压缩小说
        NovelSpiderUtil.compressZipFile(savePath + "/" + fiction.getName() + ".txt", null);
        System.out.println(fiction.getName() + ",压缩完成！");
        // 返回小说位置
        return savePath + "/" + fiction.getName() + ".txt";
    }
}
class DownloadCallable implements Callable<String>{

    private List<Chapter> chapterList;

    private int tryTimes;

    private String path;

    public DownloadCallable(List<Chapter> chapterList, int tryTimes, String path) {
        this.chapterList = chapterList;
        this.tryTimes = tryTimes;
        this.path = path;
    }

    @Override
    public String call() throws Exception {
        try (PrintWriter out = new PrintWriter(new File(path), "UTF-8")) {
            for (Chapter chapter : chapterList) {
                // 通过工厂获取章节爬虫实例
                IChapterDetailSpider chapterDetailSpider = ChapterDetailSpiderFactory.getChapterDetailSpider(chapter.getUrl());
                Map<String, String> chapterDetailMap = chapterDetailSpider.getChapterDetails(chapter.getUrl(), tryTimes);
                // 获取失败，直接跳过
                if (chapterDetailMap == null){
                    continue;
                }
                chapter.setContent(chapterDetailMap.get("content"));
                chapter.setPreviousChapterUrl(chapterDetailMap.get("previous"));
                chapter.setNextChapterUrl(chapterDetailMap.get("next"));
                // 输出到文本
                out.println("\t" + chapter.getTitle());
                out.println(chapter.getContent());
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return path;
    }
}