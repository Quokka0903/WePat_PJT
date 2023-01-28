package com.wepat.domain.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class OpenCrypt {
    private static final Logger logger = LoggerFactory.getLogger(OpenCrypt.class);

    public static byte[] getSHA256(String source, String salt) {
        byte byteData[] = null;
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(source.getBytes());
            md.update(salt.getBytes());
            byteData = md.digest();
            logger.info("원문: "+source+ "   SHA-256: "+
                    byteData.length+","+byteArrayToHex(byteData));
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return byteData;
    }


    // byte[] to hex
    public static String byteArrayToHex(byte[] ba) {
        if (ba == null || ba.length == 0) {
            return null;
        }

        StringBuffer sb = new StringBuffer(ba.length * 2);
        String hexNumber;
        for (int x = 0; x < ba.length; x++) {
            hexNumber = "0" + Integer.toHexString(0xff & ba[x]);

            sb.append(hexNumber.substring(hexNumber.length() - 2));
        }
        return sb.toString();
    }

}

