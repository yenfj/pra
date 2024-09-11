package com.example.practice.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.practice.entity.NewMeal;
import com.example.practice.entity.NewMealId;
import com.example.practice.repository.NewMealDAO;
import com.example.practice.service.ifs.NewMealService;

@Service
public class NewMealServiceImp implements NewMealService {
	@Autowired
	private NewMealDAO newMealDao;

	@Override
	public void add(NewMeal newMeal) {
		// 檢查參數
		if (!(StringUtils.hasText(newMeal.getName())|| StringUtils.hasText(newMeal.getCookingStyle()))) {
			System.out.println("-餐點名稱或烹煮名稱錯誤-");
			return;
		}
		if (newMeal.getPrice() <= 0) {
			System.out.println("-餐點價格錯誤-");
			return;
		}
		// 檢查是否存在
		// 僅確認是否存在於DB中，不做任何處理: exists
		// 因有多個PK且被class NewMealId所管理，故existsById()的Id須帶入class NewMealId
		if (newMealDao.existsById(new NewMealId(newMeal.getName(), newMeal.getCookingStyle()))) {
			System.out.println("-胎餐點已存在-");
			return;
		}
		System.out.println("-新增餐點成功-");
		System.out.printf("%s - %s: %d\n", newMeal.getCookingStyle(), newMeal.getName(), newMeal.getPrice());
		newMealDao.save(newMeal);
	}

	@Override
	public void findMeal(String name, String cookingStyle) {
		// 檢查參數
		if (!(StringUtils.hasText(name)|| StringUtils.hasText(cookingStyle))) {
			System.out.println("-餐點名稱或烹煮名稱錯誤-");
			return;
		}
		// 方法1
		Optional<NewMeal> op = newMealDao.findById(new NewMealId(name, cookingStyle));
		// 判斷Optional<NewMeal> op是否為null
		if (op.isEmpty()) {
			System.out.println("-查無資料-");
			return;
		}
		NewMeal newMeal = op.get();
		System.out.printf("%s - %s: %d\n", newMeal.getCookingStyle(), newMeal.getName(), newMeal.getPrice());
		// 方法2
		newMeal = newMealDao.findByNameAndCookingStyle(name, cookingStyle);
		if (newMeal == null) {
			System.out.println("-查無資料-");
			return;
		}
		System.out.printf("%s - %s: %d\n", newMeal.getCookingStyle(), newMeal.getName(), newMeal.getPrice());
	}

}
