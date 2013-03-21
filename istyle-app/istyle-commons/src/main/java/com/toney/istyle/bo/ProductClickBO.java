/**
 * 
 */
package com.toney.istyle.bo;

import java.io.Serializable;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :ProductClickBo.java
 * @DESCRIPTION :商品统计数据.
 * @AUTHOR :ly_zy_ljh
 * @VERSION :v1.0
 * @DATE :2013-3-19
 *       </p>
 **************************************************************** 
 */
public class ProductClickBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7010824813703134940L;

	private Long pid;
	private Long totalClick;
	private Long transferNumber;
	private Long likeNumber;
	private Long commentNumber;
	private Long recommendNumber;

	/**
	 * @return the pid
	 */
	public Long getPid() {
		return pid;
	}

	/**
	 * @param pid
	 *            the pid to set
	 */
	public void setPid(Long pid) {
		this.pid = pid;
	}

	/**
	 * @return the totalClick
	 */
	public Long getTotalClick() {
		return totalClick;
	}

	/**
	 * @param totalClick
	 *            the totalClick to set
	 */
	public void setTotalClick(Long totalClick) {
		this.totalClick = totalClick;
	}

	/**
	 * @return the transferNumber
	 */
	public Long getTransferNumber() {
		return transferNumber;
	}

	/**
	 * @param transferNumber
	 *            the transferNumber to set
	 */
	public void setTransferNumber(Long transferNumber) {
		this.transferNumber = transferNumber;
	}

	/**
	 * @return the likeNumber
	 */
	public Long getLikeNumber() {
		return likeNumber;
	}

	/**
	 * @param likeNumber
	 *            the likeNumber to set
	 */
	public void setLikeNumber(Long likeNumber) {
		this.likeNumber = likeNumber;
	}

	/**
	 * @return the commentNumber
	 */
	public Long getCommentNumber() {
		return commentNumber;
	}

	/**
	 * @param commentNumber
	 *            the commentNumber to set
	 */
	public void setCommentNumber(Long commentNumber) {
		this.commentNumber = commentNumber;
	}

	/**
	 * @return the recommendNumber
	 */
	public Long getRecommendNumber() {
		return recommendNumber;
	}

	/**
	 * @param recommendNumber
	 *            the recommendNumber to set
	 */
	public void setRecommendNumber(Long recommendNumber) {
		this.recommendNumber = recommendNumber;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProductClickBo [pid=" + pid + ", totalClick=" + totalClick + ", transferNumber=" + transferNumber + ", likeNumber=" + likeNumber + ", commentNumber="
				+ commentNumber + ", recommendNumber=" + recommendNumber + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductClickBO other = (ProductClickBO) obj;
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
