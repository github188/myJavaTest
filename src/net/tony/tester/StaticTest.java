
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
 * @Date      2015年9月14日
 */
public class StaticTest {

	
	
	public static void main(String[] args) {

		System.out.println(Const.HELLO_WORLD);
		
		Const cons = new Const();
		int i = 1;
		Integer j =2;
		cons.test(i, j);
		System.out.println(i);
		System.out.println(j);
	}

}

