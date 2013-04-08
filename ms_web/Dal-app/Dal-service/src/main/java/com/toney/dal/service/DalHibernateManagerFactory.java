package com.toney.dal.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.toney.dal.model.Page;

/**
 * 
 *************************************************************** 
 * <p>
 * 
 * @CLASS :DalManagerFactory 
 * @DESCRIPTION :Dao 统一入口，这种方式比较适合hibernate，ibatis 不是很适合，基本上都使用cache，，这是非常理想的状态了。
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Dec 24, 2012
 *       </p>
 **************************************************************** 
 */
public interface DalHibernateManagerFactory<T, PK extends Serializable> {

	public List<T> list();

	public  T get(PK id);
	
	public List<T> listByIds(List<PK> ids);
	
	public T save(T object);

	void remove(PK id);

	public Page<T> listPage(T object, Map<String, Object> map);

	public Page<T> listPage(T object, final Object... objects);

	public List<T> list(T object, final Object... objects);

	public List<T> list(T object, Map<String, Object> map);
}
