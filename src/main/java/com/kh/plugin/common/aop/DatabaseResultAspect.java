package com.kh.plugin.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.kh.plugin.exception.DataPersistenceException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class DatabaseResultAspect {

	@Around("execution(* com.kh.plugin..*Mapper.signUp(..)) || "
		  + "execution(* com.kh.plugin..*Mapper.save*(..)) || "	
		  + "execution(* com.kh.plugin..*Mapper.update*(..)) || "	
		  + "execution(* com.kh.plugin..*Mapper.delete*(..)) || "	
		  + "execution(* com.kh.plugin..*Mapper.increaseCount(..)) || "	
		  + "execution(* com.kh.plugin..*Mapper.logout(..))")
	public Object validateMapperResult(ProceedingJoinPoint joinPoint) throws Throwable {
		String methodName = joinPoint.getSignature().getName();
		Object result = joinPoint.proceed();
		if (result instanceof Integer affectedRows) {
            if (affectedRows < 1) {
            	log.error("[DB 영속성 검증 실패] 메서드명: {} -> 반영된 행의 수가 0개입니다.", methodName);
                throw new DataPersistenceException("잠시후 다시 시도해주세요.");
            }
        }
		return result;
	}
}
