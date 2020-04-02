package com.nines.novel.spider;

import java.util.Map;

/**
 * @ClassName: SswChapterDetailSpider
 * @Description: 搜书网章节信息实现类
 * @author: Nines
 * @date: 2020年04月02日 19:07
 */
public class SswChapterDetailSpider extends DefaultChapterDetailSpider {

    private static final String FRONT_STR = "您可以在百度里搜索“";
    private static final String BACK_STR = " 搜书网(www.soshuw.com)”查找最新章节！";
    private static final String LAST_STR = "最新章节地址：";

    @Override
    public Map<String, String> getChapterDetails(String url) {
        Map<String, String> map = super.getChapterDetails(url);
        String content = map.get("content");
        // 获取小说名
        String name = content.substring(content.indexOf(FRONT_STR)+FRONT_STR.length(), content.indexOf(BACK_STR));
        // 拼接成代处理字符串
        String headStr = FRONT_STR+name+BACK_STR;
        String footStr = name+LAST_STR;
        map.put("content", content.substring(content.indexOf(headStr)+headStr.length(), content.indexOf(footStr)));
        return map;
    }
}
