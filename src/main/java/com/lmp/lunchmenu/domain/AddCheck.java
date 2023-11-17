package com.lmp.lunchmenu.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCheck {
	@JsonProperty("Act       ")
	private String act;
	@JsonProperty("Menu_Name ")
	private String menuName;
	@JsonProperty("Code      ")
	private int code;
	@JsonProperty("Status    ")
	private String status;
	
	public void setCode(int code) {
		this.code = code;
		switch (code) {
		case -1:
			this.status = "FAILED";
			break;
		case 1 :
			this.status = "SUCCESS";
			break;
		}
	}
	
	
	
}