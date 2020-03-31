package junit;

import com.nines.novel.spider.service.ICrawler;
import com.nines.novel.spider.service.impl.AbstractCrawler;
import com.nines.novel.spider.service.impl.DefaultCrawler;
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
        ICrawler crawler = new DefaultCrawler();
        crawler.getChapters("https://www.booktxt.net/0_31/");
    }

}
