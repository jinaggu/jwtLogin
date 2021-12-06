package com.example.demo.controller;

import com.example.demo.util.JWTUtill;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@RestController
@Log4j2
@RequestMapping("/process/")
public class LoginController {

    @Autowired
    public JWTUtill jwtUtill;
    String email = "";

    @PostMapping(value = "/jwtLogin")
    public ResponseEntity<String> login(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String email = req.getParameter("email");
        log.info("email : " + email);
        log.info("jwtLogin Controller ---------");
        String token = jwtUtill.generateToken(email);
        log.info("token : " + token);
        Cookie cookie = new Cookie("token!!!!!!", token);
        res.addCookie(cookie);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @GetMapping(value = "/verification")
    public Object verification(HttpServletRequest req, HttpServletResponse res) throws Exception {
        log.info("REQUESTURI: " + req.getRequestURI());

        boolean checkHeader = checkAuthHeader(req);


        if (checkHeader) {
            log.info("success ok !!");
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            /*res.setStatus(HttpServletResponse.SC_FORBIDDEN);
            // json 리턴 및 한글깨짐 수정.
            res.setContentType("application/json;charset=utf-8");
            JSONObject json = new JSONObject();

            String message = "FAIL CHECK API TOKEN";
            json.put("code","403");
            json.put("message", message);

            PrintWriter out = res.getWriter();
            out.print(json);*/
            return new ResponseEntity<>(false, HttpStatus.OK);
        }

    }

    private boolean checkAuthHeader(HttpServletRequest request) {
        boolean checkResult = false;
        String authHeader = request.getHeader("Authorization");

        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            log.info("Authorization exist : " + authHeader);

            try {
                String email = jwtUtill.validateAndExtract(authHeader.substring(7));
                this.email = email;
                log.info("validate result: " + email);
                checkResult = email.length() > 0;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return checkResult;
    }

}
