
 /**
 * *********************** 版权声明 ***********************************
 *
 * 版权所有：浙江大华技术股份有限公司
 * ©CopyRight DahuaTech 2016   
 *  
 * *******************************************************************
*/

package net.tony.basic;

/**
 * TODO
 *
 * @author    郝嘉斌
 * @Date      2016年2月29日
 */
public class NotifyTest {

	private static class MyThread extends Thread {

		private final int i;
		
		public MyThread(int i) {
			super();
			this.i = i;
		}

		@Override
		public void run() {
			synchronized (MyThread.class) {
				try {
					MyThread.class.wait();
					System.out.println("线程"+i+"获得了锁");
				} catch (InterruptedException e) {
					e.printStackTrace();
					
				}
			}
		}
		
		
		
	}
	
	public static void main(String[] args) {
		for (int i = 0 ; i <10 ;i++) {
			new MyThread(i).start();
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0 ;i<10;i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (MyThread.class) {
				MyThread.class.notify();	
			}
		}
	}
	
}

