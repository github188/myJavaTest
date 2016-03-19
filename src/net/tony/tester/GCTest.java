
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
 * @author    郝嘉斌
 * @Date      2015年9月11日
 */
public class GCTest {
	
	static int j = 100;
	
	public static void main(String[] args) {
	
		for (int i = 0 ;i<1000;i++) {
			System.out.println(i);
		}
		System.gc();
		
		
	}
}

