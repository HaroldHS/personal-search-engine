package com.example.personal_search_engine.helper;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {

    public static String getMD5Hash(String input) {
        String hashResult;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(input.getBytes());
            
            BigInteger digestInt = new BigInteger(1, digest);
            hashResult = digestInt.toString(16);
            while (hashResult.length() < 32) {
                hashResult = "0"+hashResult;
            }

        } catch (NoSuchAlgorithmException e) {
            return null;
        }

        return hashResult;
    }
}
