package com.lsk.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author:${六月的雨}
 * @Date:2020/4/13 09:39
 * @Description:ssm com.lsk.utils 加密工具类
 */
public class BCryptPasswordEncoderUtils {
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public static String encoderPassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) {
        String pwd = encoderPassword("123456");
        //$2a$10$jLmGhGxILWVgdNgY.wYLC.c/PCesRKcEho/6fpIAS2yhgiTTc9XP6
        //$2a$10$ibXTEMt4Hcxlbxpax2W7Fut6gJ6fbuVc3Y2eap5TZ9m4EGqcBL5uG
        System.out.println(pwd);
    }
}
