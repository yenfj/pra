package com.example.practice;

import org.junit.jupiter.api.Test;

import com.example.practice.vo.MyThread;
import com.example.practice.vo.MyThread2;

public class ThreadTest {
	@Test
	public void Test01() {
		MyThread myThread = new MyThread();
//		myThread.run();
//		System.out.println("--------------------------");
		myThread.start();
		System.out.println("**************************");
		System.out.println("**************************");
		System.out.println("**************************");
		System.out.println("**************************");
		System.out.println("**************************");
		System.out.println("**************************");
		System.out.println("**************************");
		System.out.println("**************************");
		System.out.println("**************************");
		System.out.println("**************************");
		System.out.println("**************************");
	}
	
	@Test
	public void Test02() {
		// �h������L�k�bTest������A�i��PracticeApplication.java����main����
		MyThread my01 = new MyThread("My01");
		MyThread my02 = new MyThread("My02");
		
		my01.start();
		my02.start();
	}
	
	@Test
	public void Test03() {
		// �h������L�k�bTest������A�i��PracticeApplication.java����main����
		MyThread2 my01 = new MyThread2("My01");
		Thread my = new Thread(my01);
		my.start();		
	}
}
