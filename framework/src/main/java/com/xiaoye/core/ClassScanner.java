package com.xiaoye.core;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @description: 类扫描
 * @author: guoyanjun
 * @date: 2019/6/27 14:07
 */
public class ClassScanner {
    public static List<Class<?>> scanClasses(String packageName) throws IOException, ClassNotFoundException {
        List<Class<?>> result = new ArrayList<>();
        String path = packageName.replace(".","/") ;
        //获取类加载器
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources = classLoader.getResources(path);
        while (resources.hasMoreElements()){
            URL url = resources.nextElement();
            if (url.getProtocol().contains("jar")){
                JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                String jarFilePath = jarURLConnection.getJarFile().getName();
                result.addAll(getClassForJar(jarFilePath,path));
            }
        }
        return result;
    }

    private static List<Class<?>> getClassForJar(String jarFilePath, String path) throws IOException, ClassNotFoundException {
        List<Class<?>> result = new ArrayList<>();
        JarFile jarFile = new JarFile(jarFilePath);
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()){
            JarEntry jarEntry = entries.nextElement();
            String entryName = jarEntry.getName();// com/xiaoye/Test.class
            if (entryName.startsWith(path) && entryName.endsWith(".class")){
                String className = entryName.replace("/",".").substring(0,entryName.length()-6);
                result.add(Class.forName(className));
            }
        }
        return result;
    }
}
