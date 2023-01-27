package com.cos.reflect.controller;

import com.cos.reflect.annotation.RequestMapping;

public class UserController {

    @RequestMapping("/join")
    public String join(){
        return "/";
    }

    @RequestMapping("/login")
    public String login(){
        return "/";
    }

    @RequestMapping("/user")
    public String user(){
        return "/";
    }
}
