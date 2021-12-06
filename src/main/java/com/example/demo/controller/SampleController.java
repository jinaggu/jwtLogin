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
public class SampleController {

    @GetMapping("/login")
    public String login(HttpServletResponse response) {
        Cookie setCookie = new Cookie("name", "name"); // 쿠키 이름을 name으로 생성
        setCookie.setMaxAge(60*60*24); // 기간을 하루로 지정(60초 * 60분 * 24시간)
        response.addCookie(setCookie);
        log.info("success...........");
        log.info("-------------------------");
        return "sample/login";
    }

    /*@GetMapping("/success2")
    public void success(HttpServletRequest req, HttpServletResponse res) {
        log.info("success2...........");
        log.info("-------------------------");
        *//*Cookie[] cookies = req.getCookies();

        // 2) 읽은 쿠기 정보 출력하기
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                log.info("%s=%s\n",
                        cookie.getName(),  // 쿠키 이름
                        cookie.getValue());

            }
        } else {
            log.info("쿠키가 한 개도 없습니다.");
        }*//*
    }*/

    @GetMapping("/service_1")
    public void service_1() {
        log.info("success...........");
        log.info("-------------------------");
    }

    @GetMapping("/service_2")
    public void service_2() {
        log.info("success...........");
        log.info("-------------------------");
    }

}
