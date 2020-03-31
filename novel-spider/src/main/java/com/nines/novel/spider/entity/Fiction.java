package com.nines.novel.spider.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName: Fiction
 * @Description: 小说实体类
 * @author: Nines
 * @date: 2020年03月31日 15:09
 */
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

    public Fiction() {
    }

    public Fiction(Long id, String name, String author, String intro, int total, LocalDateTime lastUpdated, String origin, String url, boolean status, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.intro = intro;
        this.total = total;
        this.lastUpdated = lastUpdated;
        this.origin = origin;
        this.url = url;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Fiction{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", intro='" + intro + '\'' +
                ", total=" + total +
                ", lastUpdated=" + lastUpdated +
                ", origin='" + origin + '\'' +
                ", url='" + url + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
