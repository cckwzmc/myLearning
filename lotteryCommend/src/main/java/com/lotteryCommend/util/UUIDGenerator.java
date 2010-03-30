package com.lotteryCommend.util;

import java.util.UUID;

/**
 * 
 * @author LIYI
 *
 */
public class UUIDGenerator {
	public UUIDGenerator() {
	}
	/**
	 * generator a new UUID
	 * @return
	 */
	public static String generatorUUID(){
		return UUID.randomUUID().toString();
	}
}
