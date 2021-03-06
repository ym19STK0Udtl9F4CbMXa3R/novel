package junit;

import com.nines.novel.spider.config.DownloadConfig;
import com.nines.novel.spider.interfaces.IFictionSpider;
import com.nines.novel.spider.interfaces.impl.*;
import com.nines.novel.spider.interfaces.IChapterDetailSpider;
import com.nines.novel.spider.interfaces.IChapterSpider;
import com.nines.novel.spider.interfaces.INovelDownload;
import com.nines.novel.util.*;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CrawlTest
 * @Description: 爬虫测试类
 * @author: Nines
 * @date: 2020年03月31日 16:45
 */
public class CrawlTest {

    /**
     * 测试读取xml文件
     */
    @Test
    public void testXml(){
        System.out.println(SpiderSiteUtil.getContext(NovelSiteEnum.DINGDIANXIAOSHUO).toString());
    }

    /**
     * 测试获取小说信息与章节列表
     */
    @Test
    public void testCrawlChapters(){
        IChapterSpider chapterSpider = ChapterSpiderFactory.getChapterSpider("https://www.bxwxorg.com/read/104207/");
        Map<String, Object> result = chapterSpider.getChapters("https://www.bxwxorg.com/read/104207/", 3);
        for (String key : result.keySet()) {
            System.out.println(key+"==>"+result.get(key));
        }
    }

    /**
     * 测试搜书网 获取小说信息与章节列表
     */
    @Test
    public void testSswCrawlChapters(){
        IChapterSpider chapterSpider = new SswChapterSpider();
        Map<String, Object> result = chapterSpider.getChapters("https://www.soshuw.com/WanMeiShiJie/", 3);
        for (String key : result.keySet()) {
            System.out.println(key+"==>"+result.get(key));
        }
    }

    /**
     * 获取章节信息
     */
    @Test
    public void testCrawlChapterDetails(){
        IChapterDetailSpider chapterDetailSpider = ChapterDetailSpiderFactory.getChapterDetailSpider("https://www.bxwxorg.com/read/104207/262052.html");
        Map<String, String> result = chapterDetailSpider.getChapterDetails("https://www.bxwxorg.com/read/104207/262052.html", 3);
        for (String key : result.keySet()) {
            System.out.println(key+"==>"+result.get(key));
        }
    }

    /**
     * 获取搜书网章节信息
     */
    @Test
    public void testSswCrawlChapterDetails(){
        IChapterDetailSpider chapterDetailSpider = new SswChapterDetailSpider();
        Map<String, String> result = chapterDetailSpider.getChapterDetails("https://www.soshuw.com/WanMeiShiJie/643603.html", 3);
        for (String key : result.keySet()) {
            System.out.println(key+"==>"+result.get(key));
        }
    }

    /**
     * 测试多线程下载小说并保存
     */
    @Test
    public void testDownload() {
        INovelDownload download = new NovelDownload();
        String filePath = download.download("https://www.bxwxorg.com/read/104207/", new DownloadConfig(50, 5, "G:/Crawl"));
        System.out.println("下载成功！文件地址：" + filePath);
    }

    /**
     * 测试文件合并
     */
    @Test
    public void testMergeFile(){
        NovelSpiderUtil.multiFileMerge("G:\\Crawl\\搜书网\\完美世界", "G:\\Crawl\\搜书网\\完美世界\\完美世界.txt", true);
    }

    /**
     * 测试压缩文件
     */
    @Test
    public void testZip(){
        NovelSpiderUtil.compressZipFile("G:\\Crawl\\搜书网\\完美世界\\完美世界.txt", null);
    }

    /**
     * 测试小说列表爬虫
     */
    @Test
    public void testFictionSpider(){
        IFictionSpider fictionSpider = FictionSpiderFactory.getFictionSpider("https://www.booktxt.net/xiaoshuodaquan/");
        List<String> list = fictionSpider.getFiction("https://www.booktxt.net/xiaoshuodaquan/", 3);
        for (String url : list) {
            System.out.println(url);
        }
    }

    /**
     * 测试获取所有小说列表页面
     */
    @Test
    public void testGetTotal(){
        BxwxFictionSpider bxwxFictionSpider = new BxwxFictionSpider();
        List<String> list = bxwxFictionSpider.getTotalPage("https://www.bxwxorg.com/all/", 3);
        for (String url : list) {
            System.out.println(url);
        }
    }

    /**
     * 测试获取失败，重试
     */
    @Test
    public void testTryTimes(){
        IChapterDetailSpider chapterDetailSpider = new SswChapterDetailSpider();
        Map<String, String> result = chapterDetailSpider.getChapterDetails("https://www.soshuw.com/WanMeiShiJie/643603.html", 3);
        System.out.println("获取失败");
        if (result == null){
            System.out.println("NULL");
        }
    }
}
