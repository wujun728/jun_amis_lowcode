package com.ruoyi.common.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.util.ArrayUtil;
import com.ruoyi.common.utils.StrUtils;
import com.ruoyi.framework.config.RuoYiConfig;

import cn.hutool.core.date.DateUtil;

/**
 * 文件处理工具类
 * @author ruoyi
 */
public class FileUtils extends org.apache.commons.io.FileUtils {
	public static String FILENAME_PATTERN = "[a-zA-Z0-9_\\-\\|\\.\\u4e00-\\u9fa5]+";

	/**
	 * 输出指定文件的byte数组
	 * @param filePath 文件路径
	 * @param os 输出流
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
	 * @param filePath 文件
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
	 * @param filename 文件名称
	 * @return true 正常 false 非法
	 */
	public static boolean isValidFilename(String filename) {
		return filename.matches(FILENAME_PATTERN);
	}

    /**
     * 检查文件是否可下载
     * @param resource 需要下载的文件
     * @return true 正常 false 非法
     */
    public static boolean checkAllowDownload(String resource) {
        // 禁止目录上跳级别
        if (StrUtils.contains(resource, "..")) {
            return false;
        }

        // 检查允许下载的文件规则
        if (ArrayUtil.contains(MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION, FileTypeUtils.getFileType(resource))) {
            return true;
        }

        // 不在允许下载的文件规则
        return false;
    }

	/**
	 * 下载文件名重新编码
	 * @param request 请求对象
	 * @param fileName 文件名
	 * @return 编码后的文件名
	 */
	public static String setFileDownloadHeader(HttpServletRequest request, String fileName)
			throws UnsupportedEncodingException {
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
     * @param response 响应对象
     * @param realFileName 真实文件名
     * @return
     */
    public static void setAttachmentResponseHeader(HttpServletResponse response, String realFileName) throws UnsupportedEncodingException {
        String percentEncodedFileName = percentEncode(realFileName);
        StringBuilder contentDispositionValue = new StringBuilder();
        contentDispositionValue.append("attachment; filename=")
                .append(percentEncodedFileName)
                .append(";")
                .append("filename*=")
                .append("utf-8''")
                .append(percentEncodedFileName);
        response.setHeader("Content-disposition", contentDispositionValue.toString());
    }

    /**
     * 百分号编码工具方法
     * @param s 需要百分号编码的字符串
     * @return 百分号编码后的字符串
     */
    public static String percentEncode(String s) throws UnsupportedEncodingException {
        String encode = URLEncoder.encode(s, StandardCharsets.UTF_8.toString());
        return encode.replaceAll("\\+", "%20");
    }

	/**
	 * 获取文件后缀名，不带“.”
	 * @param fileFileName 文件名或文件路径
	 */
	public static String getFileSuffix(String fileFileName) {
		String fileSuffix = "";
		if (fileFileName.lastIndexOf(".") != -1) {
			fileSuffix = fileFileName.substring(fileFileName.lastIndexOf(".") + 1);
		}
		return fileSuffix;
	}

	/**
	 * 读取修改时间
	 */
	public static String getModifiedTime(String filePath) {
		File file = new File(filePath);
		if (file == null || !file.exists()) {
			return "";
		}
		return getModifiedTime(file);
	}

	/**
	 * 读取修改时间
	 */
	public static String getModifiedTime(File file) {
		Calendar cal = Calendar.getInstance();
		long time = file.lastModified();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		cal.setTimeInMillis(time);
		return formatter.format(cal.getTime());
	}

	/**
	 * 修改文件编辑时间
	 * @param filePath 文件路径
	 * @param modifyTime 最后一次修改时间
	 */
	public static boolean changeFileModifyTime(String filePath, String modifyTime) {
		File file = new File(filePath);
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return file.setLastModified(DateUtil.parse(modifyTime).getTime());
	}

	/**
	 * 校验文件后缀名
	 * @param fileSuffixs 后缀名数组
	 * @param fileFileName 文件名
	 */
	public static boolean checkFileSuffix(String[] fileSuffixs, String fileFileName) {
		String fileSuffix = "";
		if (fileFileName.lastIndexOf(".") != -1) {
			fileSuffix = fileFileName.substring(fileFileName.lastIndexOf(".") + 1).toUpperCase();
		}

		if ("".equals(fileSuffix)) {
			return false;
		}

		for (String str : fileSuffixs) {
			if (fileSuffix.equals(str.toUpperCase())) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 获取下载路径
	 * @param fileName 文件名称
	 */
	public static String getAbsoluteFile(String fileName) {
		String downloadPath = RuoYiConfig.getDownloadPath() + fileName;
		File desc = new File(downloadPath);
		if (!desc.getParentFile().exists()) {
			desc.getParentFile().mkdirs();
		}
		return downloadPath;
	}
}