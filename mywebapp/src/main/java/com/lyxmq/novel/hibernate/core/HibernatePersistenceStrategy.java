package com.lyxmq.novel.hibernate.core;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;

import com.lyxmq.novel.exception.NovelPersistenceException;

@Service("persistenceStrategy")
public class HibernatePersistenceStrategy extends HibernateDaoSupport {

	private static Log logger = LogFactory.getFactory().getInstance(HibernatePersistenceStrategy.class);

	/**
	 * Flush changes to the datastore, commit transaction, release em.
	 * 
	 * @throws NovelPersistenceException
	 */
	public void flush() throws NovelPersistenceException {
		super.getHibernateTemplate().flush();
	}

	/**
	 * Release database session, rolls back any uncommitted changes.
	 */
	public void release() throws NovelPersistenceException {
		super.getHibernateTemplate().clear();
	}

	/**
	 * Save Entity using an existing transaction
	 * 
	 * @param obj
	 * @return the persist object
	 * @throws NovelPersistenceException
	 */
	public Object store(Object obj) throws NovelPersistenceException {
		super.getHibernateTemplate().persist(obj);
		return obj;
	}

	/**
	 * Remove object from persistence storage.
	 * 
	 * @param clazz
	 * @param id
	 * @throws NovelPersistenceException
	 */
	@SuppressWarnings("unchecked")
	public void remove(Class clazz, Serializable id) throws NovelPersistenceException {
		Object obj = super.getHibernateTemplate().load(clazz, id);
		super.getHibernateTemplate().delete(obj);
	}

	/**
	 * Remove object from persistence storage.
	 * 
	 * @param clazz
	 * @param id
	 * @throws NovelPersistenceException
	 */
	public void remove(Object obj) throws NovelPersistenceException {
		super.getHibernateTemplate().delete(obj);
	}

	/**
	 * Remove object from persistence storage.
	 * 
	 * @param clazz
	 * @param id
	 * @throws NovelPersistenceException
	 */
	@SuppressWarnings("unchecked")
	public void removeAll(Collection co) throws NovelPersistenceException {
		for (Iterator iterator = co.iterator(); iterator.hasNext();) {
			Object obj = (Object) iterator.next();
			super.getHibernateTemplate().delete(obj);
		}
	}

	/**
	 * Retrieve object,
	 * 
	 * @param clazz
	 *            the class of object to retrieve
	 * @param id
	 *            the id of the object to retrieve
	 * @return the object retrieved
	 * @throws WebloggerException
	 *             on any error retrieving object
	 */
	@SuppressWarnings("unchecked")
	public Object get(Class clazz, String id) throws NovelPersistenceException {
		return super.getHibernateTemplate().load(clazz, id);
	}

	/**
	 * Retrieve object List,
	 * 
	 * @param clazz
	 * @return
	 * @throws NovelPersistenceException
	 */
	@SuppressWarnings("unchecked")
	public List getAll(Class clazz) throws NovelPersistenceException {
		return super.getHibernateTemplate().loadAll(clazz);
	}

	/**
	 * 按参数传递查询，key	- value的方式
	 * @param hql
	 * @param filterMap hql中的参数
	 * @return
	 * @throws NovelPersistenceException
	 */
	@SuppressWarnings("unchecked")
	public List getAll(String hql, Map filterMap) throws NovelPersistenceException {
		Session session = super.getSession();
		Query query = session.createQuery(hql);
		setFilterToQuery(query, filterMap);
		return query.list();
	}

	/**
	 * 把filterMap中的参数整合入HQL
	 * @param query
	 * @param filterMap
	 * @throws NovelPersistenceException
	 */
	@SuppressWarnings("unchecked")
	public void setFilterToQuery(org.hibernate.Query query, Map filterMap) throws NovelPersistenceException {
		if (filterMap == null)
			return;
		Set entries = filterMap.entrySet();
		for (Object iter : entries) {
			Map.Entry entry = (Map.Entry) iter;
			Object key = entry.getKey();

			Object value = entry.getValue();
			query.setParameter(key.toString(), value);
		}
	}
}
