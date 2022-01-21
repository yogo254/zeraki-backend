package com.zeraki.zerakibackend.app.auth;

import java.util.Random;

public class RefreshTokenGenerator {

    public static String generateToken() {
        String randomString = "";
        final String alphabet = "0123456789abcdefghijkmnopqrstuvwxyz";
        final int N = alphabet.length();

        Random r = new Random();

        for (int i = 0; i < 50; i++) {
            randomString += String.valueOf(alphabet.charAt(r.nextInt(N)));
        }

        return randomString;
    }

}
