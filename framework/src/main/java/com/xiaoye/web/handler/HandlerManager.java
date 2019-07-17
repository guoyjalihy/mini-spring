package com.xiaoye.web.handler;

import com.xiaoye.web.mvc.Controller;
import com.xiaoye.web.mvc.RequestMapping;
import com.xiaoye.web.mvc.RequestParam;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 解析Controller注解的类并保存
 * @author: guoyanjun
 * @date: 2019/6/28 14:57
 */
public class HandlerManager {

    public static List<MappingHandler> mappingHandlerList  = new ArrayList<>();

    public static void resolveMappingHandler(List<Class<?>> clsList){
        clsList.forEach(cls -> {
            if (cls.isAnnotationPresent(Controller.class)){
                parseHandlerFromController(cls);
            }
        });
    }

    private static void parseHandlerFromController(Class<?> cls) {
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods){
            if (!method.isAnnotationPresent(RequestMapping.class)){
                continue;
            }
            String uri =method.getAnnotation(RequestMapping.class).value();
            Parameter[] parameters = method.getParameters();
            List<String> argList = new ArrayList<>();
            for (Parameter parameter : parameters){
                if (parameter.isAnnotationPresent(RequestParam.class)){
                    argList.add(parameter.getAnnotation(RequestParam.class).value());
                }
            }
            String[] args = argList.toArray(new String[argList.size()]);
            MappingHandler mappingHandler = new MappingHandler(uri,cls,args,method);
            mappingHandlerList.add(mappingHandler);
        }
        System.out.println("mapping:" + mappingHandlerList.toString());
    }
}
