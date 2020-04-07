package junit;

import com.nines.novel.spider.config.DownloadConfig;
import com.nines.novel.spider.interfaces.impl.*;
import com.nines.novel.spider.interfaces.IChapterDetailSpider;
import com.nines.novel.spider.interfaces.IChapterSpider;
import com.nines.novel.spider.interfaces.INovelDownload;
import com.nines.novel.util.NovelSiteEnum;
import com.nines.novel.util.SpiderSiteUtil;
import com.nines.novel.util.NovelSpiderUtil;
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
     * 测试顶点小说 获取小说信息与章节列表
     */
    @Test
    public void testDdxsCrawlChapters(){
        IChapterSpider chapterSpider = new DefaultChapterSpider();
        Map<String, Object> result = chapterSpider.getChapters("https://www.booktxt.net/0_31/");
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
        Map<String, Object> result = chapterSpider.getChapters("https://www.soshuw.com/WanMeiShiJie/");
        for (String key : result.keySet()) {
            System.out.println(key+"==>"+result.get(key));
        }
    }

    /**
     * 获取章节信息
     */
    @Test
    public void testCrawlChapterDetails(){
        IChapterDetailSpider chapterDetailSpider = new DefaultChapterDetailSpider();
        Map<String, String> result = chapterDetailSpider.getChapterDetails("https://www.soshuw.com/WanMeiShiJie/643603.html");
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
        Map<String, String> result = chapterDetailSpider.getChapterDetails("https://www.soshuw.com/WanMeiShiJie/643603.html");
        for (String key : result.keySet()) {
            System.out.println(key+"==>"+result.get(key));
        }
    }

    /**
     * 测试多线程下载小说并保存
     */
    @Test
    public void testDownload() throws InterruptedException {
        INovelDownload download = new NovelDownload();
        String filePath = download.download("https://www.booktxt.net/2_2219/", new DownloadConfig(50, 5, "G:/Crawl"));
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
        DefaultFictionSpider fictionSpider = new DefaultFictionSpider();
        List<String> list = fictionSpider.getFiction("https://www.booktxt.net/xiaoshuodaquan/");
        for (String url : list) {
            System.out.println(url);
        }
    }
}
