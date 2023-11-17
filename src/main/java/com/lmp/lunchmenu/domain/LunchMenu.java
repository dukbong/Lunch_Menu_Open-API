package com.lmp.lunchmenu.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LunchMenu {
	@JsonProperty("Menu_Seq ")
	private String menuNum;  // 메뉴 번호
	@JsonProperty("Menu_Name")
	private String menuName; // 메뉴 이름
	@JsonProperty("Use_YN   ")
	private String useYn;    // 매뉴 사용 여부
}
