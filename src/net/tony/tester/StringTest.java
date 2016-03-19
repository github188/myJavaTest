
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
 * @Date      2015-3-3
 */
public class StringTest {

	/**
	 * TODO
	 *
	 * @param args      
	 */

	public static void main(String[] args) {

		String a = "abc" + "def"+"hgk";
		
		String b = "abc";
		b = b +"def";
		b = b + "hgk";
		
		String s = "";
		for (int i =0;  i < 1000;i++){
			s = s+"1";
		}
		//编译后
		/*String s = "";
		for (int i =0 ;i < 1000 ; i++) {
			s = new StringBuilder(s).append("1").toString();
		}*/
	}

}

