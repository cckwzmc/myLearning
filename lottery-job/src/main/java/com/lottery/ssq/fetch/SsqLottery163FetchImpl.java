package com.lottery.ssq.fetch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.ObjectUtils;

import com.lottery.ssq.LotterySsqFetchConfig;
import com.lottery.ssq.fetch.dao.LotteryFetchDao;
import com.lottery.util.html.HttpHtmlService;

public class SsqLottery163FetchImpl implements ISsqLotteryFetch {

    final String URL163 = "http://sports.163.com/special/00051O8E/ssqgd.html";
    private LotteryFetchDao lotteryFetchDao=null;
    
    public void setLotteryFetchDao(LotteryFetchDao lotteryFetchDao) {
		this.lotteryFetchDao = lotteryFetchDao;
	}

	@SuppressWarnings("unchecked")
	@Override
    public String getSsqLotteryDetail(String url, String title) {
    	List<String[]> ssqList=this.getSsqLotteryIndexList();
    	List webList=this.lotteryFetchDao.getSsqLotteryWebFetchList(1);
    	for(String[] detail:ssqList){
    		for (Iterator iterator = webList.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();
				String webTitle=ObjectUtils.toString(map.get("title"));
				String[] wt=StringUtils.split(webTitle,"|");
			}
    	}
        return null;
    }

    @Override
    public List<String[]> getSsqLotteryIndexList() {
        String listContent = HttpHtmlService.getHtmlContent(URL163, "GBK");
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
                List<Element> articleTitle = li.getAllElementsByClass("articleTitle");
                List<Element> href = articleTitle.get(0).getAllElements("a");
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
