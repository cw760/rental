package com.rp.rentalcar.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.os.Environment;

public class FileUtils {

	public static String SDPATH = Environment.getExternalStorageDirectory()
			+ "/";

	public String getSDPATH() {
		return SDPATH;
	}

	/**
	 * 在SD卡上创建文件
	 * 
	 * @throws IOException
	 */
	public static File creatSDFile(String fileName) throws IOException {
		File file = new File(SDPATH + fileName);
		if (file.exists()) {
			file.delete();
		}
		file.createNewFile();
		return file;
	}

	/**
	 * 在SD卡上创建目录
	 * 
	 * @param dirName
	 */
	public static File creatSDDir(String dirName) {
		File dir = new File(SDPATH + dirName);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir;
	}

	/**
	 * 判断SD卡上的文件夹是否存在
	 */
	public static boolean isFileExist(String fileName) {
		File file = new File(SDPATH + fileName);
		return file.exists();
	}

	/**
	 * 将一个InputStream里面的数据写入到SD卡中
	 */
	public static File write2SDFromInput(String path, String fileName,
			InputStream input) {
		File file = null;
		OutputStream output = null;
		try {
			creatSDDir(path);
			file = creatSDFile(path + fileName);
			output = new FileOutputStream(file);
			int length = 0;
			byte buffer[] = new byte[1024 * 1024];
			while ((length = input.read(buffer)) != -1) {
				output.write(buffer, 0, length);
			}
			output.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				output.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return file;
	}
}
