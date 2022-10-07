package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LogAspect {
	
	@Before("execution(* *..*.*UserService.*(..))")
	public void startLog(JoinPoint jp) {
		log.info("Method Start:" + jp.getSignature());
	}
	
	@After("execution(* *..*.*UserService.*(..))")
	public void afterLog(JoinPoint jp) {
		log.info("Method End:" + jp.getSignature());
	}

	@Around("@within(org.springframework.stereotype.Controller)")
	public Object startLog(ProceedingJoinPoint jp) throws Throwable{
		log.info("Method Start:" + jp.getSignature());
		
		try {
			Object result = jp.proceed();
			
			log.info("Method End:" + jp.getSignature());
			
			return result;
		}catch(Exception e) {
			log.error("Method End With Error:" + jp.getSignature());
			
			throw e;
		}
	}
}
