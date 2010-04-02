package com.lotteryCommend.model.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "lottery_authority")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Authority implements Serializable {
	private static final long serialVersionUID = 2190699727132221271L;
	private Long id;
	private String authorityName;

	public Authority() {
	}

	public Authority(Long id, String authorityName) {
		this.id = id;
		this.authorityName = authorityName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "authority_name")
	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

}
