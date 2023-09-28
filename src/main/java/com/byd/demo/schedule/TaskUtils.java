package com.byd.demo.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Cron表达式参数分别表示：
 *
 * 秒（0~59） 例如0/5表示每5秒
 * 分（0~59）
 * 时（0~23）
 * 日（0~31）的某天，需计算
 * 月（0~11）
 * 周几（ 可填1-7 或 SUN/MON/TUE/WED/THU/FRI/SAT）
 */
@Component
public class TaskUtils {
    //定时五秒 @Scheduled(fixedRate = 5000)
    public static void doTask(){
        System.out.println("==============do task==========");
    }
}
