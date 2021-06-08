package com.qingshop.mall.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.MissingResourceException;

@Component
public final class PropertiesUtil  {
   public static final byte[] KEY = { 9, -1, 0, 5, 39, 8, 6, 19 };
   private static Environment env;
   @Autowired
   protected void set(Environment env) throws IOException {
      PropertiesUtil.env = env;
   }

   /**
    * Get a value based on key , if key does not exist , null is returned
    * 
    * @param key
    * @return
    */
   public static String getString(String key) {
      try {
         return env.getProperty(key);
      } catch (MissingResourceException e) {
         return null;
      }
   }

   /**
    * Get a value based on key , if key does not exist , null is returned
    * 
    * @param key
    * @return
    */
   public static String getString(String key, String defaultValue) {
      try {
         String value = env.getProperty(key);
         if (StringUtils.isEmpty(value)) {
            return defaultValue;
         }
         return value;
      } catch (MissingResourceException e) {
         return defaultValue;
      }
   }

   /**
    * 根据key获取值
    * 
    * @param key
    * @return
    */
   public static int getInt(String key) {
      return Integer.parseInt(env.getProperty(key));
   }

   /**
    * 根据key获取值
    * 
    * @param key
    * @param defaultValue
    * @return
    */
   public static int getInt(String key, int defaultValue) {
      String value = env.getProperty(key);
      if (StringUtils.isBlank(value)) {
         return defaultValue;
      }
      return Integer.parseInt(value);
   }

   /**
    * 根据key获取值
    * 
    * @param key
    * @param defaultValue
    * @return
    */
   public static boolean getBoolean(String key, boolean defaultValue) {
      String value = env.getProperty(key);
      if (StringUtils.isBlank(value)) {
         return defaultValue;
      }
      return new Boolean(value);
   }
}