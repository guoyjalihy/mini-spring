package com.xiaoye.controller;

import com.xiaoye.web.mvc.Controller;
import com.xiaoye.web.mvc.RequestMapping;
import com.xiaoye.web.mvc.RequestParam;

/**
 * @description: 测试
 * @author: guoyanjun
 * @date: 2019/6/26 14:24
 */
@Controller
public class TestController {
    @RequestMapping("/test")
    public String test(@RequestParam("id")String id){
        System.out.println(id);
        return String.valueOf(id);
    }
}
