package com.teachmeskills.course_project.coder;

import java.util.Base64;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Class with static methods:
 * login coding(input parameter - "inputLogin");
 * password coding(input parameter - "inputPassword");
 * login decoding(input parameter - "inputDecodeLogin");
 * password decoding(input parameter - "inputDecodePassword");
 * method "addSalt"(input parameter - "input")
 * class contains private constructor which does not allow creating objects of this class.
 */
public class Coder {
    private Coder() {

    }

    public static String codeLogin(String inputLogin) {
        String encodedString = Base64.getEncoder().encodeToString(inputLogin.getBytes());
        return addSalt(encodedString);
    }

    public static String codePassword(String inputPassword) {
        String encodedString = Base64.getEncoder().encodeToString(inputPassword.getBytes());
        return addSalt(encodedString);
    }

    public static String decodeLogin(String inputDecodeLogin) {
        byte[] bytes = Base64.getDecoder().decode(inputDecodeLogin.substring(10));
        return new String(bytes);
    }

    public static String decodePassword(String inputDecodePassword) {
        byte[] bytes = Base64.getDecoder().decode(inputDecodePassword.substring(10));
        return new String(bytes);
    }

    private static String addSalt(String input) {
        String symbols = "abcdefghijklmnopqrstuvwxyz0123456789";

        String salt = new Random().ints(10, 0, symbols.length())
                .mapToObj(symbols::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());

        return salt + input;
    }
}
