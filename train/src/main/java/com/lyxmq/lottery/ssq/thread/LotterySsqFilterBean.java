package com.lyxmq.lottery.ssq.thread;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LotterySsqFilterBean {
	private List redList;
	private Lock lock = new ReentrantLock();

	public List getRedList() {
		return redList;
	}

	public void setRedList(List redList) {
		this.redList = redList;
	}

	public Lock getLock() {
		return lock;
	}

	public void setLock(Lock lock) {
		this.lock = lock;
	}

}
