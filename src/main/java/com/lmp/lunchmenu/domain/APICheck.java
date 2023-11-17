package com.lmp.lunchmenu.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class APICheck {
	
	@JsonProperty("Key_SEQ   ")
	private String keyNum;
	@JsonProperty("Key_VALUE ")
	private String keyValue;
	@JsonProperty("Key_Issued")
	private String keyIssued;
	@JsonProperty("Key_MaxAge")
	private String keyMaxAge;
	
	
//	@JsonProperty("Code      ")
//	private int code;
	@JsonProperty("Status    ")
	private String status;
	
	@JsonProperty("Note      ")
//	private String note = "If you wish to extend the validity period further, please contact us at test@gmail.com";
	private String note;
	
//	public void setCode(int code) {
//		this.code = code;
//		
//		switch (code) {
//		case 1:
//			this.status = "The authentication key has now expired.  Please request a new authentication key.";
//			break;
//		case 2 :
//			this.status = "You have entered an invalid authentication key.  Please double-check and try again.";
//			break;
//		}
//	}
}
