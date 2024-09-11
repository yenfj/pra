package com.example.practice;

import com.example.practice.vo.MyThread;
import com.example.practice.vo.MyThread3;
import com.example.practice.vo.SellTicket;

public class PracticeApplication2 {

	public static void main(String[] args) {
		// 執行緒
//		MyThread my1 = new MyThread("My1");
//		MyThread my2 = new MyThread("My2");
//		my1.start();
//		my2.start();
//		MyThread2 my = new MyThread("MT1");
//		Thread t = new Thread(my);
//		t.start();
//		----------------------------------
		// 同步
//		SellTicket st = new SellTicket();
//		Thread t1 = new Thread(st, "機器 A");
//		Thread t2 = new Thread(st, "機器 B");
//		t1.start();
//		t2.start();
//		----------------------------------
		// 一般類別
//		MyThread my11 = new MyThread() {
//			@Override
//			public void run() {
//				System.out.println("==============");
//			}
//		};
//		my11.start();
//		----------------------------------
		MyThread3 my3 = new MyThread3();
		Thread t3 = new Thread(my3);
		t3.start();
		synchronized (t3) {
			try {
				t3.wait();
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("sum = " + my3.getSum());
	}

}
