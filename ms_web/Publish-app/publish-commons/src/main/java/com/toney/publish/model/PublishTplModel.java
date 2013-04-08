package com.toney.publish.model;

import java.io.Serializable;
import java.util.List;

/**
 * 
 *************************************************************** 
 * <p>
 * 
 * @CLASS :TemplateParameter
 * @DESCRIPTION :发布和模板相关的参数<br/>
 *              决定一个模板的要素：<br/>
 *              1、是否全站首页(isHomePage)<br/>
 *              2、频道首页（channelId）<br/>
 *              3、频道列表页(channelIdList:如果为空取最高一个等级) <br/>
 *              4、活动/主题首页（channelId） 5、活动/主题列表页（channelIdList 如果为空取最高一个等级）<br/>
 *              6、detail页(channelId,isDetailPage) 详情页只绑定在channelId下.
 *              7、detail全量出版（isPublishAllDetail） <br/>
 *              8、detail范围出版（detailMinId,detailMaxId,detailMinId=detailMaxId出版一页
 *              ）
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Dec 18, 2012
 *       </p>
 **************************************************************** 
 */
public class PublishTplModel implements Serializable {

	private static final long serialVersionUID = -6589974469572855146L;

	/**
	 * '1', 'home_page_index', '全站首页', '1'<br/>
	 * '2', 'channel_page_index', '频道首页', '1'<br/>
	 * '3', 'channel_page_list', '频道列表页', '1'<br/>
	 * '4', 'activity_page_index', '活动/专题首页', '1'<br/>
	 * '5', 'activity_page_list', '活动/专题列表页', '1'<br/>
	 * '6', 'detail_page', '详细页', '1'<br/>
	 */
	private String tplTypeCode;

	private List<Long> channelIdList;
	private List<Long> channelListIdList;
	/**
	 * 全量出版则忽略后面的请求 可以跟channelId或channelListId
	 */
	private boolean isPublishAllDetail;
	/**
	 * 区间出版
	 */
	private Long detailMinId;
	private Long detailMaxId;
	/**
	 * 指定Id出版
	 */
	private List<Long> detailIdList;


	public String getTplTypeCode() {
		return tplTypeCode;
	}

	/**
	 * '1', 'home_page_index', '全站首页', '1'<br/>
	 * '2', 'channel_page_index', '频道首页', '1'<br/>
	 * '3', 'channel_page_list', '频道列表页', '1'<br/>
	 * '4', 'activity_page_index', '活动/专题首页', '1'<br/>
	 * '5', 'activity_page_list', '活动/专题列表页', '1'<br/>
	 * '6', 'detail_page', '详细页', '1'<br/>
	 * @param tplTypeCode
	 */
	public void setTplTypeCode(String tplTypeCode) {
		this.tplTypeCode = tplTypeCode;
	}

	public List<Long> getChannelIdList() {
		return channelIdList;
	}

	public void setChannelIdList(List<Long> channelIdList) {
		this.channelIdList = channelIdList;
	}

	public List<Long> getChannelListIdList() {
		return channelListIdList;
	}

	public void setChannelListIdList(List<Long> channelListIdList) {
		this.channelListIdList = channelListIdList;
	}

	public boolean isPublishAllDetail() {
		return isPublishAllDetail;
	}

	public void setPublishAllDetail(boolean isPublishAllDetail) {
		this.isPublishAllDetail = isPublishAllDetail;
	}

	public Long getDetailMinId() {
		return detailMinId;
	}

	public void setDetailMinId(Long detailMinId) {
		this.detailMinId = detailMinId;
	}

	public Long getDetailMaxId() {
		return detailMaxId;
	}

	public void setDetailMaxId(Long detailMaxId) {
		this.detailMaxId = detailMaxId;
	}

	public List<Long> getDetailIdList() {
		return detailIdList;
	}

	public void setDetailIdList(List<Long> detailIdList) {
		this.detailIdList = detailIdList;
	}

}
