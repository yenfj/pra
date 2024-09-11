package com.example.practice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.practice.service.ifs.AtmService;
import com.example.practice.vo.AddInfoReq;
import com.example.practice.vo.AddInfoRes;
import com.example.practice.vo.AtmReq;
import com.example.practice.vo.AtmReq2;
import com.example.practice.vo.AtmRes;
import com.example.practice.vo.GetAccRes;
import com.example.practice.vo.GetInfoReq;
import com.example.practice.vo.GetInfoRes;

// @RestController: �]�t�F@Controller�M@ResponseBody
// @Controller: �O���N�����O���Spring Boot�U�ަ�Controller����
// @ResponseBody: �i�H�N�۩w�q������(response)�ഫ��JSON�榡�ǿ鵹�~��
// �[�F@RestController��A�N���ݭn�baddInfoRes(�Ψ�L��xxxRes)�e���[�W@ResponseBody
@RestController
public class AtmController {
	@Autowired
	private AtmService atmService;
	
	/*
	 * 1. @PostMapping: ��ܽШD��k��http method�OPOST
	 * 2. value = "atm/add_info": ��ܸӽШD�����|(URL)�A�۩w�q
	 * 3. @RequestBody: ��~���ШD����JSON����("key": "value")������۩w�q��AddInfoReq�����ݩʦW��(JSON "key" == Req���ݩʦW��)�A�ç�Ƚᤩ��������ܼƸ�
	 */
	@PostMapping(value = "atm/add_info")
	// �U�@�檺�t�@�ؼg�k: @RequestMapping(value = "atm/add_info", method = RequestMethod.POST)
	public AddInfoRes addInfo(@Valid @RequestBody AddInfoReq req) {
		return  atmService.addInfo(req);
	}
	// @GetMapping: ��ܽШD��k��http method�OGET
	@GetMapping(value = "atm/get_acc")
	public GetAccRes getAcc() {
		return atmService.getAcc();
	}
	
	@PostMapping(value = "atm/get_info")
	public GetInfoRes getInfoByAcc(@Valid @RequestBody GetInfoReq req) {
		return atmService.getInfoByAcc(req.getAcc(), req.getPwd());
	}
	
	@PostMapping(value = "atm/deposit")
	public AtmRes deposit(@Valid @RequestBody AtmReq req) {
		return atmService.deposit(req.getAcc(), req.getPwd(), req.getAmount());
	}
	
	@PostMapping(value = "atm/withdrawals")
	public AtmRes withdrawals(@Valid @RequestBody AtmReq req) {
		return atmService.withdrawals(req.getAcc(), req.getPwd(), req.getAmount());
	}
	
	@PostMapping(value = "atm/operate_atm")
	public AtmRes operateAtm(@Valid @RequestBody AtmReq2 req) {
		return atmService.operateAtm(req.getAcc(), req.getPwd(), req.getAmount(), req.getDeposit());
	}
}
