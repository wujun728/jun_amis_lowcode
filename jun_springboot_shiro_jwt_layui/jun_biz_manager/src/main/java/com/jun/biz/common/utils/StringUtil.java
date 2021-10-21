package com.jun.biz.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 2019/6/14 14:47
 * <p>
 * Description: [字符串帮助]
 * <p>
 *
 * 
 */
public class StringUtil {
    /**
     * 按字节截取字符串
     *
     * @param string
     * @param byteSize
     * @return
     */
    public static String subString(String string, int byteSize) {
        if (string == null || string.isEmpty()) {
            return string;
        }
        int length = getBytes(string, "GB18030").length;
        if (length <= byteSize) {
            return string;
        }

        String prevSubString = "";
        for (int i = 1; i < string.length(); i++) {
            String subString = string.substring(0, i);
            int subStringLength = getBytes(subString, "GB18030").length;
            if (subStringLength < byteSize) {
                prevSubString = subString;
            } else if (subStringLength == byteSize) {
                return subString;
            } else {
                return prevSubString;
            }
        }

        return prevSubString;
    }

    public static byte[] getBytes(String s, String charset) {
        try {
            return s.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String findByRegex(String content, String regex) {
        Pattern c = Pattern.compile(regex);
        Matcher mc = c.matcher(content);
        if (mc.find()) {
            return mc.group(1);
        }
        return null;
    }

    public static List<String> splitByRegex(String content, String regex) {
        List<String> result = new ArrayList<>(8);
        Pattern c = Pattern.compile(regex);
        Matcher matcher = c.matcher(content);
        while (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                result.add(matcher.group(i));
            }
        }
        return result;
    }


    public static String unicodeToCn(String unicode) {
        String unicodeCompile = "(?<=\\\\u).{4}?";
        String a;
        Matcher matcher = Pattern.compile(unicodeCompile).matcher(unicode);
        for (; matcher.find(); ) {
            a = matcher.group();
            unicode = unicode.replace("\\u" + a, String.valueOf((char) Integer.valueOf(a, 16).intValue()));
        }
        return unicode;
    }

    public static String nullOrString(Object obj) {
        return obj == null ? null : obj.toString();
    }

    /**
     * 将 str2 插入到 str 中 w 后面的位置
     * @param str
     * @param w
     * @param str2
     * @return
     */
    public static String insert(String str , String w,String str2){
        if(!str.contains(w)){
            return str + str2;
        }
        int idx = str.indexOf(w)+w.length()-1;
        return str.substring(0,idx+1)+str2+str.substring(idx+1);
    }


    public static void main(String[] args) throws UnsupportedEncodingException {
//        String s = "ღ᭄ꦿ离岸এ樱º᭄এ";
//        System.out.println(findByRegex("我是one，13661377510","(\\d{11})"));
//        System.out.println("前缀-0303-男-武汉-13661377510-one".getBytes("GB18030").length);
//        s = subString(s, 32);
//        System.out.println(s);
//        System.out.println(s.getBytes("GB18030").length);

//        System.out.println(insert("abcdefg","ef","|"));
        System.out.println(findByRegex("36.5KiG","([0-9,.]*)"));
    }
}
