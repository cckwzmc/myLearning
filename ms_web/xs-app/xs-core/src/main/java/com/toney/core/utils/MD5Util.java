package com.toney.core.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author kevin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MD5Util {
    
    public String getDigest(byte[] originData) {
        
        byte[] digest = null;
        try {
            MessageDigest alga = MessageDigest.getInstance("MD5");
            alga.update(originData);
            digest = alga.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        return getMD5ofStr(digest);
    }

    private String getMD5ofStr(byte[] digest) {
            String digestHexStr = "";
            for (int i = 0; i < 16; i++) {
                    digestHexStr += byteHEX(digest[i]);
            }
            return digestHexStr;
    }

    private String byteHEX(byte ib) {
            char[] Digit = { '0','1','2','3','4','5','6','7','8','9',
            'A','B','C','D','E','F' };
            char [] ob = new char[2];
            ob[0] = Digit[(ib >>> 4) & 0X0F];
            ob[1] = Digit[ib & 0X0F];
            String s = new String(ob);
            return s;
    }

    public static String getMD5LowerCase(String str)
    {
       return new MD5Util().getDigest(str.getBytes()).toLowerCase();    
    }
    
    public static String getMD5UpperCase(String str)
    {
       return new MD5Util().getDigest(str.getBytes()).toUpperCase();    
    }
    public static String getMD5(String str)
    {
    	return new MD5Util().getDigest(str.getBytes());    
    }
    
    
}

