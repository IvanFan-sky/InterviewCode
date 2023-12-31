package com.fanlian.interviewcode.common;

/**
 * @Author FanLian
 * @Create 2023-12-11 18:48
 * @Version 1.0
 * @Description 线程池常量类
 */
public class ThreadPoolConstant {
    /**
     * 核心线程数量
     */
    public static final int CORE_THREAD_NUM = 10;

    /**
     * 最大线程数量
     */
    public static final int MAX_THREAD_NUM = 15;

    /**
     * 非核心线程存活时间
     */
    public static final long KEEP_ALIVE_TIME_SECONDS = 10L;

    /**
     * 任务队列长度
     */
    public static final int QUEUE_LENGTH = 20;

    /**
     * 线程超时时间
     */
    public static final long TIME_OUT = 70;

}
 