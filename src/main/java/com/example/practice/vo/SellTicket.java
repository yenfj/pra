package com.example.practice.vo;

public class SellTicket implements Runnable {
	
	private int tickets = 100;

	@Override
	public void run() {
		for (int i = 0; i <= 100; i++) {
			// (this)指synchronized(this) {}中{}之內容
			synchronized(this) {
				if (tickets > 0) {
				try {
					Thread.sleep(500);
				}catch (InterruptedException e) {
					e.printStackTrace();
				}
					tickets--;
					System.out.println(Thread.currentThread().getName() + " 票賣出一張，剩下 " + tickets + " 張");
				}
			}
		}
	}
}
