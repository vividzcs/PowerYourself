package com.nwuer.core.common.util;

import org.springframework.beans.factory.annotation.Value;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

    @Value("${nwuer.user.salt}")
    private static String salt;

    /**
     * 获取特定字符串的MD5码
     * eg:md5("YEN") 输出：0d9962cb9239354e6da0ca55ebd0b93c
     * @param str
     * @return
     */
    public static String md5(String str){
        try {
            MessageDigest md=MessageDigest.getInstance("MD5");
            str += salt;
            md.update(str.getBytes());
            byte[] b=md.digest();

            int temp;
            StringBuffer sb=new StringBuffer("");
            for ( int offset = 0; offset <b.length ; offset++ ) {
                temp=b[offset];
                if(temp<0) temp+=256;
                if(temp<16) sb.append("0");
                sb.append(Integer.toHexString(temp));
            }
            str=sb.toString();

        } catch ( NoSuchAlgorithmException e ) {
            e.printStackTrace();
        }
        return str;
    }
}