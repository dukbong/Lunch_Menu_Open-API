package com.lmp.lunchmenu.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCheck {
	private String act;
	private String menuName;
	private int code;
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