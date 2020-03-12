package com.carl.glideutilsdemo.util;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * IO工具
 *
 * @author zou.sq
 */
public class IoUtils {

    /**
     * 关闭流
     *
     * @param stream 可关闭的流
     */
    public static void closeStream(Closeable stream) {
        try {
            if (stream != null) {
                stream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String loadTextFile(File file) throws IOException {
        InputStream inputStream = new FileInputStream(file);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[4096];
        int len;
        while ((len = inputStream.read(bytes)) > 0) {
            byteArrayOutputStream.write(bytes, 0, len);
        }
        String result = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
        closeStream(inputStream);
        closeStream(byteArrayOutputStream);
        return result;
    }
}
