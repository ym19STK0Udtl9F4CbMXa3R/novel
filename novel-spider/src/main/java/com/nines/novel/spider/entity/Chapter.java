package com.nines.novel.spider.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName: Chapter
 * @Description: 小说章节实体类
 * @author: Nines
 * @date: 2020年03月31日 15:57
 */
@Data
@Accessors(chain = true)
public class Chapter implements Serializable {

    private static final long serialVersionUID = 1545459764700235691L;

    /**
     * ID
     */
    private Long id;

    /**
     * 小说ID
     */
    private Long fictionId;

    /**
     * 章节标题
     */
    private String title;

    /**
     * 章节内容
     */
    private int content;

    /**
     * 链接地址
     */
    private String url;

    /**
     * 上一章ID
     */
    private Long previousChapter;

    /**
     * 下一章ID
     */
    private Long nextChapter;

    /**
     * 作品是否有效
     */
    private boolean status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
