package com.kanfa.news.common.util;

import org.junit.Test;

import java.util.regex.Pattern;

/**
 * Created by kanfa on 2018/4/23.
 */
public class RegularVerifyUtils {


    public static Boolean phoneVerify(String phone ){
        String regex = "^1\\d{10}$";
        boolean r = Pattern.matches(regex ,phone);
        return r;
    }

    @Test
    public void  dddd(){
        Boolean aBoolean = phoneVerify("13866669999");
        System.out.println(aBoolean);

    }
}
