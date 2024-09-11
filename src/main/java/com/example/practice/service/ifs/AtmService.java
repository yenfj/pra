package com.example.practice.service.ifs;

import com.example.practice.vo.AddInfoReq;
import com.example.practice.vo.AddInfoRes;
import com.example.practice.vo.AtmRes;
import com.example.practice.vo.GetAccRes;
import com.example.practice.vo.GetInfoRes;

public interface AtmService {
	
	public AddInfoRes addInfo(AddInfoReq req);
	
	public GetAccRes getAcc();
	
	public GetInfoRes getInfoByAcc(String acc, String pwd);
	
	public AtmRes deposit(String acc, String pwd, int amount);
	
	public AtmRes withdrawals(String acc, String pwd, int amount);
	
	public AtmRes operateAtm(String acc, String pwd, int amount, boolean isDeposit);
}
