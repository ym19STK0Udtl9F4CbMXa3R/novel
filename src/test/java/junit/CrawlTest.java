package junit;

import com.nines.novel.spider.DefaultChapterDetailSpider;
import com.nines.novel.spider.DefaultChapterSpider;
import com.nines.novel.spider.SswChapterDetailSpider;
import com.nines.novel.spider.SswChapterSpider;
import com.nines.novel.spider.interfaces.IChapterDetailSpider;
import com.nines.novel.spider.interfaces.IChapterSpider;
import com.nines.novel.util.NovelSiteEnum;
import com.nines.novel.util.SpiderSiteUtil;
import org.junit.Test;

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

    @Test
    public void testCrawlChapterDetails(){
        IChapterDetailSpider chapterDetailSpider = new DefaultChapterDetailSpider();
        Map<String, String> result = chapterDetailSpider.getChapterDetails("https://www.soshuw.com/WanMeiShiJie/643603.html");
        for (String key : result.keySet()) {
            System.out.println(key+"==>"+result.get(key));
        }
    }

    @Test
    public void testSswCrawlChapterDetails(){
        IChapterDetailSpider chapterDetailSpider = new SswChapterDetailSpider();
        Map<String, String> result = chapterDetailSpider.getChapterDetails("https://www.soshuw.com/WanMeiShiJie/643603.html");
        for (String key : result.keySet()) {
            System.out.println(key+"==>"+result.get(key));
        }
    }

}
