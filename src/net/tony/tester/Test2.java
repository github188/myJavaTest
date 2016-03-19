
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
 * @Date      2015-3-10
 */
public class Test2 {

	volatile Test2 t;
	
	private static class T {
		
		public T() {
			test();
		}

		public void test(){
			
		}
	}
	
	private static final class Ta extends T {

		String a;
		
		public Ta() {
			a = "A";
		}



		@Override
		public void test() {
			System.out.println(a);
		}
		
	}
	
	public static void main(String[] args) {
		Ta a = new Ta();
		a.test();
	}
}

