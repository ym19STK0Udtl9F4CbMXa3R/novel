package junit;

import com.nines.novel.NovelApplication;
import com.nines.novel.entity.Fiction;
import com.nines.novel.service.FictionService;
import com.nines.novel.service.impl.FictionServiceImpl;
import com.nines.novel.util.IdWorker;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;

/**
 * @ClassName: SqlTest
 * @Description: 数据库相关测试
 * @author: Nines
 * @date: 2020年04月12日 20:47
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NovelApplication.class)
public class SqlTest {

    @Resource
    FictionService fictionService;

    @Test
    public void insertTest(){
        int num = fictionService.insert(new Fiction()
                .setId(IdWorker.getWorkerId())
                .setName("测试小说")
                .setAuthor("Nines")
                .setIntro("测试小说新增")
                .setTotal(1)
                .setLastUpdatedTitle("addFiction")
                .setLastUpdatedUrl("www.baidu.com/search")
                .setLastUpdatedTime(LocalDateTime.now())
                .setOrigin(3)
                .setUrl("www.baidu.com")
                .setStatus(true)
                .setCreateTime(LocalDateTime.now())
                .setUpdateTime(LocalDateTime.now())
        );
        System.out.println("新增" + num + "条！");
    }

}
