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
@Table(name = "lottery_role_authority")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RoleAuthority implements Serializable {
	private static final long serialVersionUID = 2190699727132221271L;
	private Long id;
	private Long roleId;
	private Long authoriryId;

	public RoleAuthority() {
	}

	public RoleAuthority(Long id, Long roleId, Long authorityId) {
		this.id = id;
		this.roleId = roleId;
		this.authoriryId = authorityId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "role_id", nullable = false)
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Column(name = "authority_id", nullable = false)
	public Long getAuthoriryId() {
		return authoriryId;
	}

	public void setAuthoriryId(Long authoriryId) {
		this.authoriryId = authoriryId;
	}

}
