package com.carl.glideutilsdemo.util;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

/**
 * @author zou.sq
 * 文件帮助类
 */
public class FileUtil {

    /**
     * 建立一个MIME类型与文件后缀名的匹配表
     */
    private static final String[][] MIME_MAP_TABLE = {
            //{后缀名，    MIME类型}
            {"3gp", "video/3gpp"},
            {"apk", "application/vnd.android.package-archive"},
            {"asf", "video/x-ms-asf"},
            {"avi", "video/x-msvideo"},
            {"bin", "application/octet-stream"},
            {"bmp", "image/bmp"},
            {"c", "text/plain"},
            {"class", "application/octet-stream"},
            {"conf", "text/plain"},
            {"cpp", "text/plain"},
            {"doc", "application/msword"},
            {"exe", "application/octet-stream"},
            {"gif", "image/gif"},
            {"gtar", "application/x-gtar"},
            {"gz", "application/x-gzip"},
            {"h", "text/plain"},
            {"htm", "text/html"},
            {"html", "text/html"},
            {"jar", "application/java-archive"},
            {"java", "text/plain"},
            {"jpeg", "image/jpeg"},
            {"jpg", "image/jpeg"},
            {"js", "application/x-javascript"},
            {"log", "text/plain"},
            {"m3u", "audio/x-mpegurl"},
            {"m4a", "audio/mp4a-latm"},
            {"m4b", "audio/mp4a-latm"},
            {"m4p", "audio/mp4a-latm"},
            {"m4u", "video/vnd.mpegurl"},
            {"m4v", "video/x-m4v"},
            {"mov", "video/quicktime"},
            {"mp2", "audio/x-mpeg"},
            {"mp3", "audio/x-mpeg"},
            {"mp4", "video/mp4"},
            {"mpc", "application/vnd.mpohun.certificate"},
            {"mpe", "video/mpeg"},
            {"mpeg", "video/mpeg"},
            {"mpg", "video/mpeg"},
            {"mpg4", "video/mp4"},
            {"mpga", "audio/mpeg"},
            {"msg", "application/vnd.ms-outlook"},
            {"ogg", "audio/ogg"},
            {"pdf", "application/pdf"},
            {"png", "image/png"},
            {"pps", "application/vnd.ms-powerpoint"},
            {"ppt", "application/vnd.ms-powerpoint"},
            {"prop", "text/plain"},
            {"rar", "application/x-rar-compressed"},
            {"rc", "text/plain"},
            {"rmvb", "audio/x-pn-realaudio"},
            {"rtf", "application/rtf"},
            {"sh", "text/plain"},
            {"tar", "application/x-tar"},
            {"tgz", "application/x-compressed"},
            {"txt", "text/plain"},
            {"wav", "audio/x-wav"},
            {"wma", "audio/x-ms-wma"},
            {"wmv", "audio/x-ms-wmv"},
            {"wps", "application/vnd.ms-works"},
            //{".xml",    "text/xml"},
            {"xml", "text/plain"},
            {"z", "application/x-compress"},
            {"zip", "application/zip"},
            {"", "*/*"}
    };

    public static String getImageSourceFromDrawable(Context context, String sourceId) {
        return "android.resource://" + context.getPackageName() + "/mipmap/" + sourceId;
    }


    public static boolean rename(File file, File dest) {
        if (null == file || !file.exists() || null == dest) {
            return false;
        }
        return file.renameTo(dest);
    }

    public static void delete(String filePath) {
        if (StringUtil.isNullOrEmpty(filePath)) {
            return;
        }
        delete(new File(filePath));
    }

    public static void delete(File file) {
        if (file == null || !file.exists() || file.isDirectory()) {
            return;
        }
        if (file.delete()) {
            EvtLog.d("FileUtil", "delete fail");
        }
    }

    /**
     * 递归删除目录,保留文件夹
     *
     * @param dir 文件路径
     */
    public static void deleteDirRecursive(File dir, boolean deleteSelf) {
        if (dir == null || !dir.exists() || !dir.isDirectory()) {
            return;
        }
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        for (File f : files) {
            if (f.isFile()) {
                f.delete();
            } else {
                deleteDirRecursive(f, deleteSelf);
            }
        }
        if (deleteSelf) {
            dir.delete();
        }
    }

    /**
     * 判断SD卡是否已经准备好
     *
     * @return 是否有SDCARD
     */
    public static boolean isSDCardReady() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public static boolean isAMR(String type) {
        return !TextUtils.isEmpty(type) && "amr".equalsIgnoreCase(type);
    }

    public static String getFileType(String name) {
        String fileType = "";
        try {
            fileType = name.substring(name.lastIndexOf(".") + 1, name.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileType;
    }

    /**
     * 获取文件后缀
     *
     * @param file 文件
     * @return 文件后缀名
     */
    public static String getMineType(File file) {
        if (null == file) {
            return "";
        }
        String fName = file.getName();
        return fName.substring(fName.lastIndexOf(".") + 1, fName.length()).toLowerCase(Locale.getDefault());
    }

    /**
     * 根据文件后缀名获得对应的MIME类型。
     *
     * @param file 文件
     * @return 文件后缀名映射系统mine
     */
    public static String getMappingType(File file) {
        String type = "*/*";
        String fName = file.getName();
        //获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fName.lastIndexOf(".");
        if (dotIndex < 0) {
            return type;
        }
        /* 获取文件的后缀名 */
        String end = getMineType(file);
        if (StringUtil.equals(end, "")) {
            return type;
        }
        //在MIME和文件类型的匹配表中找到对应的MIME类型。
        for (String[] aMimeMapTable : MIME_MAP_TABLE) {
            if (end.equals(aMimeMapTable[0])) {
                type = aMimeMapTable[1];
            }
        }
        return type;
    }


    public static void copyFile(File sourceFile, File targetFile) throws IOException {
        // 新建文件输入流并对它进行缓冲
        FileInputStream input = new FileInputStream(sourceFile);
        BufferedInputStream inBuff = new BufferedInputStream(input);

        // 新建文件输出流并对它进行缓冲
        FileOutputStream output = new FileOutputStream(targetFile);
        BufferedOutputStream outBuff = new BufferedOutputStream(output);

        // 缓冲数组
        byte[] b = new byte[1024 * 5];
        int len;
        while ((len = inBuff.read(b)) != -1) {
            outBuff.write(b, 0, len);
        }
        // 刷新此缓冲的输出流
        outBuff.flush();

        IoUtils.closeStream(inBuff);
        IoUtils.closeStream(outBuff);
        IoUtils.closeStream(output);
        IoUtils.closeStream(input);
    }
}
