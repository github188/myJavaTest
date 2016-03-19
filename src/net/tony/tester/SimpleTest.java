package net.tony.tester;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;


 /**
 * *********************** 版权声明 ***********************************
 *
 * 版权所有：浙江大华技术股份有限公司
 * ©CopyRight DahuaTech 2014   
 *  
 * *******************************************************************
 */

/**
 *
 * @author    郝嘉斌
 * @Date      2014-12-24
 */
public class SimpleTest {

	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, MalformedURLException, ClassNotFoundException {
		URL url = new File("D:\\workspace\\HttpSimulation\\bin").toURL();
		URL commons_codec_1_5_jar = new URL("file:/D:\\workspace\\HttpSimulation\\main\\lib\\commons-codec-1.5.jar");
		URL commons_httpclient_3_0_1_jar = new URL("file:/D:\\workspace\\HttpSimulation\\main\\lib\\commons-httpclient-3.0.1.jar");
		URL commons_io_2_2_jar = new URL("file:/D:\\workspace\\HttpSimulation\\main\\lib\\commons-io-2.2.jar");
		URL commons_lang3_3_1_jar = new URL("file:/D:\\workspace\\HttpSimulation\\main\\lib\\commons-lang3-3.1.jar");
		URL commons_logging_1_1_1_jar = new URL("file:/D:\\workspace\\HttpSimulation\\main\\lib\\commons-logging-1.1.1.jar");
		URLClassLoader loader = new URLClassLoader(new URL[]{url,commons_codec_1_5_jar,commons_httpclient_3_0_1_jar,commons_io_2_2_jar,commons_lang3_3_1_jar,commons_logging_1_1_1_jar});
		Class c = Class.forName("com.dahua.javagroup.Main",true,loader);
		
		System.out.println(c);
	}
}

