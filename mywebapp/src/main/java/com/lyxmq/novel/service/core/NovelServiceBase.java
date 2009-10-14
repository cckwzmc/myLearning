package com.lyxmq.novel.service.core;

import org.springframework.stereotype.Service;

import com.lyxmq.novel.hibernate.core.PersistenceStrategy;

@Service("novelServiceBase")
public class NovelServiceBase {
	private PersistenceStrategy persistenceStrategy = null;

	public PersistenceStrategy getPersistenceStrategy() {
		return persistenceStrategy;
	}

	public void setPersistenceStrategy(PersistenceStrategy persistenceStrategy) {
		this.persistenceStrategy = persistenceStrategy;
	}

}
