package com.lmp.lunchmenu.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.lmp.lunchmenu.domain.APICheck;
import com.lmp.lunchmenu.domain.AddCheck;
import com.lmp.lunchmenu.domain.LunchMenu;

public interface LunchService {

	LunchMenu getRandomMenu();

	AddCheck addMenu(String menuName);

	List<LunchMenu> getAllMenu();

	AddCheck deleteMenu(String menuName);

	Optional<APICheck> apiCheck(String keyValue);

	APICheck keyIssuance(String keyValue, Date date);
	
}
