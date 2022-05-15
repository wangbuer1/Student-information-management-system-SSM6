package com.martian.common.util;

import org.apache.commons.lang.RandomStringUtils;

import java.util.Random;
import java.util.UUID;

/**
 * 随机数工具类
 */
public class RandomUtils {

    /**
     * 获取指定范围内的随机数
     * @param min 最小值
     * @param max 最大值
     * @return
     */
    public static int randomIntegerByMinAndMax(int min, int max) {
        if(min == 0) {
            max ++;
        }
        Random random = new Random();
        return random.nextInt(max) % (max - min + 1) + min;
    }

    /**
     * 随机大小写字母+数字
     * @param length 生成长度
     */
    public static String randomAlphanumeric(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    /**
     * 获取UUID
     */
    public static String getUUid() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-", "").toLowerCase();
    }

    /**
     * 生成16位随机数
     * @return
     */
    public static String getRandomNumber() {
        /**
         * 生成规则
         * 日期(6位161122)+时间(时间毫秒5位)+5位随机数
         */
        int randomNum = (int) (Math.random() * 90000) + 10000;
        StringBuilder num = new StringBuilder();
        num.append(DateUtils.formatDate("yyMMdd")).append(DateUtils.getSecond()).append(randomNum);
        return num.toString();
    }

    public static void main(String[] args) {
        System.out.println(getUUid().length());
    }
}
