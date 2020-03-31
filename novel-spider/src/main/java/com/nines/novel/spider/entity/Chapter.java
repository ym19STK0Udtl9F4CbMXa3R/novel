package com.nines.novel.spider.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName: Chapter
 * @Description: 小说章节实体类
 * @author: Nines
 * @date: 2020年03月31日 15:57
 */
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
     * 章节号
     */
    private String number;

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

    public Chapter() {
    }

    public Chapter(Long id, Long fictionId, String number, String title, int content, String url, Long previousChapter, Long nextChapter, boolean status, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.fictionId = fictionId;
        this.number = number;
        this.title = title;
        this.content = content;
        this.url = url;
        this.previousChapter = previousChapter;
        this.nextChapter = nextChapter;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", fictionId=" + fictionId +
                ", number='" + number + '\'' +
                ", title='" + title + '\'' +
                ", content=" + content +
                ", url='" + url + '\'' +
                ", previousChapter=" + previousChapter +
                ", nextChapter=" + nextChapter +
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

    public Long getFictionId() {
        return fictionId;
    }

    public void setFictionId(Long fictionId) {
        this.fictionId = fictionId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getPreviousChapter() {
        return previousChapter;
    }

    public void setPreviousChapter(Long previousChapter) {
        this.previousChapter = previousChapter;
    }

    public Long getNextChapter() {
        return nextChapter;
    }

    public void setNextChapter(Long nextChapter) {
        this.nextChapter = nextChapter;
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
