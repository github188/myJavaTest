
 /**
 * *********************** 版权声明 ***********************************
 *
 * 版权所有：浙江大华技术股份有限公司
 * ©CopyRight DahuaTech 2015   
 *  
 * *******************************************************************
*/

package net.tony.kicker;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;

/**
 *
 * @author    郝嘉斌
 * @Date      2015年4月10日
 */
public class Kicker {

	private static final String attendanceUrl = "/portal/Base/WebHandler.ashx";
	
	private static final String host = "work.dahuatech.com";
	
	private static HttpClient client;

	
	public static void main(String[] args) throws HttpException, IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException {
		init();
		PostMethod postMethod = new PostMethod(attendanceUrl);
		client.executeMethod(postMethod);
		System.out.println(postMethod.getResponseBodyAsString());
		postMethod.releaseConnection();
		
		KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
		FileInputStream fis = new FileInputStream(new File("D:\\文档\\private\\my.keystore"));
		trustStore.load(fis,"hjb123456".toCharArray());
		
	}

	private static void init() {
		client = new HttpClient();
		Protocol https = new Protocol("https", new MySSLProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", https);
		client.getHostConfiguration().setHost(host, 80, https);
	}
	
}

