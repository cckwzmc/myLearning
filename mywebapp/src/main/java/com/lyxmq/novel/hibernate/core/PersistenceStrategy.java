package com.lyxmq.novel.hibernate.core;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

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
	
	/**
     * Flush changes to the datastore, commit transaction, release em.
	 * @throws NovelPersistenceException
	 */
	public void flush( ) throws NovelPersistenceException{
		EntityManager em=getEntityManager(true);
		em.getTransaction().commit();
	}
	 /**
     * Release database session, rolls back any uncommitted changes.
     */
	public void release() throws NovelPersistenceException{
		EntityManager em=getEntityManager(true);
		em.getTransaction().rollback();
		em.clear();
		em.close();
		setThreadLocaleEntityManager(null);
	}
	 /**
     * Set the current ThreadLocal EntityManager
     */
	@SuppressWarnings("unchecked")
	public void setThreadLocaleEntityManager(Object object) {
		threadLocalEntityManager.set(object);
	}
	/**
	 * Save Entity using an  existing transaction
	 * @param obj
	 * @return the persist object
	 * @throws NovelPersistenceException
	 */
	public Object store(Object obj) throws NovelPersistenceException{
		EntityManager em=getEntityManager(true);
		if(!em.contains(obj)){
			 // If entity is not managed we can assume it is new
			em.persist(obj);
			//em.persist(obj);
		}
		return obj;
	}
	/**
     * Remove object from persistence storage.
	 * @param clazz
	 * @param id
	 * @throws NovelPersistenceException
	 */
	@SuppressWarnings("unchecked")
	public void remove(Class clazz,Serializable id) throws NovelPersistenceException{
		EntityManager em=getEntityManager(true);
		Object obj=em.find(clazz, id);
		em.remove(obj);
	}
	/**
	 * Remove object from persistence storage.
	 * @param clazz
	 * @param id
	 * @throws NovelPersistenceException
	 */
	public void remove(Object obj) throws NovelPersistenceException{
		EntityManager em=getEntityManager(true);
		em.remove(obj);
	}
	/**
	 * Remove object from persistence storage.
	 * @param clazz
	 * @param id
	 * @throws NovelPersistenceException
	 */
	@SuppressWarnings("unchecked")
	public void removeAll(Collection  co) throws NovelPersistenceException{
		EntityManager em=getEntityManager(true);
		for (Iterator iterator = co.iterator(); iterator.hasNext();) {
			Object obj = (Object) iterator.next();
			em.remove(obj);
		}
	}
	/**
	 *  Get the EntityManager associated with the current thread of control.
     * @param isTransactionRequired true if a transaction is begun if not
     * already active
	 * @return EntityManager
	 */
	public  EntityManager getEntityManager(boolean isTransactionRequired) {
		EntityManager em=getThreadLocaleEntityManager();
		if(isTransactionRequired&&!em.getTransaction().isActive()){
			em.getTransaction().begin();
		}
		return em;
	}
	  
    /**
     * Get the current ThreadLocal EntityManager
     */
	@SuppressWarnings("unchecked")
	private EntityManager getThreadLocaleEntityManager() {
		EntityManager em=(EntityManager) threadLocalEntityManager.get();
		if(em==null){
			emf.createEntityManager();
			threadLocalEntityManager.set(emf);
		}
		return em;
	}
	
	
}
