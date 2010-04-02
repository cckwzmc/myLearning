package com.lotteryCommend.model.user;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * User Bean
 * 
 * @author LIYI
 * 
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "lottery_user")
public class User implements Serializable {

	private static final long serialVersionUID = 2738450849497302912L;
	private String id;
	private String username;
	private String password;
	private String nickname;// or screenname
	private String fullName;// 为了兼容国外习惯而使用
	private Date dateCreated;
	private String emailAddress;
	private Boolean enabled = Boolean.TRUE;
	private String activationCode;

	private Set<Role> roles = new HashSet<Role>();

	public User() {
	}

	public User(String id, String username, String password, String nickname, String fullName, Date dateCreated, String emailAddress, Boolean enabled, String activationCode) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.fullName = fullName;
		this.dateCreated = dateCreated;
		this.emailAddress = emailAddress;
		this.enabled = enabled;
		this.activationCode = activationCode;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "username", nullable = false, unique = true)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "nickname")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name = "fullname")
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column(name = "datecreated")
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Column(name = "email", nullable = false, unique = true)
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Column(name = "isenabled", nullable = false)
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Column(name = "activation_code")
	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	// 多对多定义，cascade操作避免定义CascadeType.REMOVE
	@ManyToMany(fetch = FetchType.EAGER)
	// 中间表定义,表名采用默认命名规则
	@JoinTable(name = "lottery_user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	@Fetch(FetchMode.JOIN)
	// 集合按id排序.
	@OrderBy("id")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public boolean equals(Object other) {
		if (other == this)
			return true;
		if (other instanceof User != true)
			return false;
		User o = (User) other;

		return new EqualsBuilder().append(this.getUsername(), o.getUsername()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getUsername()).toHashCode();
	}
}
