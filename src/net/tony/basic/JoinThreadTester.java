
 /**
 * *********************** 版权声明 ***********************************
 *
 * 版权所有：浙江大华技术股份有限公司
 * ©CopyRight DahuaTech 2015   
 *  
 * *******************************************************************
*/

package net.tony.basic;

import java.io.Console;
import java.io.File;

/**
 * TODO
 *
 * @author    郝嘉斌
 * @Date      2015年3月14日
 */
public class JoinThreadTester {
	
	public static class JoinThread extends Thread {
		public static volatile int n = 0 ;
		
		public void run() {
			for (int i=0;i<10;i++) {
				n = n+1;
				try {
					sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {

		Thread threads[] = new Thread[100];
		for (int i =0 ; i < threads.length ;i++) {
			threads[i] = new JoinThread();
		}
		for (int i =0 ; i < threads.length ;i++) {
			threads[i].start();
		}
		for (int i =0 ; i < threads.length ;i++) {
			threads[i].join();
		}
		System.out.println("n="+JoinThread.n);
		
		/*File f = new File("D:\\restartAll.sh");
		while (true) {
			Thread.sleep(2000);
			System.out.println(f.lastModified());
		}*/
	}
	
}

