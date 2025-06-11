package com.odc.common.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncrptor {

    public static String encryptSha256(String password) {
        try {
            // SHA-256 해시 알고리즘 인스턴스 생성
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // 비밀번호를 바이트 배열로 변환하여 해시
            byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            // 바이트 배열을 16진수 문자열로 변환
            StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
            for (byte b : encodedhash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            // SHA-256 알고리즘을 찾을 수 없는 경우 (거의 발생하지 않음)
            e.printStackTrace();
            return null;
        }
    }
    public static boolean matches(String rawPassword, String encryptedPassword) {
        String encryptedRaw = encryptSha256(rawPassword);
        return encryptedRaw != null && encryptedRaw.equals(encryptedPassword);
    }
}
