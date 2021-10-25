package com.reading.app.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cj
 */
public class ReadFile {
    private static void test(String fileDir) {
        List<File> fileList = new ArrayList<File>();
        File file = new File(fileDir);
        File[] files = file.listFiles();
        if (files == null) {
            return;
        }
        // 遍历，目录下的所有文件
        for (File f : files) {
            if (f.isFile()) {
                fileList.add(f);
            } else if (f.isDirectory()) {
                System.out.println(f.getAbsolutePath());
                test(f.getAbsolutePath());
            }
        }
        for (File f1 : fileList) {
            String path = f1.getPath();
            String parentPath = "F:\\temp\\MuMu共享文件夹";
            String pa = path.substring(parentPath.length(),path.length());
            System.out.println(pa);
        }
    }

    public static void main(String[] args) {
        test("F:\\temp\\MuMu共享文件夹");
    }
}
