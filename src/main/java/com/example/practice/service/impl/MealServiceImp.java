package com.example.practice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.practice.entity.Meal;
import com.example.practice.repository.MealDAO;
import com.example.practice.service.ifs.MealService;

@Service // �N�����O���Spring Boot�U�ަ�service��
public class MealServiceImp implements MealService {
	@Autowired
	private MealDAO mealDao;
	
	private boolean checkPattern(Meal meal) {
		// StringUtils.hasText()�P�_�O�_���r��(�Ŧr��""�B�ťզr��" "�Bnull = false)
		if (!StringUtils.hasText(meal.getName())) {
			System.out.println("-�\�I�W�ٿ��~-");
			return true;
		}
		if (meal.getPrice() <= 0) {
			System.out.println("-�\�I������~-");
			return true;
		}
		return false;
	}

	@Override
	public void addMeal(Meal meal) {
		/*
		 * 1. �ˬd�ݩ�(�W�٬O�_���šB����O�_���t)
		 * 2. �ˬd���s�W���O�_�w�s�b
		 */
		if (checkPattern(meal)) {
			return;
		}
		
		// �ˬd�\�I�W�٬O�_�w�s�bDB��
		// existsById(): Id��entity��PK(@Id)�A�ˬdDB���O�_�s�b��PK(�s�b = true)
		if (mealDao.existsById(meal.getName())) {
			System.out.println("-�\�I�W�٤w�s�b-");
			return;
		}
		
		// �N��Ʀs�JDB: DAO.save(entity);
		// save(): PK�Y�w�s�b��DB>>��s���(PK�L�k�Q��s)
		// 		   PK�Y���s�b��DB>>�s�W���
		mealDao.save(meal);
		System.out.println("-�s�W���-");
		System.out.printf("%s: %d\n", meal.getName(), meal.getPrice());
	}
	
	@Override
	public void addMeals(List<Meal> meal) {
		List<String> ids = new ArrayList<>();
		for (Meal item: meal) {
			if (checkPattern(item)) {
				System.out.printf("-'%s'�s�W����-\n", item.getName());
				continue;
			}
			if (ids.contains(item.getName())) {
				System.out.println("-�\�I�W�٤w�s�b-");
				System.out.printf("-'%s'�s�W����-\n", item.getName());
				continue;
			}
			ids.add(item.getName());
		}
		// ��k1
		List<Meal> checkedIds = mealDao.findAllById(ids);
		if (!checkedIds.isEmpty()) {
			for (Meal item: checkedIds) {
				System.out.println("-�\�I�W�٤w�s�b-");
				System.out.printf("-'%s'�s�W����-\n", item.getName());
			}
			return;
		}
		// ��k2
		if (mealDao.existsAllByNameIn(ids)) {
			System.out.println("-�\�I�W�٤w�s�b-");
			return;
		}
		System.out.printf("-�s�W��Ʀ��\: �@%1d��-", meal.size());
		mealDao.saveAll(meal);
	}

	// �ק�W��: �R���ª��B�s�W�s�W�٥B�������(PK�L�k��s)
	// updateMeal(): �u������
	@Override
	public void updatePrice(Meal meal) {
		if (checkPattern(meal)) {
			return;
		}
		if (!mealDao.existsById(meal.getName())) {
			System.out.println("-�\�I�W�٤��s�b-");
			return;
		}
		// save(): PK�Y�w�s�b��DB>>��s���(PK�L�k�Q��s)
		// 		   PK�Y���s�b��DB>>�s�W���
		mealDao.save(meal);
		System.out.println("-�ק���-");
		System.out.printf("%s: %d\n", meal.getName(), meal.getPrice());
	}

	@Override
	public void findByName(String name) {
		if (!StringUtils.hasText(name)) {
			System.out.println("-�\�I�W�ٿ��~-");
			return;
		}
//		if (!mealDao.existsById(name)) {
//			System.out.println("-�d�ߵ��G-");
//			System.out.println("-�\�I�W�٤��s�b-");
//			return;
//		}
		
		// ��k01
		Optional<Meal> op = mealDao.findById(name);
		// isEmpty(): �P�_�QOptional�]�_�Ӫ�<Meal>�O�_��null(null = true)
		if (op.isEmpty()) {
			System.out.println("-�d�ߵ��G-");
			System.out.println("-�\�I�W�٤��s�b-");
		}
		// �NMeal�qOptional�����X
		Meal meal = op.get();
		System.out.println("-�d�ߵ��G-");
		System.out.printf("%s: %d\n", meal.getName(), meal.getPrice());
//		System.out.println(op); // Optional[com.example.practice.entity.Meal@24f3fb87]
		
		// ��k02: �ϥ��ݩʦW�٨Ӧ۩w�qDAO��k
		meal = mealDao.findByName(name);
		// �䤣���ơBmeal = null: �Y���P�_meal == null�AgetName()�|����
		if (meal == null) {
			System.out.println("-�d�ߵ��G-");
			System.out.println("-�\�I�W�٤��s�b-");
		}
		System.out.println("-�d�ߵ��G-");
		System.out.printf("%s: %d\n", meal.getName(), meal.getPrice());
	}

	@Override
	public void allMeal() {
		List<Meal> dbList = mealDao.findAll();
		dbList.forEach(item ->{
			System.out.printf("%s: %d\n", item.getName(), item.getPrice());
		});
	}
}