package com.bear.Test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author LiuShanshan
 * @version V1.0
 * @Description
 */
public class TestController {
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("123456"));
    }
}
