package com.xiaoye.web.handler;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @description: 映射处理器
 * @author: guoyanjun
 * @date: 2019/6/28 14:55
 */
public class MappingHandler {
    private String uri;
    private Class<?> controller;
    private String[] args;
    private Method method;
    MappingHandler(String uri,Class<?> controller,String[] args,Method method){
        this.uri = uri;
        this.controller = controller;
        this.args = args;
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void handle(ServletRequest req, ServletResponse res) throws IllegalAccessException, InstantiationException, InvocationTargetException, IOException {
        Object[] params = new Object[args.length];
        for(int i=0;i<args.length; i++){
            params[i] = req.getParameter(args[i]);
        }
        System.out.println(params[0]);
        Object response = method.invoke(controller.newInstance(), params);
        res.getWriter().println(response.toString());
    }

    @Override
    public String toString() {
        return "MappingHandler{" +
                "uri='" + uri + '\'' +
                ", controller=" + controller +
                ", args=" + Arrays.toString(args) +
                ", method=" + method +
                '}';
    }
}
