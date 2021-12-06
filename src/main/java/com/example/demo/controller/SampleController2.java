package com.example.demo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Log4j2
@RequestMapping("/sample/")
public class SampleController2 {

    @GetMapping("/success3")
    public String success(HttpServletRequest req, HttpServletResponse res) {
        log.info("success2...........");
        log.info("-------------------------");
        Cookie[] getCookie = req.getCookies();
        if(getCookie!=null){
            for (Cookie c : getCookie) {
                String name = c.getName(); // 쿠키 이름 가져오기
                String value = c.getValue(); // 쿠키 값 가져오기
                if (name.equals("token")) {
                    log.info(value);
                }
            }
        }
        return "sample/success3";

    }

}
