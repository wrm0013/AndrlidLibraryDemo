package com.gq.baselibrary.https.httputil;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by user on 2016/12/2.
 */

public class SafeUtils {
    public static String secretKey = "4704540132603844096724182068402814985072698556303674268151921210";
    public static int[] is = {1, 5, 8, 10, 13, 16, 19, 22, 25, 27, 28};//要拼接的位置

    /**
     * 签名生成算法
     *
     * @return 签名
     * @throws IOException
     */
    public static String getSignature(HashMap<String, String> params, String secret) throws IOException {
        // 先将参数以其参数名的字典序升序进行排序
        Map<String, String> sortedParams = new TreeMap<>(params);
        Set<Map.Entry<String, String>> entrys = sortedParams.entrySet();

        // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
        StringBuilder basestring = new StringBuilder();
        for (Map.Entry<String, String> param : entrys) {
            basestring.append(param.getKey()).append("=").append(param.getValue());
        }
        basestring.append(secret);

        // 使用MD5对待签名串求签
        byte[] bytes;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            bytes = md5.digest(basestring.toString().getBytes("UTF-8"));
        } catch (GeneralSecurityException ex) {
            throw new IOException(ex);
        }

        // 将MD5输出的二进制结果转换为小写的十六进制
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex);
        }
        return sign.toString();
    }

    /**
     * 返回URL和参数排序的结果
     *
     * @param url URL地址
     */

    public static String sequenceUrl(String url) {
        int i = 0;
        Uri uri = Uri.parse(url);
        Set<String> query = uri.getQueryParameterNames();
        StringBuilder sb = new StringBuilder(url);
        if (!query.isEmpty()) {
            TreeSet<String> treeQuery = new TreeSet<>(query);
            sb.setLength(0);
//            String s = url.substring(0, url.indexOf("?"));
//            sb.append(s);
            for (String key : treeQuery) {
                String value = uri.getQueryParameter(key);
                if (i == 0) {
                    i++;
//                    sb.append("?").append(key).append("=").append(value);
                    sb.append(key).append("=").append(value);
                } else {
                    sb.append("&").append(key).append("=").append(value);
                }
            }
        }
        return sb.append("&").append("secretKey").append("=").append(secretKey + "").toString();
    }

    /** */
    /**
     * @return String 生成32位的随机数作为id
     */
    public static String getCode(int digit) {
        String strRand = "";
        for (int i = 0; i < digit; i++) {
            strRand += String.valueOf((int) (Math.random() * 10));
        }
        return strRand;
    }

    /**
     * 将传来的数据进行数据拼接
     */
    public static String getAccessToData(String number) {
        String code = getCode(32);
        String s = "";

        try {
            byte[] ns = number.getBytes("UTF8");
            byte[] cs = code.getBytes("UTF8");
            for (int i = 0; i < ns.length; i++) {
                cs[is[i]] = ns[i];
            }
            s = new String(cs, "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }


    /**
     * base64加密
     */
    public static String base64Encode(String number) {

        if(TextUtils.isEmpty(number)){
            return "";
        }
        return Base64.encodeToString(number.getBytes(), Base64.DEFAULT);
    }

    /**
     * base64解密
     */
    public static String base64Decode(String number) {

        if(TextUtils.isEmpty(number)){
            return "";
        }
        return new String( Base64.decode(number.getBytes(), Base64.DEFAULT));
    }



}
