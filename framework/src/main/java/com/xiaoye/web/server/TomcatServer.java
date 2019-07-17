package com.xiaoye.web.server;

import com.xiaoye.web.servlet.DispatcherServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

/**
 * @description: tomcat
 * @author: guoyanjun
 * @date: 2019/6/21 15:58
 */
public class TomcatServer {
    private Tomcat tomcat;
    private String[] args;

    public TomcatServer(String[] args){
        this.args = args;
    }
    public void startServer() throws LifecycleException {
        tomcat = new Tomcat();
        tomcat.setPort(8899);
        tomcat.start();
        Context context = new StandardContext();
        context.setPath("");
        context.addLifecycleListener(new Tomcat.FixContextListener());

        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        Tomcat.addServlet(context,"dispatcherServlet",dispatcherServlet).setAsyncSupported(true);
        context.addServletMappingDecoded("/","dispatcherServlet");
        tomcat.getHost().addChild(context);


        Thread awaitTread = new Thread("tomcat_await_thread"){
            @Override
            public void run() {
                System.out.printf("tomcat ...");
                TomcatServer.this.tomcat.getServer().await();
            }
        };
        awaitTread.start();
    }
}
