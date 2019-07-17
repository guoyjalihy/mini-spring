package com.xiaoye.starter;

import com.xiaoye.core.ClassScanner;
import com.xiaoye.web.handler.HandlerManager;
import com.xiaoye.web.server.TomcatServer;

import java.util.List;

/**
 * @description: Mini
 * @author: guoyanjun
 * @date: 2019/6/21 15:11
 */
public class MiniApplication {
    public static void run(Class<?> cls,String[] args) {
        System.out.println("Hello Mini-Spring");
        TomcatServer tomcatServer = new TomcatServer(args);
        try {
            tomcatServer.startServer();
//            List<Class<?>> classes = ClassScanner.scanClasses("C:\\Projects_git_private\\mini-spring\\test\\build\\libs\\test-1.0-SNAPSHOT.jar");
            List<Class<?>> classes = ClassScanner.scanClasses(cls.getPackage().getName());
            classes.forEach(clss -> System.out.println(clss.getName()));
            HandlerManager.resolveMappingHandler(classes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
