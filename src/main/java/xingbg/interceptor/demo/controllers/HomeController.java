package xingbg.interceptor.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xingbg.interceptor.demo.annotations.TestClassA;
import xingbg.interceptor.demo.annotations.TestClassB;
import xingbg.interceptor.demo.annotations.TestClassC;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/home")
public class HomeController {

    @Autowired
    private TestClassA testClassA;

    @Autowired
    private TestClassB testClassB;

    @Autowired
    private TestClassC testClassC;


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
