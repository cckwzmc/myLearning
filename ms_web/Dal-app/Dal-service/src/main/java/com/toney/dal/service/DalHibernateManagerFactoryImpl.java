package com.toney.dal.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.toney.commons.lang.Page;

public class DalHibernateManagerFactoryImpl<T,PK  extends  Serializable> implements DalHibernateManagerFactory<T, PK> {

	@Override
	public List<T> list() {
		return null;
	}

	@Override
	public T get(PK id) {
		return null;
	}

	@Override
	public T save(T object) {
		return null;
	}

	@Override
	public void remove(PK id) {

	}

	@Override
	public Page<T> listPage(T object, Map<String, Object> map) {
		return null;
	}

	@Override
	public Page<T> listPage(T object, Object... objects) {
		return null;
	}

	@Override
	public List<T> list(T object, Object... objects) {
		return null;
	}

	@Override
	public List<T> list(T object, Map<String, Object> map) {
		return null;
	}

	@Override
	public List<T> listByIds(List<PK> ids) {
		return null;
	}

}
