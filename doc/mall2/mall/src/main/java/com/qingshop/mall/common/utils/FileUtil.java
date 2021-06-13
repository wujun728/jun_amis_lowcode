package com.qingshop.mall.common.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qingshop.mall.common.exception.BusinessException;

/**
 * FolderFileScanner
 */
public class FileUtil {

	private static final Logger log = LoggerFactory.getLogger(FileUtil.class);

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

	public static void checkDirectoryTraversal(Path parentPath, Path pathToCheck) {
		if (pathToCheck.startsWith(parentPath.normalize())) {
			return;
		}

		throw new BusinessException("无权限访问 " + pathToCheck);
	}

	public static Path createTempDirectory() throws IOException {
		return Files.createTempDirectory("nbclass");
	}

	public static boolean isEmpty(Path path) throws IOException {
		if (!Files.isDirectory(path) || Files.notExists(path)) {
			return true;
		}

		try (Stream<Path> pathStream = Files.list(path)) {
			return pathStream.count() == 0;
		}
	}

	public static void unzip(ZipInputStream zis, Path targetPath) throws IOException {
		if (Files.notExists(targetPath)) {
			Files.createDirectories(targetPath);
		}
		if (!isEmpty(targetPath)) {
			throw new DirectoryNotEmptyException("Target directory: " + targetPath + " was not empty");
		}

		ZipEntry zipEntry = zis.getNextEntry();

		while (zipEntry != null) {
			Path entryPath = targetPath.resolve(zipEntry.getName());
			FileUtil.checkDirectoryTraversal(targetPath, entryPath);
			if (zipEntry.isDirectory()) {
				Files.createDirectories(entryPath);
			} else {
				Files.copy(zis, entryPath);
			}
			zipEntry = zis.getNextEntry();
		}
	}

	public static void delete(Path deletingPath) {
		try {
			if (Files.isDirectory(deletingPath)) {
				Files.walk(deletingPath).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
			} else {
				Files.delete(deletingPath);
			}
		} catch (IOException e) {
			log.warn("Failed to delete:{},{}", deletingPath, e);
		}
	}

	public static String getBaseName(String filename) {
		int separatorLastIndex = StringUtils.lastIndexOf(filename, File.separatorChar);
		if (separatorLastIndex == filename.length() - 1) {
			return "";
		}
		if (separatorLastIndex >= 0 && separatorLastIndex < filename.length() - 1) {
			filename = filename.substring(separatorLastIndex + 1);
		}
		int dotLastIndex = StringUtils.lastIndexOf(filename, '.');

		if (dotLastIndex < 0) {
			return filename;
		}
		return filename.substring(0, dotLastIndex);
	}

	public static void closeStream(InputStream inputStream) {
		try {
			if (inputStream != null) {
				inputStream.close();
			}
		} catch (IOException e) {
			log.warn("Failed to close input stream", e);
		}
	}

	public static void closeStream(ZipInputStream zipInputStream) {
		try {
			if (zipInputStream != null) {
				zipInputStream.closeEntry();
				zipInputStream.close();
			}
		} catch (IOException e) {
			log.warn("Failed to close input zipInputStream", e);
		}
	}

	public static Path skipZipParentFolder(Path unzippedPath) throws IOException {
		try (Stream<Path> pathStream = Files.list(unzippedPath)) {
			List<Path> childrenPath = pathStream.collect(Collectors.toList());
			if (childrenPath.size() == 1 && Files.isDirectory(childrenPath.get(0))) {
				return childrenPath.get(0);
			}
			return unzippedPath;
		}
	}

	public static void copyFolder(Path source, Path target) throws IOException {
		Files.walkFileTree(source, new SimpleFileVisitor<Path>() {

			private Path current;

			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				current = target.resolve(source.relativize(dir).toString());
				Files.createDirectories(current);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				Files.copy(file, target.resolve(source.relativize(file).toString()), StandardCopyOption.REPLACE_EXISTING);
				return FileVisitResult.CONTINUE;
			}
		});
	}

	public static List<String> getTemplates(Path path) {
		List<String> themeTemplate = new ArrayList<>();
		try (Stream<Path> templateStream = Files.list(path)) {
			templateStream.forEach(templatePath -> {
				String templatePathStr = templatePath.toString();
				if (!Files.isDirectory(templatePath) && templatePathStr.endsWith("html")) {
					String templateName = templatePathStr.substring(templatePathStr.lastIndexOf(File.separator) + 1).split("\\.")[0];
					themeTemplate.add(templateName);
				}
			});
			return themeTemplate;
		} catch (IOException e) {
			throw new BusinessException("Get theme html template error");
		}
	};

	public static String readFile(Path path) {
		try {
			return new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
		} catch (IOException e) {
			return null;
		}
	}
}