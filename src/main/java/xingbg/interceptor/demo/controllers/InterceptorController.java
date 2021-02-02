package xingbg.interceptor.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xingbg.interceptor.demo.annotations.RequestDate;

import java.time.LocalDate;

@RestController
@RequestMapping("interceptor")
public class InterceptorController {

    @RequestMapping("/errrequestdate")
    public String errRequestDate(LocalDate date) {
        return date.toString();
    }

    @RequestMapping("/requestdate")
    public String requestDate(@RequestDate LocalDate date) {
        return date.toString();
    }
}
