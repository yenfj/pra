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
//			System.out.printf("%s ���b�����%2d��\n", getName(), i);
			System.out.println(getName() + "���b����� " + i + "��");
			try {
				sleep(500);
			}catch (InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
