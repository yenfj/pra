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

// @RestController: 包含了@Controller和@ResponseBody
// @Controller: 是指將此類別交由Spring Boot託管成Controller物件
// @ResponseBody: 可以將自定義的物件(response)轉換成JSON格式傳輸給外部
// 加了@RestController後，就不需要在addInfoRes(或其他的xxxRes)前面加上@ResponseBody
@RestController
public class AtmController {
	@Autowired
	private AtmService atmService;
	
	/*
	 * 1. @PostMapping: 表示請求方法的http method是POST
	 * 2. value = "atm/add_info": 表示該請求的路徑(URL)，自定義
	 * 3. @RequestBody: 把外部請求中的JSON物件("key": "value")對應到自定義的AddInfoReq中的屬性名稱(JSON "key" == Req之屬性名稱)，並把值賦予到對應的變數裡
	 */
	@PostMapping(value = "atm/add_info")
	// 下一行的另一種寫法: @RequestMapping(value = "atm/add_info", method = RequestMethod.POST)
	public AddInfoRes addInfo(@Valid @RequestBody AddInfoReq req) {
		return  atmService.addInfo(req);
	}
	// @GetMapping: 表示請求方法的http method是GET
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
