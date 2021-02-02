package xingbg.interceptor.demo.interceptors;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import xingbg.interceptor.demo.annotations.UnAuth;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * 实现接口或者继承抽象类HandlerInterceptorAdapter
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final Method method = ((HandlerMethod) handler).getMethod();
        if (method.isAnnotationPresent(UnAuth.class)) {
            return true;
        }

        Cookie[] cookies = request.getCookies();
        if (cookies==null || cookies.length==0) {
            redirect(response,401,"未授权");
            return false;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")&& cookie.getValue()!=null && !cookie.getValue().equals("")) {
                return true;
            }
        }
        redirect(response,401,"未授权,请访问/home/login");
        return false;
    }

    private void redirect(HttpServletResponse response, int httpCode, String message) throws IOException {

        response.setStatus(httpCode);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        final PrintWriter writer = response.getWriter();
        writer.write(String.format("{httpCode:%s, message:%s}",httpCode,message));
    }
}
