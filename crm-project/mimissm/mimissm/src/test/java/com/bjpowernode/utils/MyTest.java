package com.bjpowernode.utils;

import org.junit.Test;
import utils.MD5Util;

public class MyTest {
    @Test
    public void testMD5(){
        String mi = MD5Util.getMD5("000000");
        System.out.println(mi);
    }
}
