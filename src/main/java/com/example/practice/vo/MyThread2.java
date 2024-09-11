package com.example.practice.vo;

public class MyThread2 implements Runnable {

	private String name;

	public MyThread2() {
		super();
	}

	public MyThread2(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			System.out.printf("%s 正在執行第%2d次\n", getName(), i);
			try {
				Thread.sleep(500);
			}catch (InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
