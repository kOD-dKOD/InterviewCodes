package com.example;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PubSubLock {
	public static void main(String[] args) {
		Buffer buffer = new Buffer();
		
		new Producer(buffer, "Prod-0").start();
		
		new Consumer(buffer, "Cons-0").start();
		
	}
	
	private static class Buffer {
		private Lock lock = new ReentrantLock(true);
		private Condition bufferFull = lock.newCondition();
		private Condition bufferEmpty = lock.newCondition();
		
		private int count, index;
		private int[] buffer = new int[10];
		
		public void put(int item) {
			lock.lock();
			
			try {
				while(count == buffer.length) {
					System.out.println("[" + Thread.currentThread().getName() + "] Buffer FULL !! Producer waiting ...");
					bufferFull.await();
				}
				
				System.out.println("[" + Thread.currentThread().getName() + "] Producer Index: " + index);
				buffer[index++] = item;
				count++;
				System.out.println("[" + Thread.currentThread().getName() + "] Buffer Items: " + count + ", " + buffer.length);
				bufferEmpty.signal();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
		
		public int get() {
			lock.lock();
			try {
				while(count == 0) {
					System.out.println("[" + Thread.currentThread().getName() + "] Buffer Empty !! Consumer waiting ...");
					bufferEmpty.await();
				}
				
				count--;
				System.out.println("[" + Thread.currentThread().getName() + "] Consumer Index: " + index);
				int get = buffer[--index];
				bufferFull.signal();
				return get;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
			
			return -1;
		}
	}
	
	private static class Producer extends Thread {
		private Buffer buffer;
		private Random random = new Random();
		
		public Producer(Buffer buffer, String threadName) {
			super(threadName);
			this.buffer = buffer;
		}
		
		@Override
		public void run() {
			while(true) {
				int randomNumber = random.nextInt(Integer.MAX_VALUE);
				System.out.println("[" + Thread.currentThread().getName() + "] Producer produced: " + randomNumber);
				this.buffer.put(randomNumber);
				
				/*try {
					Thread.sleep(10);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			}
		}
	}
	
	private static class Consumer extends Thread {
		private Buffer buffer;
		
		public Consumer(Buffer buffer, String threadName) {
			super(threadName);
			this.buffer = buffer;
		}
		
		@Override
		public void run() {
			while(true) {
				int consumed = this.buffer.get();
				System.out.println("[" + Thread.currentThread().getName() + "] Consumer consumed: " + consumed);
				
				try {
					System.out.println("[" + Thread.currentThread().getName() + "] Consumer working for 10 seconds");
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
