package com.example.practice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;

import com.example.practice.constant.ResMessage;
import com.example.practice.entity.Atm;
import com.example.practice.repository.AtmDAO;
import com.example.practice.service.ifs.AtmService;
import com.example.practice.vo.AddInfoReq;
import com.example.practice.vo.AddInfoRes;
import com.example.practice.vo.AtmRes;
import com.example.practice.vo.GetAccRes;
import com.example.practice.vo.GetInfoRes;

@Service
public class AtmServiceImpl implements AtmService {
	// 密碼加密工具
	// 加密: encoder.encode(原始密碼);
	// 密碼比對: encoder.matches(原始密碼, 加密過後之密碼);
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Autowired
	AtmDAO atmDao;

	@Override
	public AddInfoRes addInfo(AddInfoReq req) {
		// 參數檢查
//		if (!StringUtils.hasText(req.getAcc())) {
//			return new AddInfoRes(ResMessage.ACC_ERROR.getCode(), ResMessage.ACC_ERROR.getMessage());
//		}
//		if (!StringUtils.hasText(req.getPwd())) {
//			return new AddInfoRes(ResMessage.PWD_ERROR.getCode(), ResMessage.PWD_ERROR.getMessage());
//		}
//		if (req.getBalance() < 0) {
//			return new AddInfoRes(ResMessage.BAL_ERROR.getCode(), ResMessage.BAL_ERROR.getMessage());
//		}
		// 數據檢查
		if (atmDao.existsById(req.getAcc())) {
			return new AddInfoRes(ResMessage.ACC_EXISTED.getCode(), ResMessage.ACC_EXISTED.getMessage());
		}
		// 密碼加密
		String enCodePwd = encoder.encode(req.getPwd());
		// 加密後的密碼覆蓋替代成原本的密碼
		req.setPwd(enCodePwd);

		atmDao.save(new Atm(req.getAcc(), req.getPwd(), req.getBalance()));
		return new AddInfoRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
	}

	@Override
	public GetAccRes getAcc() {
		List<Atm> atmList = atmDao.findAll();
		List<String> accList = new ArrayList<>();
		atmList.forEach(item -> {
			accList.add(item.getAcc());
		});
		return new GetAccRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(), accList);
	}

	@Override
	public GetInfoRes getInfoByAcc(String acc, String pwd) {
//		// 參數檢查
//		if (!StringUtils.hasText(acc)) {
//			return new GetInfoRes(ResMessage.ACC_ERROR.getCode(), ResMessage.ACC_ERROR.getMessage());
//		}
//		if (!StringUtils.hasText(pwd)) {
//			return new GetInfoRes(ResMessage.PWD_ERROR.getCode(), ResMessage.PWD_ERROR.getMessage());
//		}
		// 數據檢查: 不直接用acc和pwd找是因為pwd有進行加密
		if (!atmDao.existsById(acc)) {
			return new GetInfoRes(ResMessage.ACC_NOT_FOUND.getCode(), ResMessage.ACC_NOT_FOUND.getMessage());
		}
		Atm info = atmDao.findById(acc).get();
		// encoder.matches(原始密碼, 加密過後之密碼)
		if (!encoder.matches(pwd, info.getPwd())) {
			return new GetInfoRes(ResMessage.PWD_ERROR.getCode(), ResMessage.PWD_ERROR.getMessage());
		}
		return new GetInfoRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(), //
				info.getAcc(), info.getBalance());
	}

	@Override
	public AtmRes deposit(String acc, String pwd, int amount) {
		return operateAtm(acc, pwd, amount, true);
	}

	@Override
	public AtmRes withdrawals(String acc, String pwd, int amount) {
		return operateAtm(acc, pwd, amount, false);
	}

	@Override
	public AtmRes operateAtm(String acc, String pwd, int amount, boolean deposit) {
//		// 參數檢查
//		if (!StringUtils.hasText(acc)) {
//			return new AtmRes(ResMessage.ACC_ERROR.getCode(), ResMessage.ACC_ERROR.getMessage());
//		}
//		if (!StringUtils.hasText(pwd)) {
//			return new AtmRes(ResMessage.PWD_ERROR.getCode(), ResMessage.PWD_ERROR.getMessage());
//		}
//		if (amount < 0) {
//			return new AtmRes(ResMessage.AMOUNT_ERROR.getCode(), ResMessage.AMOUNT_ERROR.getMessage());
//		}
		// 數據檢查
		if (!atmDao.existsById(acc)) {
			return new AtmRes(ResMessage.ACC_NOT_FOUND.getCode(), ResMessage.ACC_NOT_FOUND.getMessage());
		}
		Atm info = atmDao.findById(acc).get();
		if (!encoder.matches(pwd, info.getPwd())) {
			return new AtmRes(ResMessage.PWD_ERROR.getCode(), ResMessage.PWD_ERROR.getMessage());
		}
		if (deposit) { // 存款
			info.setBalance(info.getBalance() + amount);
		}else { // 提款
			// 存款金額不足
			if (amount > info.getBalance()) {
				return new AtmRes(ResMessage.INSUFFICIENT_BAL.getCode(), //
						ResMessage.INSUFFICIENT_BAL.getMessage());
			}
			info.setBalance(info.getBalance() - amount);
		}
		atmDao.save(info);
		return new AtmRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(), //
				info.getAcc(), info.getBalance());
	}
}
