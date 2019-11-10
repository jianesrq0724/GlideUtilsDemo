package com.carl.glideutilsdemo.util;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author qiu.d
 */
public class StringUtil {

    private static final int MOBIL_F_TAG = 3;
    private static final int MOBIL_L_TAG = 7;
    private static final int MOBIL_P_TAG_PREFIX = 6;
    private static final int MOBIL_N_TAG_PREFIX = 10;
    private static final String TAG = "StringUtil";

    /**
     * 判断字符串是否为null或者空字符串
     *
     * @param input 输入的字符串
     * @return 如果为null或者空字符串，返回true；否则返回false
     */
    public static boolean isNullOrEmpty(String input) {
        return TextUtils.isEmpty(input) || 0 == input.trim().length();
    }

    public static boolean equals(@NonNull String fristCheck, @NonNull String secondCheck) {
        return TextUtils.equals(fristCheck, secondCheck);
    }

    /**
     * @param color   关键字颜色
     * @param text    文本
     * @param keyword 关键字
     * @return
     */
    public static SpannableString getHighLightKeyWord(Context mContext, int color, String text, String keyword) {
        if (StringUtil.isNullOrEmpty(text)) {
            return null;
        }
        SpannableString s = new SpannableString(text);
        if (StringUtil.isNullOrEmpty(keyword)) {
            return s;
        }
        try {
            Pattern p = Pattern.compile(keyword);
            Matcher m = p.matcher(s);
            while (m.find()) {
                int start = m.start();
                int end = m.end();
                s.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext,
                        color)), start, end,
                        Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            }
        } catch (Exception e) {
            EvtLog.e(TAG, new Throwable("Unknow char style,just change only one word"));
            if (text.contains(keyword)) {
                s.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, color)),
                        text.indexOf(keyword.substring(0, 1)),
                        keyword.length(),
                        Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            }
        }
        return s;
    }

    /**
     * 处理搜索文本的字段
     *
     * @param dots    前缀
     * @param content 内容
     * @param key     关键词
     * @return 处理后的文本
     */
    public static String dealString(String dots, String content, String key) {
        if (StringUtil.isNullOrEmpty(content) || StringUtil.isNullOrEmpty(key)) {
            return "";
        }
        try {
            int keyIndex = content.indexOf(key);
            if (keyIndex <= 0) {
                return content;
            }
            if (content.length() - 1 - keyIndex <= key.length() + 3) {
                if (keyIndex >= 8) {
                    String concats = dots.concat(content.subSequence(keyIndex - 3, content.length() - 1).toString());
                    return concats;
                } else {
                    return content;
                }
            }
            String concat = dots.concat(content.subSequence(keyIndex, content.length() - 1).toString());
            return concat;
        } catch (Exception e) {
            EvtLog.e("error index math");
            return content;
        }
    }


    /**
     * @param color      关键字颜色
     * @param text       文本
     * @param keywordArr 多个关键字
     * @return
     */
    public static SpannableString getHighLightKeyWord(Context mContext, int color, String text, String[] keywordArr) {
        SpannableString s = new SpannableString(text);
        for (String keyword : keywordArr) {
            if (StringUtil.isNullOrEmpty(keyword)) {
                break;
            }
            try {
                Pattern p = Pattern.compile(keyword);
                Matcher m = p.matcher(s);
                while (m.find()) {
                    int start = m.start();
                    int end = m.end();
                    s.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, color)), start, end,
                            Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                }
            } catch (Exception e) {
                EvtLog.e(TAG, new Throwable("Unknow char style,just change only one word"));
                if (text.contains(keyword)) {
                    s.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, color)),
                            text.indexOf(keyword.substring(0, 1)),
                            keyword.length(),
                            Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                }
            }
        }
        return s;
    }

    /**
     * 根据需求截取list数据
     *
     * @param mList        原始数据
     * @param startSubSize 开始位置
     * @param endSubSize   结束位置
     * @param <T>          泛型
     * @return
     */
    public static <T> List<T> subList(List<T> mList, int startSubSize, int endSubSize) {
        if (startSubSize < 0) {
            startSubSize = 0;
        }
        if (endSubSize <= 0) {
            EvtLog.e(TAG, new Throwable("endSubSize is not 0."));
            return mList;
        }
        if (startSubSize > endSubSize) {
            EvtLog.e(TAG, new Throwable("Error start/end size"));
            return mList;
        }
        return mList == null ? mList : (mList.size() >= endSubSize ? mList.subList(startSubSize, endSubSize) : mList);
    }

    /**
     * 处理手机号码
     *
     * @param userMobile 需要处理的手机号码
     * @return String 处理后的手机号码
     */
    public static String getProcessedMobile(String userMobile) {
        String mProcessedDrawMobile = "";
        if (!StringUtil.isNullOrEmpty(userMobile)) {
            EvtLog.d(TAG, userMobile);
            // 判断是否是+86开头的手机号码
            if ('+' == userMobile.charAt(0) && userMobile.length() >= MOBIL_N_TAG_PREFIX) {
                mProcessedDrawMobile = userMobile.substring(0, MOBIL_P_TAG_PREFIX) + "****"
                        + userMobile.substring(MOBIL_N_TAG_PREFIX);
            } else if ('+' != userMobile.charAt(0) && userMobile.length() >= MOBIL_L_TAG) {
                mProcessedDrawMobile = userMobile.substring(0, MOBIL_F_TAG) + "****"
                        + userMobile.substring(MOBIL_L_TAG);
            } else {
                mProcessedDrawMobile = userMobile;
            }
        }
        return mProcessedDrawMobile;
    }

    /**
     * 检查是否是字符串
     *
     * @param input 被检查字符串
     * @return 是否是数字组成的字符串，包括0开头;<br>
     * 如果为空或者空字符串，返回true；比如："011"返回true，"a11"返回false
     */
    public static boolean isNumberString(String input) {
        if (!isNullOrEmpty(input)) {
            return input.matches("[0-9]+");
        }
        return false;
    }

    /**
     * 使用java正则表达式去掉多余的.与0
     *
     * @param str 带检测的字符串
     * @return String 去掉多余的.与0
     */
    public static String filterZeroAndDot(String str) {
        if (!isNullOrEmpty(str) && str.indexOf(".") > 0) {
            // 去掉多余的0
            str = str.replaceAll("0+?$", "");
            // 如最后一位是.则去掉
            str = str.replaceAll("[.]$", "");
        }
        return str;
    }

    public static boolean isMobileNumber(String mobiles) {
        Pattern p = Pattern.compile("^(13|14|15|16|17|18|19)\\d{9}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static boolean isEmail(String email) {
        if (isNullOrEmpty(email)) {
            return false;
        }
        String check = "^(?:[a-z0-9]+[_\\-+\\.]+)*[a-z0-9]+@(?:([a-z0-9]+-?)*[a-z0-9]+\\.)+(?:[a-z]{2,})+$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(email.toLowerCase());
        return matcher.matches();
    }

    /**
     * 验证中文名字
     */
    public static boolean isValidName(String name) {
        Pattern pattern = Pattern.compile("^[\\u4e00-\\u9fa5\\·]{2,10}$");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    /**
     * 隐藏字符串中间部分 用*代替
     *
     * @param number 加密字符串
     * @param start  起始位置
     * @param end    结束位置
     * @return 加密后的字符串
     */
    public static String hideMiddleNumber(String number, int start, int end) {
        if (isNullOrEmpty(number)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            if (i >= start && i <= number.length() - end - 1) {
                sb.append('*');
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 验证身份证
     */
    public static boolean isValidIdCard(String idCard) {
        Pattern pattern = Pattern.compile("^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$");
        Matcher matcher = pattern.matcher(idCard);
        return matcher.matches();
    }
}