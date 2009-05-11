package com.iris.scholar.system.service;

import com.iris.scholar.server.ws.dao.ScholarFetchDao;

public class DAOInstanceService {
	/**
	 * Sets AutowireMode to AUTOWIRE_BY_NAME and configures all context files
	 * needed to tests DAOs.
	 * 
	 * @return String array of Spring context files.
	 */
	protected String[] getConfigLocations() {

		// setAutowireMode(BeanWiringInfo.AUTOWIRE_BY_NAME);
		return new String[] { "classpath:/spring/applicationContext*.xml" };
	}

	protected ScholarFetchDao getFetchDao() {
		ScholarFetchDao dao = null;

		return dao;
	}

//	public static void main(String[] args) {
//		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/spring/applicationContext*.xml");
//		ScholarFetchDao fetchDao = (ScholarFetchDao) context.getBean("scholarFetchDao");
//		if (fetchDao == null) {
//			System.out.println("fetchDao is null!!");
//		}
//	}
}
