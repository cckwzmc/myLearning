package com.lottery.ssq.fetch;

import java.util.ArrayList;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;

import org.apache.commons.lang.StringUtils;

import com.lottery.ssq.LotterySsqFetchConfig;
import com.lottery.util.html.HttpHtmlService;

public class SsqLotterySinaFetchImpl implements ISsqLotteryFetch {

    final String URLSINA = "http://sports.sina.com.cn/l/lotto/3.shtml";

    @Override
    public String getSsqLotteryDetail(String url, String title) {
        return null;
    }

    @Override
    public List<String[]> getSsqLotteryIndexList() {
        String listContent = HttpHtmlService.getHtmlContent(URLSINA, "GBK");
        if (StringUtils.isBlank(listContent)) {
            return null;
        }
        List<String[]> ssqList = new ArrayList<String[]>();
        Source source = new Source(listContent);
        List<Element> list = source.getAllElementsByClass("articleList");
        for (Element ul : list) {
            List<Element> liList = ul.getAllElements("li");
            for (Element li : liList) {
                String[] ssq = new String[2];
                List<Element> href = li.getAllElements().get(0).getAllElements("a");
                String hrefValue = href.get(0).getAttributeValue("href");
                String hrefTitle = href.get(0).getContent().getTextExtractor().toString();

                if (hrefTitle.indexOf(LotterySsqFetchConfig.expect + "") != -1 || hrefTitle.indexOf(LotterySsqFetchConfig.expect.substring(LotterySsqFetchConfig.expect.length() - 3)) != -1) {
                    ssq[0] = hrefValue;
                    ssq[1] = hrefTitle;
                    ssqList.add(ssq);
                }

            }
        }
        return ssqList;
    }
}
