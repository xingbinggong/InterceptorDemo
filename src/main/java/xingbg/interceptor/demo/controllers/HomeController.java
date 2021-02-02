package xingbg.interceptor.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xingbg.interceptor.demo.annotations.UnAuth;
import xingbg.interceptor.demo.importtest.TestClassA;
import xingbg.interceptor.demo.importtest.TestClassB;
import xingbg.interceptor.demo.importtest.TestClassC;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("home")
public class HomeController {

    @Autowired
    private TestClassA testClassA;

    @Autowired
    private TestClassB testClassB;

    @Autowired
    private TestClassC testClassC;

    @UnAuth
    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("token","dddd");
        cookie.setDomain(request.getServerName());
        cookie.setMaxAge(60*60*12);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "{message:'Login Success.'}";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("token",null);
        cookie.setDomain(request.getServerName());
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "{message:'Logout Success.'}";
    }


    @GetMapping("/index")
    public String index(HttpServletResponse response) {
        Cookie cookie = new Cookie("testname","dddd");
        cookie.setDomain("fiqs.jt2.com");
        cookie.setMaxAge(200);
        cookie.setPath("/");
        response.addCookie(cookie);
        return HomeController.class.getName();
    }

    @GetMapping("/import1")
    public String import1() { return testClassA.getMessage();}

    @GetMapping("/import2")
    public String import2(){ return testClassB.getMessage();}

    @GetMapping("/import3")
    public String import3(){ return testClassC.getMessage(); }

}
