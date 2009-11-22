package com.lyxmq.novel.model.cms.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.lyxmq.novel.model.BaseModel;

/**
 * CMS在首页的位置（包括频道首页）
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name="cms_articleLocation")
public class CmsArtLocation extends BaseModel {
	private static final long serialVersionUID = 3455915664695658265L;

	private int id;
	private String flag;
	private String desc;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column(name="flag",length=2,nullable=false)
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Column(name="desc",length=10,nullable=false)
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @param o
	 * @return
	 */
	@SuppressWarnings("null")
	@Override
	public boolean equals(Object o) {
		if(this==o){
			return true;
		}
		if(!(o instanceof CmsArtLocation)){
			return false;
		}
		CmsArtLocation cmsArtLocation=(CmsArtLocation) o; 
		return !(cmsArtLocation!=null?!cmsArtLocation.getFlag().equals(this.getFlag()):cmsArtLocation.getFlag()!=null);
	}

	@Override
	public int hashCode() {
		return flag!=null?flag.hashCode():0;
	}

	@Override
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE);
		sb.append("flag",this.flag);
		sb.append("desc",this.desc);
		return sb.toString();
	}
}
