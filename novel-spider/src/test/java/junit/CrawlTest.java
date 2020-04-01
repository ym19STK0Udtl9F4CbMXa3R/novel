package junit;

import com.nines.novel.spider.service.ISpider;
import com.nines.novel.spider.service.impl.DefaultSpider;
import com.nines.novel.spider.util.NovelSiteEnum;
import com.nines.novel.spider.util.SpiderSiteUtil;
import org.junit.Test;

/**
 * @ClassName: CrawlTest
 * @Description: 爬虫测试类
 * @author: Nines
 * @date: 2020年03月31日 16:45
 */
public class CrawlTest {

    @Test
    public void testCrawl(){
        ISpider spider = new DefaultSpider();
        spider.getChapters("https://www.soshuw.com/WanMeiShiJie/");
    }

    @Test
    public void testXml(){
        System.out.println(SpiderSiteUtil.getContext(NovelSiteEnum.DINGDIANXIAOSHUO).toString());
    }

}
