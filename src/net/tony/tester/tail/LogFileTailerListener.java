
 /**
 * *********************** 版权声明 ***********************************
 *
 * 版权所有：浙江大华技术股份有限公司
 * ©CopyRight DahuaTech 2015   
 *  
 * *******************************************************************
*/

package net.tony.tester.tail;

/**
 *
 * @author    郝嘉斌
 * @Date      2015年4月16日
 */
public interface LogFileTailerListener {

	void newLogFileLine(String line);
}

