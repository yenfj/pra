package com.example.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import com.example.practice.vo.MyThread;
import com.example.practice.vo.MyThread2;

// �]��spring-boot-starter-security���̿�A�G�n�ư��w�]���򥻦w���ʳ]�w(�b�K�n�J����)
// �ư��b�K�n�J���ҴN�O�[�W exclude = SecurityAutoConfiguration.class
// �Y�����᭱���h��class�A�ݥ�{}�]��F�Y�u���@��class�h����
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class PracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeApplication.class, args);
	}

}
