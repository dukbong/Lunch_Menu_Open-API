package com.lmp.lunchmenu.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lmp.lunchmenu.dao.LunchDao;
import com.lmp.lunchmenu.filter.LunchFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {
	
	private final LunchDao lunchDao;
	
    @Bean
    public FilterRegistrationBean<LunchFilter> yourFilter() {
        FilterRegistrationBean<LunchFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LunchFilter(lunchDao));
        registrationBean.addUrlPatterns("/*"); // 모든 URL 패턴에 필터가 적용됩니다.
        return registrationBean;
    }
	
}
