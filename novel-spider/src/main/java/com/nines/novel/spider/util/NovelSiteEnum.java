package com.nines.novel.spider.util;

/**
 * @ClassName: NovelSiteEnum
 * @Description: 爬虫站点
 * @author: Nines
 * @date: 2020年04月01日 17:48
 */
public enum NovelSiteEnum {

    /**
     * 顶点小说
     */
    DINGDIANXIAOSHUO(0, "booktxt.net"),

    /**
     * 搜书网
     */
    SOUSHUWANG(1, "soshuw.com");

    private int id;
    private String uri;

    NovelSiteEnum(int id, String uri) {
        this.id = id;
        this.uri = uri;
    }

    /**
     * 通过ID得到站点
     * @param id 站点id
     * @return 站点
     */
    public static NovelSiteEnum getSiteById(int id){
        for (NovelSiteEnum site : values()) {
            if (site.id == id){
                return site;
            }
        }
        throw new RuntimeException("目前尚未支持该小说网站");
    }

    /**
     * 通过URL得到站点
     * @param url 站点链接
     * @return 站点
     */
    public static NovelSiteEnum getSiteByUrl(String url){
        for (NovelSiteEnum site : values()) {
            if (url.contains(site.uri)){
                return site;
            }
        }
        throw new RuntimeException("目前尚未支持该小说网站");
    }

}
