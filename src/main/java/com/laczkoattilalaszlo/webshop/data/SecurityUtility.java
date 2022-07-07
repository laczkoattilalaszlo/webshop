package com.laczkoattilalaszlo.webshop.data;

import org.apache.commons.codec.binary.Hex;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class SecurityUtility {

    public static String hashPassword(String password) {
        char[] passwordChars = password.toCharArray();

        String salt = "ThisIsTheSalt";
        byte[] saltBytes = salt.getBytes();

        int iterations = 10000;
        int keyLength = 512;

        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            PBEKeySpec spec = new PBEKeySpec(passwordChars, saltBytes, iterations, keyLength);
            SecretKey key = skf.generateSecret(spec);
            byte[] hashedBytes = key.getEncoded();
            String hashedString = Hex.encodeHexString(hashedBytes);
            return hashedString;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateRandomHashToken() {
        SecureRandom random = new SecureRandom();
        Base64.Encoder base64Encoder = Base64.getUrlEncoder();
        byte[] bytes = new byte[24];
        random.nextBytes(bytes);
        String token = base64Encoder.encodeToString(bytes);
        return token;
    }

}
