package com.example.practice.vo;

public class SellTicket implements Runnable {
	
	private int tickets = 100;

	@Override
	public void run() {
		for (int i = 0; i <= 100; i++) {
			// (this)��synchronized(this) {}��{}�����e
			synchronized(this) {
				if (tickets > 0) {
				try {
					Thread.sleep(500);
				}catch (InterruptedException e) {
					e.printStackTrace();
				}
					tickets--;
					System.out.println(Thread.currentThread().getName() + " ����X�@�i�A�ѤU " + tickets + " �i");
				}
			}
		}
	}
}
