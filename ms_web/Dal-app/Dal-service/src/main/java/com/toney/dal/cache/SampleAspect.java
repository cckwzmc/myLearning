package com.toney.dal.cache;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class SampleAspect {
	@Before("execution(* com.toney.dal.cache..*.get*(..))")
	public void doBeforeServiceLayer() {
		System.out.println("=====================================");
		System.out.println("Aop: do before in Service layer");
		System.out.println("=====================================");
	}
}
