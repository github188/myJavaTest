
 /**
 * *********************** 版权声明 ***********************************
 *
 * 版权所有：浙江大华技术股份有限公司
 * ©CopyRight DahuaTech 2015   
 *  
 * *******************************************************************
*/

package net.tony.tester;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author    郝嘉斌
 * @Date      2015年3月16日
 */
public class Test3 {
	
	private static boolean stop;

	public static void main(String[] args) throws InterruptedException {
		/*Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (!stop) {
					System.out.println("test");
				}
			}
		});
		thread.start();
		TimeUnit.SECONDS.sleep(1);
		stop = true;*/
		
		
		final CountDownLatch ready = new CountDownLatch(10);
		final CountDownLatch start = new CountDownLatch(1);
		final CountDownLatch done = new CountDownLatch(10);
		
		for (int i = 0 ; i < 10;i++) {
			Executor executor = Executors.newSingleThreadExecutor();
			final int j = i;
			executor.execute(new Runnable() {
				
				@Override
				public void run() {
					ready.countDown();
					System.out.println(j);
					try {
						start.await();
						System.out.println("do sth "+j);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally 
					{
						done.countDown();
					}
					
				}
			});
		}
		ready.await();
		long startNanos = System.nanoTime();
		start.countDown();
		done.await();
		System.out.println(System.nanoTime() - startNanos);
		
	}

}

