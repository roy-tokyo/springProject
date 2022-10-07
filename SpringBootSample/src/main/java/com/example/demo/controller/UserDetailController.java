package com.example.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.user.model.MUser;
import com.example.demo.domain.user.service.UserService;
import com.example.demo.form.UserDetailForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserDetailController {
	@Autowired
	private UserService userservice;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/detail/{userId:.+}")
	public String getUser(UserDetailForm form, Model model, @PathVariable("userId") String userId) {
		
		MUser user = userservice.getUserOne(userId);
		user.setPassword(null);
		
		form = modelMapper.map(user, UserDetailForm.class);
		form.setSalaryList(user.getSalaryList());
		
		model.addAttribute("userDetailForm", form);
		
		return "user/detail";
	}
	
	@PostMapping(value="/detail", params="update")
	public String updateUser(UserDetailForm form, Model model){
		try {
			userservice.updateUserOne(form.getUserId(), form.getPassword(), form.getUserName());
		}catch(Exception e) {
			log.error("ユーザー更新でエラー", e);
		}
		return "redirect:/user/list";
	}

	@PostMapping(value="/detail", params="delete")
	public String deleteUser(UserDetailForm form, Model model){
		userservice.deletUserOne(form.getUserId());
		return "redirect:/user/list";
	}
}
