package org.incito.cw.rental.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {

  /**
   * 使用SHA算法加密
   * 
   * @author Carl 2014年4月24日
   * @param info
   * @return
   * @throws NoSuchAlgorithmException
   */
  public static String encrypt(String info) {
    String result = null;
    try {
      MessageDigest md5 = MessageDigest.getInstance("SHA");
      // 添加干扰信息
      info += "cw760";
      byte[] srcBytes = info.getBytes("UTF8");
      // 使用srcBytes更新摘要
      md5.update(srcBytes);
      // 完成哈希计算，得到result
      byte[] resultBytes = md5.digest();
      result = hex(resultBytes);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return result;
  }

  /**
   * 返回十六进制字符串
   * 
   * @author Carl 2014年4月24日
   * @param arr
   * @return
   */
  private static String hex(byte[] arr) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < arr.length; ++i) {
      sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1, 3));
    }
    return sb.toString();
  }
}
