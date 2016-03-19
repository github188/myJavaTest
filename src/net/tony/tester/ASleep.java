/**
 * *********************** 版权声明 ***********************************
 *
 * 版权所有：浙江大华技术股份有限公司
 * ©CopyRight DahuaTech 2015   
 *  
 * *******************************************************************
 */

package net.tony.tester;

/**
 * TODO
 * 
 * @author 郝嘉斌
 * @Date 2015年7月29日
 */
public class ASleep {

	boolean asleep = false;

	public ASleep() throws InterruptedException {
		for (int i = 0; i < 4; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					
					judge();
				}
			}).start();
		}
		Thread.sleep(3000);
		new Thread(new Runnable() {
			
			public void run() {
				asleep = true;
				System.out.println("end");
			}
		}).start();
	}
	
	public void judge() {
		while (!asleep) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			CMS();
		}
	}

	public void CMS() {
		System.out.println("@");
	}
	
	public static void main(String[] args) throws InterruptedException {
		new ASleep();
	}
}
