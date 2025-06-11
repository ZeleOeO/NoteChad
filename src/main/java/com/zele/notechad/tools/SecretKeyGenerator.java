package com.zele.notechad.tools;

import com.zele.notechad.exception.GenericException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public class SecretKeyGenerator {
    public static SecretKey generateKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            return keyGen.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new GenericException(e.getMessage());
        }
    }
}
