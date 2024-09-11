package com.example.practice.vo;

public class MyThread extends Thread {
	
	public MyThread() {
		super();
	}

	public MyThread(String name) {
		super(name);
	}

	@Override
	public  void run() {
		for (int i = 1; i <= 10; i++) {
//			System.out.printf("%s 正在執行第%2d次\n", getName(), i);
			System.out.println(getName() + "正在執行第 " + i + "次");
			try {
				sleep(500);
			}catch (InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
