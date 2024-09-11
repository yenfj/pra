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
		// �ˬd�Ѽ�
		if (!(StringUtils.hasText(newMeal.getName())|| StringUtils.hasText(newMeal.getCookingStyle()))) {
			System.out.println("-�\�I�W�٩βi�N�W�ٿ��~-");
			return;
		}
		if (newMeal.getPrice() <= 0) {
			System.out.println("-�\�I������~-");
			return;
		}
		// �ˬd�O�_�s�b
		// �ȽT�{�O�_�s�b��DB���A��������B�z: exists
		// �]���h��PK�B�Qclass NewMealId�Һ޲z�A�GexistsById()��Id���a�Jclass NewMealId
		if (newMealDao.existsById(new NewMealId(newMeal.getName(), newMeal.getCookingStyle()))) {
			System.out.println("-�L�\�I�w�s�b-");
			return;
		}
		System.out.println("-�s�W�\�I���\-");
		System.out.printf("%s - %s: %d\n", newMeal.getCookingStyle(), newMeal.getName(), newMeal.getPrice());
		newMealDao.save(newMeal);
	}

	@Override
	public void findMeal(String name, String cookingStyle) {
		// �ˬd�Ѽ�
		if (!(StringUtils.hasText(name)|| StringUtils.hasText(cookingStyle))) {
			System.out.println("-�\�I�W�٩βi�N�W�ٿ��~-");
			return;
		}
		// ��k1
		Optional<NewMeal> op = newMealDao.findById(new NewMealId(name, cookingStyle));
		// �P�_Optional<NewMeal> op�O�_��null
		if (op.isEmpty()) {
			System.out.println("-�d�L���-");
			return;
		}
		NewMeal newMeal = op.get();
		System.out.printf("%s - %s: %d\n", newMeal.getCookingStyle(), newMeal.getName(), newMeal.getPrice());
		// ��k2
		newMeal = newMealDao.findByNameAndCookingStyle(name, cookingStyle);
		if (newMeal == null) {
			System.out.println("-�d�L���-");
			return;
		}
		System.out.printf("%s - %s: %d\n", newMeal.getCookingStyle(), newMeal.getName(), newMeal.getPrice());
	}

}
