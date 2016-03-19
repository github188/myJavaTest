package net.tony.tester;
 /**
 * *********************** 版权声明 ***********************************
 *
 * 版权所有：浙江大华技术股份有限公司
 * ©CopyRight DahuaTech 2015   
 *  
 * *******************************************************************
 */

/**
 * 加载顺序
 *
 * @author    郝嘉斌
 * @Date      2015-1-23
 */
public class LoadOrder {
	
	static{
		System.out.println("我是静态代码块1");
	}
	
	{
		System.out.println("我是代码块1");
	}
	
	public String a = getMemberString();
	
	public static String b = getStaticString();
	
	static{
		System.out.println("我是静态代码块2");
	}
	
	{
		System.out.println("我是代码块2");
	}

	public static class InnerClass{
		
		private static String innerA = getInnerStaticString();
		
		static {
			System.out.println("我是静态内部代码块");
		}
		
		private static String getInnerStaticString() {
			System.out.println("我是内部类静态");
			return "我是内部类静态";
		}
		
	}
	
	public LoadOrder() {
		super();
		System.out.println("我是构造函数");
	}
	
	private static String getStaticString() {
		System.out.println("我是静态变量");
		return "我是静态变量";
	}
	
	private String getMemberString() {
		System.out.println("我是成员变量");
		return "我是成员变量";
	}
	
	public static void test() {
		System.out.println("我是静态函数");
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		//LoadOrder.class.getClassLoader().loadClass("LoadOrder");
		//new LoadOrder();
		Class.forName("net.tony.tester.LoadOrder");
		System.out.println("实例化");
		new LoadOrder();
		System.out.println("调用inner");
		Class.forName("net.tony.tester.LoadOrder$InnerClass");
	}
	
}

