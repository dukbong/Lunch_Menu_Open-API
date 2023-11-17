package com.lmp.lunchmenu.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LunchMenu {
	private String menuNum;  // 메뉴 번호
	private String menuName; // 메뉴 이름
	private String useYn;    // 매뉴 사용 여부
}
