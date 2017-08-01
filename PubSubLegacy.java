package com.example;

import java.util.Random;

public class PubSubLegacy {
	public static void main(String[] args) {
		
		DataQ dataQ = new DataQ();
		
		new Producer(dataQ).start();
		new Consumer(dataQ, "CON1").start();
		new Consumer(dataQ, "CON2").start();
		System.out.println("DONE");
	}
	
	public static class DataQ {
		int nextInt;
		boolean available;
		
		public synchronized void put(int nextInt) {
			// wait till consumer consumes
			while(available) {
				try {
					System.out.println("[" + Thread.currentThread().getName() + "]Waiting till consumer consumes");
					wait();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			this.nextInt = nextInt;
			System.out.println("[" + Thread.currentThread().getName() + "]Producer produced: " + this.nextInt);
			available = true;
			notifyAll();
		}

		public synchronized void get() {
			// wait till producer produces
			while(!available) {
				try {
					System.out.println("[" + Thread.currentThread().getName() + "]Waiting till producer produces");
					wait();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			System.out.println("[" + Thread.currentThread().getName() + "]Consumer consumed: " + this.nextInt);
			available = false;
			notifyAll();
		}
		
	}
	
	public static class Producer extends Thread {
		private DataQ dataQ;
		private Random random = new Random();
		
		public Producer(DataQ dataQ) {
			this.dataQ = dataQ;
		}
		
		@Override
		public void run() {
			while(true) {
				dataQ.put(random.nextInt());
				/*try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}*/
			}
		}
	}
	
	public static class Consumer extends Thread {
		private DataQ dataQ;
		
		public Consumer(DataQ dataQ, String conName) {
			super(conName);
			this.dataQ = dataQ;
		}
		
		@Override
		public void run() {
			while(true) {
				dataQ.get();
				/*try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}*/
			}
		}
	}
}
