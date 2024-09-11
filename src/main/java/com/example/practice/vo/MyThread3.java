package com.example.practice.vo;

public class MyThread3 implements Runnable {

	private int sum;

	@Override
	public void run() {
		System.out.println("°õ¦æºü°õ¦æ¤¤...");
		synchronized (this) {
			for (int i = 0; i <= 10; i++) {
				sum += 1;
			}
		}
		notify();
	}
	public int getSum() {
		return sum;
	}
}
