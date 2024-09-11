package com.example.practice.service.ifs;

import java.util.List;

import com.example.practice.entity.PersonInfo;
import com.example.practice.vo.UpdateInfoReq;
import com.example.practice.vo.UpdateInfoRes;

public interface PersonInfoService {
	public void addInfo(PersonInfo personInfo);
	
	public void getAllInfo();
	
	public void findById(String id);
	
	public void findAgeOlder(int age);
	
	public void findAgeYounger(int age);
	
	public void findAgeYoungerOrOlder(int age1, int age2);
	
	public void findByAgeBetween(int age1, int age2);
	
	public void findCity01(String city);
	
	public void findCity02(String city);
	
	public void findAgeOlderByCity(int age, String city);
	
	public void findByCity(List<String> cityList);
	
	public UpdateInfoRes updateInfo(UpdateInfoReq req);
}
