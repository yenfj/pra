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
	// �K�X�[�K�u��
	// �[�K: encoder.encode(��l�K�X);
	// �K�X���: encoder.matches(��l�K�X, �[�K�L�ᤧ�K�X);
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Autowired
	AtmDAO atmDao;

	@Override
	public AddInfoRes addInfo(AddInfoReq req) {
		// �Ѽ��ˬd
//		if (!StringUtils.hasText(req.getAcc())) {
//			return new AddInfoRes(ResMessage.ACC_ERROR.getCode(), ResMessage.ACC_ERROR.getMessage());
//		}
//		if (!StringUtils.hasText(req.getPwd())) {
//			return new AddInfoRes(ResMessage.PWD_ERROR.getCode(), ResMessage.PWD_ERROR.getMessage());
//		}
//		if (req.getBalance() < 0) {
//			return new AddInfoRes(ResMessage.BAL_ERROR.getCode(), ResMessage.BAL_ERROR.getMessage());
//		}
		// �ƾ��ˬd
		if (atmDao.existsById(req.getAcc())) {
			return new AddInfoRes(ResMessage.ACC_EXISTED.getCode(), ResMessage.ACC_EXISTED.getMessage());
		}
		// �K�X�[�K
		String enCodePwd = encoder.encode(req.getPwd());
		// �[�K�᪺�K�X�л\���N���쥻���K�X
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
//		// �Ѽ��ˬd
//		if (!StringUtils.hasText(acc)) {
//			return new GetInfoRes(ResMessage.ACC_ERROR.getCode(), ResMessage.ACC_ERROR.getMessage());
//		}
//		if (!StringUtils.hasText(pwd)) {
//			return new GetInfoRes(ResMessage.PWD_ERROR.getCode(), ResMessage.PWD_ERROR.getMessage());
//		}
		// �ƾ��ˬd: ��������acc�Mpwd��O�]��pwd���i��[�K
		if (!atmDao.existsById(acc)) {
			return new GetInfoRes(ResMessage.ACC_NOT_FOUND.getCode(), ResMessage.ACC_NOT_FOUND.getMessage());
		}
		Atm info = atmDao.findById(acc).get();
		// encoder.matches(��l�K�X, �[�K�L�ᤧ�K�X)
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
//		// �Ѽ��ˬd
//		if (!StringUtils.hasText(acc)) {
//			return new AtmRes(ResMessage.ACC_ERROR.getCode(), ResMessage.ACC_ERROR.getMessage());
//		}
//		if (!StringUtils.hasText(pwd)) {
//			return new AtmRes(ResMessage.PWD_ERROR.getCode(), ResMessage.PWD_ERROR.getMessage());
//		}
//		if (amount < 0) {
//			return new AtmRes(ResMessage.AMOUNT_ERROR.getCode(), ResMessage.AMOUNT_ERROR.getMessage());
//		}
		// �ƾ��ˬd
		if (!atmDao.existsById(acc)) {
			return new AtmRes(ResMessage.ACC_NOT_FOUND.getCode(), ResMessage.ACC_NOT_FOUND.getMessage());
		}
		Atm info = atmDao.findById(acc).get();
		if (!encoder.matches(pwd, info.getPwd())) {
			return new AtmRes(ResMessage.PWD_ERROR.getCode(), ResMessage.PWD_ERROR.getMessage());
		}
		if (deposit) { // �s��
			info.setBalance(info.getBalance() + amount);
		}else { // ����
			// �s�ڪ��B����
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
