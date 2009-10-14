package com.lyxmq.novel.hibernate.core;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;

import com.lyxmq.novel.exception.NovelPersistenceException;

@Service("persistenceStrategy")
public class PersistenceStrategy extends HibernateDaoSupport {

	private static Log logger = LogFactory.getFactory().getInstance(PersistenceStrategy.class);

	/**
	 * The thread local EntityManager.
	 */
	private final ThreadLocal threadLocalEntityManager = new ThreadLocal();

	/**
	 * The EntityManagerFactory for this Roller instance.
	 */
	private EntityManagerFactory emf = null;
	
	public Object save(Object obj) throws NovelPersistenceException{
		EntityManager em=getEntityManager(true);
		if(!em.contains(obj)){
			 // If entity is not managed we can assume it is new
			super.getHibernateTemplate().persist(obj);
			//em.persist(obj);
		}
		return obj;
	}
	/**
	 *  Get the EntityManager associated with the current thread of control.
     * @param isTransactionRequired true if a transaction is begun if not
     * already active
	 * @return EntityManager
	 */
	public  EntityManager getEntityManager(boolean isTransactionRequired) {
		// TODO Auto-generated method stub
		return null;
	}
}
