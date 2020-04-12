package com.nines.novel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName: NovelApplication
 * @Description: springboot启动类
 * @author: Nines
 * @date: 2020年04月12日 21:08
 */
@MapperScan("com.nines.novel.dao")
@SpringBootApplication
public class NovelApplication {

    public static void main(String[] args) {
        SpringApplication.run(NovelApplication.class, args);
    }

}
