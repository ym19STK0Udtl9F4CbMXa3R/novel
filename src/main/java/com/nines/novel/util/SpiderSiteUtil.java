package com.nines.novel.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SpiderSiteUtil
 * @Description: 读取xml文件找到站点信息
 * @author: Nines
 * @date: 2020年04月01日 18:02
 */
public final class SpiderSiteUtil {

    private static final Map<NovelSiteEnum, Map<String, String>> CONTEXT_MAP = new HashMap<>();

    private SpiderSiteUtil(){}

    static {
        init();
    }

    private static void init(){
        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read(new File("src/main/resources/config/Spider-Rule.xml"));
            Element root = doc.getRootElement();
            List<Element> sites = root.elements("site");
            for (Element site : sites) {
                List<Element> attrs = site.elements();
                Map<String, String> attrMap = new HashMap<>();
                for (Element attr : attrs) {
                    attrMap.put(attr.getName(), attr.getTextTrim());
                }
                CONTEXT_MAP.put(NovelSiteEnum.getSiteByUrl(attrMap.get("url")), attrMap);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取站点详细信息
     * @param siteEnum key
     * @return 站点信息
     */
    public static Map<String, String> getContext(NovelSiteEnum siteEnum){
        return CONTEXT_MAP.get(siteEnum);
    }

}
