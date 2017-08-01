package com.example;

import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileSplitter {
	public static void main(String[] args) {
		String fPath = "C:\\tmp\\test-file-new.txt";
		
		try {
			split(fPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void split(final String fPath) throws Exception {
		RandomAccessFile raf = new RandomAccessFile(fPath, "r");
		long length = raf.length();
		raf.close();
		final long byteSize = length/3;
		long remainder = length%3;
		
		System.out.println("File Length: " + length + ", Byte Size: " + byteSize + ", R: " + remainder);
		
		long startOffset = 0L;
		
		for(int loop = 0; loop < 3; loop++) {
			final long sOffset = startOffset;
			//final long eOffset = startOffset + byteSize;
			
			Runnable runnable = new Runnable() {
				
				public void run() {
					
					FileSplitter fileSplitter = new FileSplitter();
					try {
						Runtime runtime = Runtime.getRuntime();
						String tName = Thread.currentThread().getName();
						System.out.println("[" + tName + "]Total Mem: " + (runtime.totalMemory()/1000) + ", Free Mem: " + (runtime.freeMemory()/1000));
						fileSplitter.readChunk(sOffset, byteSize, fPath);
						System.out.println("[" + tName + "]Total Mem: " + (runtime.totalMemory()/1000) + ", Free Mem: " + (runtime.freeMemory()/1000));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			Thread executor = new Thread(runnable, "THREAD-" + loop);
			executor.start();
			startOffset = sOffset + byteSize;
			
		}
		
		Runtime runtime = Runtime.getRuntime();
		System.out.println("DONE. Total Mem: " + (runtime.totalMemory()/1000) + ", Free Mem: " + (runtime.freeMemory()/1000));
	}
	
	public void readChunk(long start, long byteSize, String filePath) throws Exception {
		System.out.println("Data: " + start + "-" + byteSize);
		
		byte[] byteData = new byte[(int) byteSize];
		RandomAccessFile raf = new RandomAccessFile(filePath, "r");
		raf.seek(start);
		Runtime runtime = Runtime.getRuntime();
		String tName = Thread.currentThread().getName();
		System.out.println("[" + tName + "]Total Mem: " + (runtime.totalMemory()/1000) + ", Free Mem: " + (runtime.freeMemory()/1000));
		/*boolean readNext = true;
		String line = null;
		long bytesRead = 0L;
		StringBuilder sBuilder = new StringBuilder();*/
		
		/*while (readNext && (line = raf.readLine()) != null ) {
			sBuilder.append(line);
			sBuilder.append(System.lineSeparator());
			bytesRead += line.length();
			if(bytesRead >= byteSize) {
				readNext = false;
			}
		}*/
		//System.out.println("Bytes Read: " + bytesRead);
		raf.readFully(byteData);
		raf.close();
		
		//Files.lines(null).
		Path parent = Paths.get(filePath, "").getParent();
		Files.write(parent.resolve("file-" + start + ".txt"), byteData, StandardOpenOption.CREATE);
		System.out.println("[" + tName + "]Total Mem: " + (runtime.totalMemory()/1000) + ", Free Mem: " + (runtime.freeMemory()/1000));
	}
}
