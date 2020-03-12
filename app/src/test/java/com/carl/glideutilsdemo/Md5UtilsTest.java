package com.carl.glideutilsdemo;

import com.carl.glideutilsdemo.util.Md5Utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class Md5UtilsTest {
    @Test
    public void testMd5() {
        String fileMD5 = Md5Utils.md5("");
        System.out.println(fileMD5);
    }
}