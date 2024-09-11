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

@Service // 將此類別交由Spring Boot託管成service類
public class MealServiceImp implements MealService {
	@Autowired
	private MealDAO mealDao;
	
	private boolean checkPattern(Meal meal) {
		// StringUtils.hasText()判斷是否有字串(空字串""、空白字串" "、null = false)
		if (!StringUtils.hasText(meal.getName())) {
			System.out.println("-餐點名稱錯誤-");
			return true;
		}
		if (meal.getPrice() <= 0) {
			System.out.println("-餐點價格錯誤-");
			return true;
		}
		return false;
	}

	@Override
	public void addMeal(Meal meal) {
		/*
		 * 1. 檢查屬性(名稱是否為空、價格是否為負)
		 * 2. 檢查欲新增菜單是否已存在
		 */
		if (checkPattern(meal)) {
			return;
		}
		
		// 檢查餐點名稱是否已存在DB中
		// existsById(): Id為entity的PK(@Id)，檢查DB中是否存在該PK(存在 = true)
		if (mealDao.existsById(meal.getName())) {
			System.out.println("-餐點名稱已存在-");
			return;
		}
		
		// 將資料存入DB: DAO.save(entity);
		// save(): PK若已存在於DB>>更新資料(PK無法被更新)
		// 		   PK若不存在於DB>>新增資料
		mealDao.save(meal);
		System.out.println("-新增資料-");
		System.out.printf("%s: %d\n", meal.getName(), meal.getPrice());
	}
	
	@Override
	public void addMeals(List<Meal> meal) {
		List<String> ids = new ArrayList<>();
		for (Meal item: meal) {
			if (checkPattern(item)) {
				System.out.printf("-'%s'新增失敗-\n", item.getName());
				continue;
			}
			if (ids.contains(item.getName())) {
				System.out.println("-餐點名稱已存在-");
				System.out.printf("-'%s'新增失敗-\n", item.getName());
				continue;
			}
			ids.add(item.getName());
		}
		// 方法1
		List<Meal> checkedIds = mealDao.findAllById(ids);
		if (!checkedIds.isEmpty()) {
			for (Meal item: checkedIds) {
				System.out.println("-餐點名稱已存在-");
				System.out.printf("-'%s'新增失敗-\n", item.getName());
			}
			return;
		}
		// 方法2
		if (mealDao.existsAllByNameIn(ids)) {
			System.out.println("-餐點名稱已存在-");
			return;
		}
		System.out.printf("-新增資料成功: 共%1d筆-", meal.size());
		mealDao.saveAll(meal);
	}

	// 修改名稱: 刪除舊的、新增新名稱且價格照舊(PK無法更新)
	// updateMeal(): 只能改價格
	@Override
	public void updatePrice(Meal meal) {
		if (checkPattern(meal)) {
			return;
		}
		if (!mealDao.existsById(meal.getName())) {
			System.out.println("-餐點名稱不存在-");
			return;
		}
		// save(): PK若已存在於DB>>更新資料(PK無法被更新)
		// 		   PK若不存在於DB>>新增資料
		mealDao.save(meal);
		System.out.println("-修改資料-");
		System.out.printf("%s: %d\n", meal.getName(), meal.getPrice());
	}

	@Override
	public void findByName(String name) {
		if (!StringUtils.hasText(name)) {
			System.out.println("-餐點名稱錯誤-");
			return;
		}
//		if (!mealDao.existsById(name)) {
//			System.out.println("-查詢結果-");
//			System.out.println("-餐點名稱不存在-");
//			return;
//		}
		
		// 方法01
		Optional<Meal> op = mealDao.findById(name);
		// isEmpty(): 判斷被Optional包起來的<Meal>是否為null(null = true)
		if (op.isEmpty()) {
			System.out.println("-查詢結果-");
			System.out.println("-餐點名稱不存在-");
		}
		// 將Meal從Optional中取出
		Meal meal = op.get();
		System.out.println("-查詢結果-");
		System.out.printf("%s: %d\n", meal.getName(), meal.getPrice());
//		System.out.println(op); // Optional[com.example.practice.entity.Meal@24f3fb87]
		
		// 方法02: 使用屬性名稱來自定義DAO方法
		meal = mealDao.findByName(name);
		// 找不到資料、meal = null: 若不判斷meal == null，getName()會報錯
		if (meal == null) {
			System.out.println("-查詢結果-");
			System.out.println("-餐點名稱不存在-");
		}
		System.out.println("-查詢結果-");
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