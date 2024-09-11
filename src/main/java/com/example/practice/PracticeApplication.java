package com.example.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import com.example.practice.vo.MyThread;
import com.example.practice.vo.MyThread2;

// 因用spring-boot-starter-security此依賴，故要排除預設的基本安全性設定(帳密登入驗證)
// 排除帳密登入驗證就是加上 exclude = SecurityAutoConfiguration.class
// 若等號後面有多個class，需用{}包住；若只有一個class則不必
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class PracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeApplication.class, args);
	}

}
