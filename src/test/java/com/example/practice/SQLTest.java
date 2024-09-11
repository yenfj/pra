package com.example.practice;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.Assert;

import com.example.practice.entity.PersonInfo;
import com.example.practice.repository.PersonInfoDAO;
import com.example.practice.vo.JoinVo;

@SpringBootTest
public class SQLTest {
	@Autowired
	private PersonInfoDAO dao;
	
	@Test
	public void insertTest() {
		int res = dao.insert("A88", "A88", 888, "KKH");
		// 因為是新增一筆資料，所以成功的話，insert方法會回傳成功的資料筆數數字，res預設為1
		// Assert.isTrue(判斷式，訊息);
		// 判斷式: 因為是使用isTrue，所以判斷式的結果必須為true
		// 訊息: 當前面判斷式結果為false，須回傳之訊息(錯誤訊息)
		Assert.isTrue(res == 1, "新增資料失敗");
	}
	
	@Test
	public void testIsTrue() {
		int a = 10;
		int b = 5;
		Assert.isTrue(a < b, "a is greater than b");
	}
	
	@Test
	public void updateTest() {
		int res = dao.updateCityById("高雄", "A88");
		Assert.isTrue(res == 1, "更新資料失敗");
		System.out.println(res);
	}
	
	@Test
	public void selectTest() {
		List<PersonInfo> res = dao.selectAll();
		System.out.println(res.size());
		Assert.isTrue(res.size() > 0, "查詢資料失敗");
	}
	
	@Test
	public void selectTest2() {
		List<PersonInfo> res = dao.selectAll3();
		System.out.println(res.size());
		Assert.isTrue(res.size() > 0, "查詢資料失敗");
	}
	
	@Test
	public void selectTest3() {
		List<PersonInfo> res = dao.selectAll21();
		System.out.println(res.size());
		Assert.isTrue(res.size() > 0, "查詢資料失敗");
	}
	
	@Test
	public void selectTest31() {
		List<PersonInfo> res = dao.selectAll31(30);
		System.out.println(res.size());
		Assert.isTrue(res.size() > 0, "查詢資料失敗");
	}

	@Test
	public void selectTest32() {
		List<PersonInfo> res = dao.selectAll32(999);
		System.out.println(res.size());
		Assert.isTrue(res.size() > 0, "查詢資料失敗");
	}

	@Test
	public void selectTest33() {
		List<PersonInfo> res = dao.selectAll33("高雄");
		System.out.println(res.size());
		Assert.isTrue(res.size() > 0, "查詢資料失敗");
	}
	
	@Test
	public void joinTable() {
		List<JoinVo> res = dao.joinTable();
		System.out.println(res.size());
		Assert.isTrue(res.size() > 0, "查詢資料失敗");
	}
	
	@Test
	public void joinTableWithLimit() {
		List<JoinVo> res = dao.joinTableWithLimit(PageRequest.of(0, 2));
		System.out.println(res.size());
		for (JoinVo item: res) {
			item.getId();
		}
		Assert.isTrue(res.size() > 0, "查詢資料失敗");
	}
}
