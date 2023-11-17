package com.lmp.lunchmenu.controller;

import java.util.Date;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmp.lunchmenu.domain.APICheck;
import com.lmp.lunchmenu.service.LunchService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiIssuanceController {
	
	private final LunchService LunchServiceImpl;
	
	@GetMapping("apiKey_issuance")
	public APICheck ApiKeyIssuance(){
		UUID tiCode = UUID.randomUUID();
		return LunchServiceImpl.keyIssuance(tiCode.toString(), new Date());
	}
}
