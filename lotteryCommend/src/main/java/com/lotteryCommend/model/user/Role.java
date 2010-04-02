package com.lotteryCommend.model.user;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "lottery_role")
public class Role implements Serializable {

	private static final long serialVersionUID = 5646990331423283443L;
	private Long id;
	private String roleName;
	private String screenName;
	private Set<Authority> Authorities = new HashSet<Authority>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "role_name", nullable = false)
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Column(name="screen_name",nullable=false)
	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	@ManyToMany
	@JoinTable(name="lottery_role_authority",joinColumns={@JoinColumn(name="role_id")},inverseJoinColumns={@JoinColumn(name="authority_id")})
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("id")
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	public Set<Authority> getAuthorities() {
		return Authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		Authorities = authorities;
	}

}
