package com.xiaoye.web.servlet;

import com.xiaoye.web.handler.HandlerManager;
import com.xiaoye.web.handler.MappingHandler;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @description: 分发器
 * @author: guoyanjun98
 * @date: 2019/6/24 11:13
 */
public class DispatcherServlet implements Servlet {

    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

        String uri = ((HttpServletRequest)req).getRequestURI();
        for(MappingHandler mappingHandler :HandlerManager.mappingHandlerList){
            if(mappingHandler.getUri().equals(uri)){
                try {
                    mappingHandler.handle(req,res);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
