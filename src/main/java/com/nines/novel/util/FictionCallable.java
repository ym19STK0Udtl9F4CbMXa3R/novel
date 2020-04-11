package com.nines.novel.util;

import com.nines.novel.spider.interfaces.IFictionSpider;
import com.nines.novel.spider.interfaces.impl.DefaultFictionSpider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @ClassName: FictionCallable
 * @Description: 自定义小说列表Callable
 * @author: Nines
 * @date: 2020年04月11日 16:44
 */
public class FictionCallable implements Callable<List<String>> {

    private List<String> urls;

    private int tryTimes;

    public FictionCallable(List<String> urls, int tryTimes) {
        this.urls = urls;
        this.tryTimes = tryTimes;
    }

    @Override
    public List<String> call() throws Exception {
        IFictionSpider fictionSpider = new DefaultFictionSpider();
        List<String> fictionList = new ArrayList<>();
        for (String url : urls) {
            System.out.println("开始爬取：" + url);
            fictionList.addAll(fictionSpider.getFiction(url, tryTimes));
        }
        return fictionList;
    }
}
