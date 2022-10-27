package com.gl.employee.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncryptor {

    public static void main(String[] args) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String plainText = "welcome";

        String encodedPassword = "$2a$10$iXyto2z/9bZFHbTKBbPx/eogGlz1.0BNjdomNWnZsdf6OUrtKVTsqx"
                //passwordEncoder.encode(plainText)
                ;

        System.out.println(encodedPassword);

        System.out.println(passwordEncoder.matches(plainText, encodedPassword));
    }
}
