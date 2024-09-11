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
		// �]���O�s�W�@����ơA�ҥH���\���ܡAinsert��k�|�^�Ǧ��\����Ƶ��ƼƦr�Ares�w�]��1
		// Assert.isTrue(�P�_���A�T��);
		// �P�_��: �]���O�ϥ�isTrue�A�ҥH�P�_�������G������true
		// �T��: ��e���P�_�����G��false�A���^�Ǥ��T��(���~�T��)
		Assert.isTrue(res == 1, "�s�W��ƥ���");
	}
	
	@Test
	public void testIsTrue() {
		int a = 10;
		int b = 5;
		Assert.isTrue(a < b, "a is greater than b");
	}
	
	@Test
	public void updateTest() {
		int res = dao.updateCityById("����", "A88");
		Assert.isTrue(res == 1, "��s��ƥ���");
		System.out.println(res);
	}
	
	@Test
	public void selectTest() {
		List<PersonInfo> res = dao.selectAll();
		System.out.println(res.size());
		Assert.isTrue(res.size() > 0, "�d�߸�ƥ���");
	}
	
	@Test
	public void selectTest2() {
		List<PersonInfo> res = dao.selectAll3();
		System.out.println(res.size());
		Assert.isTrue(res.size() > 0, "�d�߸�ƥ���");
	}
	
	@Test
	public void selectTest3() {
		List<PersonInfo> res = dao.selectAll21();
		System.out.println(res.size());
		Assert.isTrue(res.size() > 0, "�d�߸�ƥ���");
	}
	
	@Test
	public void selectTest31() {
		List<PersonInfo> res = dao.selectAll31(30);
		System.out.println(res.size());
		Assert.isTrue(res.size() > 0, "�d�߸�ƥ���");
	}

	@Test
	public void selectTest32() {
		List<PersonInfo> res = dao.selectAll32(999);
		System.out.println(res.size());
		Assert.isTrue(res.size() > 0, "�d�߸�ƥ���");
	}

	@Test
	public void selectTest33() {
		List<PersonInfo> res = dao.selectAll33("����");
		System.out.println(res.size());
		Assert.isTrue(res.size() > 0, "�d�߸�ƥ���");
	}
	
	@Test
	public void joinTable() {
		List<JoinVo> res = dao.joinTable();
		System.out.println(res.size());
		Assert.isTrue(res.size() > 0, "�d�߸�ƥ���");
	}
	
	@Test
	public void joinTableWithLimit() {
		List<JoinVo> res = dao.joinTableWithLimit(PageRequest.of(0, 2));
		System.out.println(res.size());
		for (JoinVo item: res) {
			item.getId();
		}
		Assert.isTrue(res.size() > 0, "�d�߸�ƥ���");
	}
}
