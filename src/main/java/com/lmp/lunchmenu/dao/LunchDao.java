package com.lmp.lunchmenu.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lmp.lunchmenu.domain.APICheck;
import com.lmp.lunchmenu.domain.LunchMenu;

@Mapper
public interface LunchDao {
	
	List<LunchMenu> getRandomMenu();

	int addMenu(String menuName);

	Optional<LunchMenu> checkMenu(String menuName);

	List<LunchMenu> getAllMenu();

	int deleteMenu(String menuName);

	Optional<APICheck> apiCheck(String keyValue);

	Optional<APICheck> keyIssuance(@Param("keyValue") String keyValue, @Param("KeyMaxAge") Date keyMaxAge);
	
	HashMap<String, Object> keyIssuance(Map<String, Object> params);
}
