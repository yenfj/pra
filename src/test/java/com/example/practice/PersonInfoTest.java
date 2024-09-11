package com.example.practice;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.practice.entity.PersonInfo;
import com.example.practice.service.ifs.PersonInfoService;

@SpringBootTest
public class PersonInfoTest {
	
	@Autowired
	private PersonInfoService personInfoService;
	
	@Test
	public void addInfoTest() {
		personInfoService.addInfo(new PersonInfo("A123456789", "AAA", 20, ""));
	}
	
	@Test
	public void getAllInfoTest() {
		personInfoService.getAllInfo();
	}
	
	@Test
	public void findByIdTest() {
		personInfoService.findById("D154852927");
	}
	
	@Test
	public void findAgeOlderTest() {
		personInfoService.findAgeOlder(21);
		personInfoService.findAgeOlder(27);
		personInfoService.findAgeOlder(30);
	}
	
	@Test
	public void findAgeYoungerTest() {
		personInfoService.findAgeYounger(20);
		personInfoService.findAgeYounger(30);
		personInfoService.findAgeYounger(40);
	}
	
	@Test
	public void findAgeYoungerOrOlderTest() {
		personInfoService.findAgeYoungerOrOlder(10, 30);
	}
	
	@Test
	public void findByAgeBetweenTest() {
		personInfoService.findByAgeBetween(20, 40);
	}
	
	@Test
	public void findCityTest() {
		personInfoService.findCity01("");
		personInfoService.findCity01("台");
		personInfoService.findCity01("屏東");
		
		personInfoService.findCity02("");
		personInfoService.findCity02("台");
		personInfoService.findCity02("屏東");
	}
	
	@Test
	public void findAgeOlderByCityTest() {
		personInfoService.findAgeOlderByCity(20, "高");
	}
	
	@Test
	public void findByCityTest() {
		List<String> cityList = List.of("屏東", "高雄");
		personInfoService.findByCity(cityList);
	}
}
