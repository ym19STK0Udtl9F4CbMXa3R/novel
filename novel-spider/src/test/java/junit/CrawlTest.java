package junit;

import com.nines.novel.spider.service.ISpider;
import com.nines.novel.spider.service.impl.DefaultSpider;
import com.nines.novel.spider.util.NovelSiteEnum;
import com.nines.novel.spider.util.SpiderSiteUtil;
import com.nines.novel.spider.util.SpiderStringUtil;
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

    @Test
    public void testString(){
        String res = SpiderStringUtil.splitFirst("蒸汽与机械的浪潮中，谁能触及非凡？历史和黑暗的迷雾里，又是谁在耳语？我从诡秘中醒来，睁眼看见这个世界： 枪械，大炮，巨舰，飞空艇，差分机；魔药，占卜，诅咒，倒吊人，封印物……光明依旧照耀，神秘从未远离，这是一段“愚者”的传说。 Tags： 诡秘之主无弹窗广告 诡秘之主最新章节 诡秘之主txt下载 《诡秘之主》为作者爱潜水的乌贼创作，目前连载中，搜书网为你第一时间提供爱潜水的乌贼精心编写原创诡秘之主最新章节及无弹窗广告、TXT电子书下载等服务。", "Tags：");
        System.out.println(res);
    }

}
