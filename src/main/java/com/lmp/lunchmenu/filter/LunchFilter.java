package com.lmp.lunchmenu.filter;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.lmp.lunchmenu.dao.LunchDao;
import com.lmp.lunchmenu.domain.APICheck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 검증 필터
 * 
 * @author user
 *
 */
@Slf4j
@RequiredArgsConstructor
public class LunchFilter implements Filter {

	private final LunchDao lunchDao;

	@Override
	public void init(FilterConfig filterConfig) {
		log.info("Lunch Filter Init..");
	}

	// 수정해야 할 사항
	// 항상 DB를 거치지 말고
	// DB에서 가져온 정보를 처음에 쿠키로 만들어서 클라이언트에게 전달한다.
	// 이젠 쿠기로 확인을 한다.
	// 그럼 DB를 거치지 않기 떄문에 좀더 효율적으로 운영이 가능할 것으로 보인다.
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		if (!request.getRequestURI().contains("key=")) {
			if (request.getRequestURI().equals("/")) {
				sendMessage(response, null, "Open API 사용법은 https://github.com/dukbong/Food-API 에서 확인 가능합니다.");
				return;
			}
			if(request.getRequestURI().equals("/apiKey_issuance")){
				chain.doFilter(servletRequest, servletResponse);
				return;
			}
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			sendMessage(response, null, "잘못된 경로로 접근하였습니다.");
			return;
		}
		String keyValue = request.getRequestURI().split("/")[1].split("=")[1];
		Optional<APICheck> result = lunchDao.apiCheck(keyValue);
		if (result.isPresent()) {
			if (timeOverCheck(result.get().getKeyMaxAge())) {
				chain.doFilter(servletRequest, servletResponse);
				return;
			}
		}
		sendMessage(response, keyValue, "유효하지 않은 API 키 또는 시간초과");
		return;
	}


	private void sendMessage(HttpServletResponse response, String keyValue, String note) {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		ObjectMapper objMapper = new ObjectMapper();
		try {
			String message = "";
			if (keyValue != null) {
				APICheck apiCheck = new APICheck("", keyValue, "", "", "BAD", note);
				response.setStatus(HttpStatus.BAD_REQUEST.value());
				objMapper.enable(SerializationFeature.INDENT_OUTPUT);
				message = objMapper.writeValueAsString(apiCheck);
			} else {
				message = note;
			}
			response.getOutputStream().write(message.getBytes());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private boolean timeOverCheck(String maxAge) {
		LocalDateTime now = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(maxAge);
		LocalDateTime maxAgeTime = timestamp.toLocalDateTime();
		return maxAgeTime.isAfter(now);
	}

	@Override
	public void destroy() {
		Filter.super.destroy();
	}
}
