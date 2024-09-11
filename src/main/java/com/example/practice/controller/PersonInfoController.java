package com.example.practice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.practice.service.ifs.PersonInfoService;
import com.example.practice.vo.UpdateInfoReq;
import com.example.practice.vo.UpdateInfoRes;

@RestController
public class PersonInfoController {
	
	@Autowired
	private PersonInfoService service;
	
	@PostMapping(value = "person_info/update")
	public UpdateInfoRes updateInfo(@Valid @RequestBody UpdateInfoReq req) {
		if (!StringUtils.hasText(req.getName())) {
			req.setName(null);
		}
		if (!StringUtils.hasText(req.getCity())) {
			req.setCity(null);
		}
		return service.updateInfo(req);
	}
}
