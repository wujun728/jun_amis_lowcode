package com.jun.plugin.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Stack;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件处理工具类
 */
public class FileUtils extends org.apache.commons.io.FileUtils {
	public static String FILENAME_PATTERN = "[a-zA-Z0-9_\\-\\|\\.\\u4e00-\\u9fa5]+";

	/**
	 * 文件或目录是否存在
	 */
	public static boolean exists(String path) {
		return new File(path).exists();
	}

	/**
	 * 文件是否存在
	 */
	public static boolean existsFile(String path) {
		File file = new File(path);
		return file.exists() && file.isFile();
	}

	/**
	 * 创建文件夹，如果目标存在则删除
	 */
	public static File createDir(String path) throws IOException {
		return createDir(path, false);
	}

	/**
	 * 创建文件，如果目标存在则删除
	 */
	public static File createFile(String path, boolean isHidden) throws IOException {
		File file = createFileSmart(path);
		if (System.getProperty("os.name").startsWith("win")) {
			Files.setAttribute(file.toPath(), "dos:hidden", isHidden);
		}
		return file;
	}

	public static File createFileSmart(String path) throws IOException {
		try {
			File file = new File(path);
			if (file.exists()) {
				file.delete();
				file.createNewFile();
			} else {
				createDirSmart(file.getParent());
				file.createNewFile();
			}
			return file;
		} catch (IOException e) {
			throw new IOException("createFileSmart=" + path, e);
		}
	}

	public static File createDirSmart(String path) throws IOException {
		try {
			File file = new File(path);
			if (!file.exists()) {
				Stack<File> stack = new Stack<>();
				File temp = new File(path);
				while (temp != null) {
					stack.push(temp);
					temp = temp.getParentFile();
				}
				while (stack.size() > 0) {
					File dir = stack.pop();
					if (!dir.exists()) {
						dir.mkdir();
					}
				}
			}
			return file;
		} catch (Exception e) {
			throw new IOException("createDirSmart=" + path, e);
		}
	}

	/**
	 * 创建文件夹，如果目标存在则删除
	 */
	public static File createDir(String path, boolean isHidden) throws IOException {
		File file = new File(path);
		deleteIfExists(file);
		File newFile = new File(path);
		newFile.mkdirs();
		if (System.getProperty("os.name").startsWith("win")) {
			Files.setAttribute(newFile.toPath(), "dos:hidden", isHidden);
		}
		return file;
	}

	/**
	 * 删除文件或文件夹
	 */
	public static void deleteIfExists(File file) throws IOException {
		if (file.exists()) {
			if (file.isFile()) {
				if (!file.delete()) {
					throw new IOException("Delete file failure,path:" + file.getAbsolutePath());
				}
			} else {
				File[] files = file.listFiles();
				if (files != null && files.length > 0) {
					for (File temp : files) {
						deleteIfExists(temp);
					}
				}
				if (!file.delete()) {
					throw new IOException("Delete file failure,path:" + file.getAbsolutePath());
				}
			}
		}
	}

	/**
	 * 删除文件或文件夹
	 */
	public static void deleteIfExists(String path) throws IOException {
		deleteIfExists(new File(path));
	}

	/**
	 * 输出指定文件的byte数组
	 * 
	 * @param filePath
	 *            文件路径
	 * @param os
	 *            输出流
	 * @return
	 */
	public static void writeBytes(String filePath, OutputStream os) throws IOException {
		FileInputStream fis = null;
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				throw new FileNotFoundException(filePath);
			}
			fis = new FileInputStream(file);
			byte[] b = new byte[1024];
			int length;
			while ((length = fis.read(b)) > 0) {
				os.write(b, 0, length);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param filePath
	 *            文件
	 * @return
	 */
	public static boolean deleteFile(String filePath) {
		boolean flag = false;
		File file = new File(filePath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * 文件名称验证
	 * 
	 * @param filename
	 *            文件名称
	 * @return true 正常 false 非法
	 */
	public static boolean isValidFilename(String filename) {
		return filename.matches(FILENAME_PATTERN);
	}

	/**
	 * 下载文件名重新编码
	 * 
	 * @param request
	 *            请求对象
	 * @param fileName
	 *            文件名
	 * @return 编码后的文件名
	 */
	public static String setFileDownloadHeader(HttpServletRequest request, String fileName) throws UnsupportedEncodingException {
		final String agent = request.getHeader("USER-AGENT");
		String filename = fileName;
		if (agent.contains("MSIE")) {
			// IE浏览器
			filename = URLEncoder.encode(filename, "utf-8");
			filename = filename.replace("+", " ");
		} else if (agent.contains("Firefox")) {
			// 火狐浏览器
			filename = new String(fileName.getBytes(), "ISO8859-1");
		} else if (agent.contains("Chrome")) {
			// google浏览器
			filename = URLEncoder.encode(filename, "utf-8");
		} else {
			// 其它浏览器
			filename = URLEncoder.encode(filename, "utf-8");
		}
		return filename;
	}

	/**
	 * 下载文件名重新编码
	 *
	 * @param response
	 *            响应对象
	 * @param realFileName
	 *            真实文件名
	 * @return
	 */
	public static void setAttachmentResponseHeader(HttpServletResponse response, String realFileName) throws UnsupportedEncodingException {
		String percentEncodedFileName = percentEncode(realFileName);

		StringBuilder contentDispositionValue = new StringBuilder();
		contentDispositionValue.append("attachment; filename=").append(percentEncodedFileName).append(";").append("filename*=").append("utf-8''").append(percentEncodedFileName);

		response.setHeader("Content-disposition", contentDispositionValue.toString());
	}

	/**
	 * 百分号编码工具方法
	 *
	 * @param s
	 *            需要百分号编码的字符串
	 * @return 百分号编码后的字符串
	 */
	public static String percentEncode(String s) throws UnsupportedEncodingException {
		String encode = URLEncoder.encode(s, StandardCharsets.UTF_8.toString());
		return encode.replaceAll("\\+", "%20");
	}
}
