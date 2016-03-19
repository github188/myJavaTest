
 /**
 * *********************** 版权声明 ***********************************
 *
 * 版权所有：浙江大华技术股份有限公司
 * ©CopyRight DahuaTech 2015   
 *  
 * *******************************************************************
*/

package net.tony.tester.hashmap;

/**
 *
 * @author    郝嘉斌
 * @Date      2015年7月10日
 */
public class DeadCircleTest {
	
	final static IntHashMap map = new IntHashMap(4);

	public static void main(String[] args) throws InterruptedException {
		map.put(new Integer(36), new Object(),false);
		map.put(new Integer(72), new Object(),false);
		//map.put(new Integer(1), new Object(),false);
		
		Thread t1 = new Thread(
			new Runnable() {
			
				@Override
				public void run() {
					map.rehash(true);
					//map.put(new Integer(108), new Object(),true);
				}
				
			}
		);
		t1.start();
		//Thread.sleep(500);
		Thread t2 = new Thread(
				new Runnable() {
					
					@Override
					public void run() {
						map.rehash(false);
						//map.put(new Integer(144), new Object(),false);
					}
					
				}
				);
		t2.start();
		
		System.out.println(map);
		Thread.sleep(15000);
		
		//System.out.println(map.get(18));
		new Thread(
				new Runnable() {
					
					@Override
					public void run() {
						System.out.println(map.get(18));
					}
				}).start();
		
		new Thread(
				new Runnable() {
					
					@Override
					public void run() {
						System.out.println(map.get(144));
					}
				}).start();
	}
	
}

