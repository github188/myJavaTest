
 /**
 * *********************** 版权声明 ***********************************
 *
 * 版权所有：浙江大华技术股份有限公司
 * ©CopyRight DahuaTech 2015   
 *  
 * *******************************************************************
*/

package net.tony.tester;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO
 *
 * @author    郝嘉斌
 * @Date      2015年3月19日
 */
public class Test4 {

	static ReentrantLock lock = new ReentrantLock();
	
	public static class VersionLocker {
		
		volatile ReentrantLock lock;
		
		private String name;

		public VersionLocker() {
			lock = new ReentrantLock();
		}

		public VersionLocker(ReentrantLock lock) {
			super();
			this.lock = lock;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public ReentrantLock getLock() {
			return lock;
		}
		
		public void test() throws InterruptedException {
			try {
				lock.lock();
				TimeUnit.SECONDS.sleep(2);
				System.out.println(lock.toString());
			} finally {
				lock.unlock();
			}
		}
	}
	
	public static void main(String[] args) {
		
		final VersionLocker locker1 = new VersionLocker();
		final VersionLocker locker2 = new VersionLocker(locker1.getLock());
		
		Thread thread1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					locker1.test();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		thread1.start();
		
		Thread thread2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					locker2.test();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		thread2.start();
		
		
	}

}

