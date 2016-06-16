package com.zhuyx.mytraining.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by zhuyingxin on 2016/6/16.
 * email : rixtdqqq_2015@163.com
 */
public class ThreadUtil {

    private static final ExecutorService pool;

    private static HashMap<String, ArrayList<Future<?>>> instances = new HashMap<>();

    static {
        pool = Executors.newCachedThreadPool();
    }

    public static void excute(Runnable command) {
        pool.execute(command);
    }

    public static <T> Future<T> submit(Callable<T> task) {
        return pool.submit(task);
    }

    public static Future<?> submit(Runnable task) {
        return pool.submit(task);
    }

    public static <T> Future<T> submit(Runnable task, T result) {
        return pool.submit(task, result);
    }

    public static void putTask(String name, Future<?> task) {
        if (instances.containsKey(name)) {
            instances.get(name).add(task);
        } else {
            ArrayList<Future<?>> list = new ArrayList<>();
            list.add(task);
            instances.put(name, list);
        }
    }

    public static ArrayList<Future<?>> getTask(String name) {
        ArrayList<Future<?>> list = null;
        if (instances.containsKey(name)) {
            list = instances.get(name);
            instances.remove(name);
        }
        return list;
    }

    public static void cancelTask(String name) {
        ArrayList<Future<?>> list = getTask(name);
        if (null != list) {
            for (Future<?> task : list) {
                if (null != task && !task.isCancelled()) {
                    task.cancel(true);
                }
            }
        }
    }

}
