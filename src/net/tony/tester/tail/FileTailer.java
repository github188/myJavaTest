
 /**
 * *********************** 版权声明 ***********************************
 *
 * 版权所有：浙江大华技术股份有限公司
 * ©CopyRight DahuaTech 2015   
 *  
 * *******************************************************************
*/

package net.tony.tester.tail;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author    郝嘉斌
 * @Date      2015年4月16日
 */
public class FileTailer extends Thread {

	private long sleep = 500;
	
	private final File logFile;
	
	private boolean startAtBegin = false;
	
	private boolean tailing = false;
	
	private Set<LogFileTailerListener> listeners = new HashSet<LogFileTailerListener>();

	public FileTailer(File logFile) {
		super();
		this.logFile = logFile;
	}
	
	
	public FileTailer(long sleep, File logFile) {
		super();
		this.sleep = sleep;
		this.logFile = logFile;
	}

	public void addListener(LogFileTailerListener listener) {
		if (tailing) {
			throw new IllegalStateException("can not add listener when tail started");
		}
		listeners.add(listener);
	}

	protected void fireNewLogFileLine(String line) {
		for (Iterator i = this.listeners.iterator() ; i.hasNext();) {
			LogFileTailerListener l = (LogFileTailerListener) i.next();
			l.newLogFileLine(line);
		}
	}
	
	public void stopTailing() {
		this.tailing = false;
	}
	
	public void run() {
		long filePointer = 0;
		if (this.startAtBegin) {
			filePointer = 0;
		} else {
			filePointer = this.logFile.length();
		}
		try{
			this.tailing = true;
			RandomAccessFile file = new RandomAccessFile(logFile, "r");
			while (this.tailing) {
				long fileLength = this.logFile.length();
				if (fileLength < filePointer) {
					file = new RandomAccessFile(logFile, "r");
					filePointer = 0;
				}
				
				if (fileLength > filePointer) {
					file.seek(filePointer);
					String line = file.readLine();
					while (line != null) {
						this.fireNewLogFileLine(line);
						line = file.readLine();
					}
					filePointer = file.getFilePointer();
				}
				sleep(this.sleep);
			}
			file.close();
		} catch (IOException e) {
			
		} catch (InterruptedException e) {
			
		}
	}
}

