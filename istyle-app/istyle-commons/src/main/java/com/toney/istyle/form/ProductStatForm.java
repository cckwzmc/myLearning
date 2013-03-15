package com.toney.istyle.form;

import com.toney.istyle.commons.BaseObject;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :ProductStatForm
 * @DESCRIPTION :商品的各种统计
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 10, 2013
 *       </p>
 **************************************************************** 
 */
public class ProductStatForm extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9002797256875309467L;

	private Long pid;
	/**
	 * 浏览次数
	 */
	private Long totalClick;
	/**
	 * 跳转到商家的次数
	 */
	private Long transferNumber;
	/**
	 * 喜欢数量
	 */
	private Long likeNumber;
	/**
	 * 评论数
	 */
	private Long commentNumber;
	/**
	 * 推荐数
	 */
	private Long recommendNumber;

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Long getTotalClick() {
		return totalClick;
	}

	public void setTotalClick(Long totalClick) {
		this.totalClick = totalClick;
	}

	public Long getTransferNumber() {
		return transferNumber;
	}

	public void setTransferNumber(Long transferNumber) {
		this.transferNumber = transferNumber;
	}

	public Long getLikeNumber() {
		return likeNumber;
	}

	public void setLikeNumber(Long likeNumber) {
		this.likeNumber = likeNumber;
	}

	public Long getCommentNumber() {
		return commentNumber;
	}

	public void setCommentNumber(Long commentNumber) {
		this.commentNumber = commentNumber;
	}

	public Long getRecommendNumber() {
		return recommendNumber;
	}

	public void setRecommendNumber(Long recommendNumber) {
		this.recommendNumber = recommendNumber;
	}

	@Override
	public String toString() {
		return "ProductStatForm [pid=" + pid + ", totalClick=" + totalClick + ", transferNumber=" + transferNumber + ", likeNumber=" + likeNumber + ", commentNumber="
				+ commentNumber + ", recommendNumber=" + recommendNumber + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commentNumber == null) ? 0 : commentNumber.hashCode());
		result = prime * result + ((likeNumber == null) ? 0 : likeNumber.hashCode());
		result = prime * result + ((pid == null) ? 0 : pid.hashCode());
		result = prime * result + ((recommendNumber == null) ? 0 : recommendNumber.hashCode());
		result = prime * result + ((totalClick == null) ? 0 : totalClick.hashCode());
		result = prime * result + ((transferNumber == null) ? 0 : transferNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductStatForm other = (ProductStatForm) obj;
		if (commentNumber == null) {
			if (other.commentNumber != null)
				return false;
		} else if (!commentNumber.equals(other.commentNumber))
			return false;
		if (likeNumber == null) {
			if (other.likeNumber != null)
				return false;
		} else if (!likeNumber.equals(other.likeNumber))
			return false;
		if (pid == null) {
			if (other.pid != null)
				return false;
		} else if (!pid.equals(other.pid))
			return false;
		if (recommendNumber == null) {
			if (other.recommendNumber != null)
				return false;
		} else if (!recommendNumber.equals(other.recommendNumber))
			return false;
		if (totalClick == null) {
			if (other.totalClick != null)
				return false;
		} else if (!totalClick.equals(other.totalClick))
			return false;
		if (transferNumber == null) {
			if (other.transferNumber != null)
				return false;
		} else if (!transferNumber.equals(other.transferNumber))
			return false;
		return true;
	}

}
