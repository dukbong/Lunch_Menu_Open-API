package com.lmp.lunchmenu.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lmp.lunchmenu.dao.LunchDao;
import com.lmp.lunchmenu.domain.APICheck;
import com.lmp.lunchmenu.domain.AddCheck;
import com.lmp.lunchmenu.domain.LunchMenu;
import com.lmp.lunchmenu.service.LunchService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class LunchServiceImpl implements LunchService {

	private final LunchDao lunchDao;
	
//	@Value("${jwt.secret}")
//	private String secretKey;
//	private long expiredMs = 1000 * 60l;
	
//	@Override
//	public String keyLog(String key){
//		return JwtUtil.createJwt(key, secretKey, expiredMs);
//	}
	
	@Override
	public LunchMenu getRandomMenu() {
		List<LunchMenu> menuList = lunchDao.getAllMenu();
		int ran = (int)(Math.random() * menuList.size());
		return menuList.get(ran);
	}

	@Override
	public AddCheck addMenu(String menuName) {
		menuName = menuName.replaceAll(" ", "");
		
		AddCheck addCheck = new AddCheck();
		addCheck.setAct("Add Menu");
		addCheck.setMenuName(menuName);
		Optional<LunchMenu> menu = lunchDao.checkMenu(menuName);
		System.out.println(menu.isPresent());
		if(menu.isPresent()){
			addCheck.setCode(-1);
		}else{
			int resultCode = lunchDao.addMenu(menuName);
			addCheck.setCode(resultCode);
		}
		
		return addCheck;
	}

	@Override
	public List<LunchMenu> getAllMenu() {
		return lunchDao.getAllMenu();
	}

	@Override
	public AddCheck deleteMenu(String menuName) {
		menuName = menuName.replaceAll(" ", "");

		AddCheck addCheck = new AddCheck();
		addCheck.setAct("Delete Menu");
		addCheck.setMenuName(menuName);
		
		Optional<LunchMenu> menu = lunchDao.checkMenu(menuName);
		
		if(menu.isPresent()){
			int resultCode = lunchDao.deleteMenu(menuName);
			addCheck.setCode(resultCode);
		}else{
			addCheck.setCode(-1);
		}
		return addCheck;
	}

	@Override
	public Optional<APICheck> apiCheck(String keyValue) {
		return lunchDao.apiCheck(keyValue);
	}

	@Override
	public APICheck keyIssuance(String keyValue, Date keyMaxAge) {
		Map<String, Object> params = new HashMap<>();
		params.put("IN_KEY_VALUE", keyValue);
		params.put("OUT_KEY_NUM", "");
		params.put("OUT_KEY_ISSUED", "");
		params.put("OUT_KEY_MAX_AGE", "");
		params.put("OUT_STATUS", "");
		params.put("OUT_NOTE", "");
		params.put("OUT_CODE", "");

		lunchDao.keyIssuance(params);
		
		APICheck apiCheck = new APICheck();
		apiCheck.setKeyValue(keyValue);
		
		if(params.get("OUT_CODE").toString().equals("Y")){
			log.info("key issuance success");
			apiCheck.setKeyNum(params.get("OUT_KEY_NUM").toString());
			apiCheck.setKeyIssued(params.get("OUT_KEY_ISSUED").toString());
			apiCheck.setKeyMaxAge(params.get("OUT_KEY_MAX_AGE").toString());
			apiCheck.setStatus(params.get("OUT_STATUS").toString());
			apiCheck.setNote(params.get("OUT_NOTE").toString());
			
			return apiCheck;
		}else{
			log.info("key issuance failed");
			apiCheck.setStatus(params.get("OUT_STATUS").toString());
			apiCheck.setStatus(params.get("OUT_NOTE").toString());
			
			return apiCheck;
		}
	}

}
