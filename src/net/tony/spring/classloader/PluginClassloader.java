
 /**
 * *********************** 版权声明 ***********************************
 *
 * 版权所有：浙江大华技术股份有限公司
 * ©CopyRight DahuaTech 2015   
 *  
 * *******************************************************************
*/

package net.tony.spring.classloader;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * TODO
 *
 * @author    郝嘉斌
 * @Date      2015年4月15日
 */
public class PluginClassloader  extends URLClassLoader{

	public PluginClassloader(URL[] urls) {
		
		super(urls);
		
	}

	
}

