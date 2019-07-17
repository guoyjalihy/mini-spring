package com.xiaoye;

import com.xiaoye.starter.MiniApplication;

/**
 * @description: test
 * @author: guoyanjun
 * @date: 2019/6/21 14:57
 */
public class Application {
    public static void main(String[] args) {
        System.out.println("Hello Spring");
        MiniApplication.run(Application.class,args);
    }
}
