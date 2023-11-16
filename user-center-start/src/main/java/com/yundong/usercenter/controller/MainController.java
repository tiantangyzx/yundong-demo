package com.yundong.usercenter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 监控
 * 用于监控应用状态是否正常
 */
@Controller
public class MainController {

    @RequestMapping(value = "/checkpreload.htm")
    public @ResponseBody String checkPreload() {
        return "success";
    }
}
