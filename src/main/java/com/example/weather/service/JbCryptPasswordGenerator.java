package com.example.weather.service;

import org.mindrot.jbcrypt.BCrypt;

public class JbCryptPasswordGenerator implements PasswordEncoder {
    private static final int WORK_LOAD = 10;

    @Override
    public String handleHashPassword(String password) {
        final String gensalt = BCrypt.gensalt(WORK_LOAD);
        return BCrypt.hashpw(password, gensalt);
    }

    @Override
    public boolean checkPassword(String passwordPlaintText, String passwordHashed) {

        if (null == passwordHashed || !passwordHashed.startsWith("$2a$")) {
            throw new java.lang.IllegalArgumentException(" Предоставлен недопустимый хеш для сравнения ");
        }
        return BCrypt.checkpw(passwordPlaintText, passwordHashed);
    }
}
