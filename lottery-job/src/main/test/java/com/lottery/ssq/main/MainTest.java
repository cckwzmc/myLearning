package com.lottery.ssq.main;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.lottery.ssq.config.LotterySsqFilterConfig;

public class MainTest {
	public static void main(String[] args) {
		try {
			Map map=BeanUtils.describe(new LotterySsqFilterConfig());
			System.out.println(map);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
