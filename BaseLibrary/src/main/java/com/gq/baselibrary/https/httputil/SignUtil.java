package com.gq.baselibrary.https.httputil;

import java.security.MessageDigest;

/**
 * Created by user on 2017/11/24.
 */

public class SignUtil {

    public static String SignMD5(String str) {
        String re_md5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            re_md5 = buf.toString();
        } catch (Exception e) {
        }
        return re_md5.toUpperCase();
    }

}


