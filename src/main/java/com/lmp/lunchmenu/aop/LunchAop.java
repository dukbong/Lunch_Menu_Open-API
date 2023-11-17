package com.lmp.lunchmenu.aop;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.lmp.lunchmenu.dao.LunchDao;
import com.lmp.lunchmenu.domain.APICheck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * 
 * 검증 aop >> 현재는 filter로 대체
 * 
 * @author jang
 *
 */
@Aspect
@Component
@RequiredArgsConstructor
public class LunchAop {

	private final LunchDao lunchDao;
	
	// @Around("execution(* com.lmp.lunchmenu.controller.*.*(..))")
	public Object checkKey(ProceedingJoinPoint joinPoint) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();

		String keyValue = extractionKey(request.getRequestURL().toString());
		
		Optional<APICheck> apiCheck = lunchDao.apiCheck(keyValue);
		
		if (apiCheck.isPresent()) {
			if (timeOutCheck(apiCheck.get().getKeyMaxAge())) {
//				apiCheck.get().setCode(1);
				sendResponse(response, apiCheck.get());
			} else {
				try {
					return joinPoint.proceed();
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		} else {
			APICheck failKey = new APICheck();
			failKey.setKeyValue(keyValue);
//			failKey.setCode(2);
			sendResponse(response, failKey);
		}
		return null;
	}

	private void sendResponse(HttpServletResponse response, APICheck apiCheck) {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		ObjectMapper objMapper = new ObjectMapper();
		objMapper.enable(SerializationFeature.INDENT_OUTPUT);
		
		try {
			String result = objMapper.writeValueAsString(apiCheck);
			response.getOutputStream().write(result.getBytes());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean timeOutCheck(String keyMaxAge) {
		LocalDateTime now = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(keyMaxAge);
		LocalDateTime keyMaxAgeTime = timestamp.toLocalDateTime();
		return keyMaxAgeTime.isBefore(now);
	}

	private String extractionKey(String url) {
		return url.split("/")[3].split("=")[1];
	}
}
