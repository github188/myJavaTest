
 /**
 * *********************** 版权声明 ***********************************
 *
 * 版权所有：浙江大华技术股份有限公司
 * ©CopyRight DahuaTech 2015   
 *  
 * *******************************************************************
*/

package net.tony.single;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author    郝嘉斌
 * @Date      2015年3月12日
 */
public class Reader {
	
	private static final String[] CHINESE_NUMBER = {"零","一","二","三","四","五","六","七","八","九"};

	private static final Map<String,Integer> unitMap = new HashMap<String,Integer>();
	static {
		unitMap.put("十", 10);
		unitMap.put("百", 100);
		unitMap.put("千", 1000);
		unitMap.put("万", 10000);//够了
	}
	
	public static void main(String[] args) {
		/*System.out.println(longToChn(70));
		System.out.println(longToChn(10));
		System.out.println(longToChn(5));
		System.out.println(longToChn(79));
		System.out.println(longToChn(179));
		System.out.println(longToChn(100));
		System.out.println(longToChn(120));
		System.out.println(longToChn(1000));
		System.out.println(longToChn(1100));
		System.out.println(longToChn(1120));
		System.out.println(longToChn(1129));*/
		
		long num = Long.valueOf(args[0]);
		
		System.out.println(txtFile(chapter(num), chapter(num+1)));
		
	}

	public static String txtFile(String begin,String end) {
		StringBuilder result = new StringBuilder();
        InputStreamReader reader = null;
        BufferedReader bReader = null;
        boolean start = false;
        int counter = 0;
        try {
            reader = new InputStreamReader(new FileInputStream("D:\\文档\\资料\\fileupload\\cp_bak.txt"),"GBK");
            bReader = new BufferedReader(reader);
            
            String line = bReader.readLine();
            while(line != null) {
                if (!start) {
                	if (line.contains(begin)) {
                		start = true;
                		appendByWidth(line.substring(line.indexOf(begin)+1), result);
                	}
                } else {
                	if (line.contains(end)) {
                		appendByWidth(line.substring(0,line.indexOf(end)), result);
                		return result.toString();
                	} else {
                		appendByWidth(line, result);
                	}
                }
                line = bReader.readLine();
                counter ++;
            }
        } catch (Exception e) {
        } 
        System.out.println("counter:"+counter);
        return result.toString();
	}
	
	private static String longToChn(long number) {
		
		String chn = "";
		
		int num = (int) (number%10);
		long left = number/10;
		
		if (num > 0 ) {//个位
			chn += CHINESE_NUMBER[num];
		}
		
		num = (int) (left%10);
		left = left/10;
		
		if (num > 0 ) {
			chn = CHINESE_NUMBER[num]+ "十" +chn;
		} else if (left > 0 && !"".equals(chn)) {
			chn = "零" + chn;
		}
		
		num = (int) (left%10);
		left = left/10;
		if (num >0 ){
			chn = CHINESE_NUMBER[num] +"百" + chn;
		} else if (left > 0 ){
			if (!"".equals(chn) && !chn.endsWith("零")) {
				chn = "零" + chn;
			} else {
			}
		}
		
		num = (int) (left%10);
		left = left/10;
		if (num > 0) {
			chn = CHINESE_NUMBER[num] +"千" + chn;
		}
		
		return chn;
	}
	
	private static String chapter(long chapterNum) {
		return "第"+longToChn(chapterNum)+"章";
	}
	
	private static void appendByWidth(String str,StringBuilder sb) {
		int length = 30;
		int counter = 0 ;
		for (int i = 0 ; i < str.length() ; i++) {
			sb.append(str.charAt(i));
			counter ++;
			if (counter == length) {
				sb.append("\r\n");
				counter = 0;
			}
		}
	}
}

