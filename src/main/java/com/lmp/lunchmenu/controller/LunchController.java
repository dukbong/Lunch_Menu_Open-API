package com.lmp.lunchmenu.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmp.lunchmenu.domain.AddCheck;
import com.lmp.lunchmenu.domain.LunchMenu;
import com.lmp.lunchmenu.service.LunchService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/key={key}")
@RequiredArgsConstructor
public class LunchController {

	private final LunchService LunchServiceImpl;
	private final MessageSource messageSource;
	

	@GetMapping("lunch")
	public LunchMenu randomLunch() {
		return LunchServiceImpl.getRandomMenu();
	}

	@GetMapping("lunch/all")
	public List<LunchMenu> allMenu() {
		return LunchServiceImpl.getAllMenu();
	}

	@PostMapping("lunch/{menuName}")
	public AddCheck addMenu(@PathVariable String menuName) {
		return LunchServiceImpl.addMenu(menuName);
	}

	@DeleteMapping("lunch/{menuName}")
	public AddCheck deleteMenu(@PathVariable String key, @PathVariable String menuName) {
		if(key.equals(messageSource.getMessage("masterKey.key",new String[]{}, Locale.getDefault()))){
			return LunchServiceImpl.deleteMenu(menuName);
		}else{
			AddCheck addCheck = new AddCheck();
			addCheck.setAct("Delete");
			addCheck.setStatus("관리자만 삭제가 가능합니다.");
			return addCheck;
		}
	}
}
