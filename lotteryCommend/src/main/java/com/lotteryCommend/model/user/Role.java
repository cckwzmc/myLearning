package com.lotteryCommend.model.user;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name="lottery_user_role")
public class Role implements Serializable{

	private static final long serialVersionUID = 5646990331423283443L;
	private String id;
	private String userId;
	private String roleId;
}
