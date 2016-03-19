package net.tony.tester.designmode;

import java.util.Date;
 /**
 * *********************** 版权声明 ***********************************
 *
 * 版权所有：浙江大华技术股份有限公司
 * ©CopyRight DahuaTech 2015   
 *  
 * *******************************************************************
 */

/**
 * 单例模型
 *
 * @author    郝嘉斌
 * @Date      2015-1-23
 */
public class Singleton {

	private Singleton() {
		
	}
	
	private static class SingletonHolder {
		private static Singleton instance = new Singleton();
	}
	
	public static Singleton getInstance() {
		return SingletonHolder.instance;
	}
	
	public static void main(String[] args) {
		Singleton.getInstance();
		Date date = new Date();
		date.setYear(1972);
		System.out.println(date.getTime());
	}
}

