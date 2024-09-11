package com.example.practice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.practice.constant.ResMessage;
import com.example.practice.entity.PersonInfo;
import com.example.practice.repository.PersonInfoDAO;
import com.example.practice.service.ifs.PersonInfoService;
import com.example.practice.vo.UpdateInfoReq;
import com.example.practice.vo.UpdateInfoRes;

@Service
public class PersonInfoServiceImp implements PersonInfoService {

	@Autowired
	private PersonInfoDAO personInfoDao;

	private boolean pattern(PersonInfo personInfo) {
		if (!personInfo.getId().matches("[A-Za-z][12]\\d{8}")) {
			System.out.println("-id格式錯誤-");
			return false;
		}
		if (!StringUtils.hasText(personInfo.getName())) {
			System.out.println("-name不得為空-");
			return false;
		}
		if (personInfo.getAge() < 0) {
			System.out.println("-age需大於0-");
			return false;
		}
		return true;
	}

	@Override
	public void addInfo(PersonInfo personInfo) {
		if (!pattern(personInfo)) {
			return;
		}
		if (personInfoDao.existsById(personInfo.getId())) {
			System.out.println("-資料已存在-");
			return;
		}
		System.out.println("-已成功新增資料-");
		System.out.println("id: " + personInfo.getId());
		System.out.println("name: " + personInfo.getName());
		System.out.println("age: " + personInfo.getAge());
		System.out.println("city: " + personInfo.getCity());
		personInfoDao.save(personInfo);
	}

	@Override
	public void getAllInfo() {
		List<PersonInfo> allList = personInfoDao.findAll();
		int cnt = 1;
		System.out.println("-ALL INFO-");
		for (PersonInfo item : allList) {
			System.out.println("--NO." + cnt);
			System.out.println("id: " + item.getId());
			System.out.println("name: " + item.getName());
			System.out.println("age: " + item.getAge());
			System.out.println("city: " + item.getCity());
			System.out.println("");
			cnt++;
		}
	}

	@Override
	public void findById(String id) {
		if (!id.matches("[A-Za-z][12]\\d{8}")) {
			System.out.println("-id格式錯誤-");
			return;
		}
		Optional<PersonInfo> op = personInfoDao.findById(id);
		if (op.isEmpty()) {
			System.out.println("-查無資料-");
			return;
		}
		PersonInfo item = op.get();
		System.out.println("id: " + item.getId());
		System.out.println("name: " + item.getName());
		System.out.println("age: " + item.getAge());
		System.out.println("city: " + item.getCity());
	}

	@Override
	public void findAgeOlder(int age) {
		if (age < 0) {
			System.out.println("-age需大於0-");
			return;
		}
		List<PersonInfo> op = personInfoDao.findByAgeGreaterThanEqualOrderByAgeDesc(age);
		if (op.isEmpty()) {
			System.out.println("-查無資料-");
			return;
		}
		System.out.println("-查詢結果如下:-");
		int cnt = 1;
		for (PersonInfo item: op) {
			System.out.println("--NO." + cnt);
			System.out.println("id: " + item.getId());
			System.out.println("name: " + item.getName());
			System.out.println("age: " + item.getAge());
			System.out.println("city: " + item.getCity());
			System.out.println("");
			cnt++;
		}
	}

	@Override
	public void findAgeYounger(int age) {
		if (age < 0) {
			System.out.println("-age需大於0-");
			return;
		}
		List<PersonInfo> op = personInfoDao.findByAgeLessThanEqualOrderByAge(age);
		if (op.isEmpty()) {
			System.out.println("-查無資料-");
			return;
		}
		System.out.println("-查詢結果如下:-");
		int cnt = 1;
		for (PersonInfo item: op) {
			System.out.println("--NO." + cnt);
			System.out.println("id: " + item.getId());
			System.out.println("name: " + item.getName());
			System.out.println("age: " + item.getAge());
			System.out.println("city: " + item.getCity());
			System.out.println("");
			cnt++;
		}
	}

	@Override
	public void findAgeYoungerOrOlder(int age1, int age2) {
		if (age1 < 0 || age2 < 0) {
			System.out.println("-age需大於0-");
			return;
		}
		if (age1 == age2) {
			System.out.println("-age1不可於age2-");
			return;
		}
		if (age1 > age2) {
			System.out.println("-age1需小於age2-");
			return;
		}
		List<PersonInfo> op = personInfoDao.findByAgeLessThanEqualOrAgeGreaterThanEqual(age1, age2);
		if (op.isEmpty()) {
			System.out.println("-查無資料-");
			return;
		}
		System.out.println("-查詢結果如下:-");
		int cnt = 1;
		for (PersonInfo item: op) {
			System.out.println("--NO." + cnt);
			System.out.println("id: " + item.getId());
			System.out.println("name: " + item.getName());
			System.out.println("age: " + item.getAge());
			System.out.println("city: " + item.getCity());
			System.out.println("");
			cnt++;
		}
	}

	@Override
	public void findByAgeBetween(int age1, int age2) {
		if (age1 < 0 || age2 < 0) {
			System.out.println("-age需大於0-");
			return;
		}
		if (age1 > age2) {
			System.out.println("-age1需小於age2-");
			return;
		}
		List<PersonInfo> op = personInfoDao.findFirst3ByAgeBetweenOrderByAgeDesc(age1, age2);
		if (op.isEmpty()) {
			System.out.println("-查無資料-");
			return;
		}
		System.out.println("-查詢結果如下:-");
		int cnt = 1;
		for (PersonInfo item: op) {
			System.out.println("--NO." + cnt);
			System.out.println("id: " + item.getId());
			System.out.println("name: " + item.getName());
			System.out.println("age: " + item.getAge());
			System.out.println("city: " + item.getCity());
			System.out.println("");
			cnt++;
		}
	}

	@Override
	public void findCity01(String city) {
		List<PersonInfo> op = personInfoDao.findByCityContaining(city);
		if (op.isEmpty()) {
			System.out.println("-查無資料-");
			return;
		}
		int cnt = 1;
		for (PersonInfo item: op) {
			System.out.println("--NO." + cnt);
			System.out.println("id: " + item.getId());
			System.out.println("name: " + item.getName());
			System.out.println("age: " + item.getAge());
			System.out.println("city: " + item.getCity());
			System.out.println("");
			cnt++;
		}
	}
	
	@Override
	public void findCity02(String city) {
		List<PersonInfo> op = personInfoDao.findByCityLike(city);
		if (op.isEmpty()) {
			System.out.println("-查無資料-");
			return;
		}
		int cnt = 1;
		for (PersonInfo item: op) {
			System.out.println("--NO." + cnt);
			System.out.println("id: " + item.getId());
			System.out.println("name: " + item.getName());
			System.out.println("age: " + item.getAge());
			System.out.println("city: " + item.getCity());
			System.out.println("");
			cnt++;
		}
	}

	@Override
	public void findAgeOlderByCity(int age, String city) {
		if (age < 0) {
			System.out.println("-age需大於0-");
			return;
		}
		List<PersonInfo> op = personInfoDao.findByAgeGreaterThanEqualAndCityContainingOrderByAgeDesc(age, city);
		
		if (op.isEmpty()) {
			System.out.println("-查無資料-");
			return;
		}
		int cnt = 1;
		for (PersonInfo item: op) {
			System.out.println("--NO." + cnt);
			System.out.println("id: " + item.getId());
			System.out.println("name: " + item.getName());
			System.out.println("age: " + item.getAge());
			System.out.println("city: " + item.getCity());
			System.out.println("");
			cnt++;
		}
	}

	@Override
	public void findByCity(List<String> cityList) {
		List<PersonInfo> op = personInfoDao.findByCityIn(cityList);
		if (op.isEmpty()) {
			System.out.println("-查無資料-");
			return;
		}
		int cnt = 1;
		for (PersonInfo item: op) {
			System.out.println("--NO." + cnt);
			System.out.println("id: " + item.getId());
			System.out.println("name: " + item.getName());
			System.out.println("age: " + item.getAge());
			System.out.println("city: " + item.getCity());
			System.out.println("");
			cnt++;
		}
	}

	@Override
	public UpdateInfoRes updateInfo(UpdateInfoReq req) {
		int res = personInfoDao.updateInfo(req.getId(), req.getName(), //
				req.getAge(), req.getCity());
		
		if (res != 0) {
			return new UpdateInfoRes(ResMessage.SUCCESS.getCode(), //
					ResMessage.SUCCESS.getMessage());
		}
		return new UpdateInfoRes(ResMessage.UPDATE_FAILED.getCode(), //
				ResMessage.UPDATE_FAILED.getMessage());
	}
	
	
}
