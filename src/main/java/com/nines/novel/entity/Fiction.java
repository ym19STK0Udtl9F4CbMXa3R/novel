package com.nines.novel.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName: Fiction
 * @Description: 小说实体类
 * @author: Nines
 * @date: 2020年03月31日 15:09
 */
@Data
@Accessors(chain = true)
public class Fiction implements Serializable {

    private static final long serialVersionUID = 4240205731739931211L;

    /**
     * ID
     */
    private Long id;

    /**
     * 小说名称
     */
    private String name;

    /**
     * 作者
     */
    private String author;

    /**
     * 作品简介
     */
    private String intro;

    /**
     * 章节总数
     */
    private int total;

    /**
     * 最后更新日期
     */
    private LocalDateTime lastUpdated;

    /**
     * 来源
     */
    private String origin;

    /**
     * 链接地址
     */
    private String url;

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
