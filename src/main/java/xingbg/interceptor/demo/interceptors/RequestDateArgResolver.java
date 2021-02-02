package xingbg.interceptor.demo.interceptors;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import xingbg.interceptor.demo.annotations.RequestDate;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Parameter;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * LocalDate 和 LocalDateTime 参数转换器
 * LocalDate 和 LocalDateTime 默认不支持get方式传参
 */
public class RequestDateArgResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> parameterType = methodParameter.getParameterType();
        List<Class> supportTypes = Arrays.asList(
                LocalDate.class,
                LocalDateTime.class
        );
        return methodParameter.hasParameterAnnotation(RequestDate.class) && supportTypes.contains(parameterType);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        Parameter parameter = methodParameter.getParameter();
        String parameterName = parameter.getName();
        RequestDate requestDate = parameter.getAnnotation(RequestDate.class);

        ServletWebRequest servletWebRequest = (ServletWebRequest)nativeWebRequest;
        String parameterValue = servletWebRequest.getParameter(parameterName);

        if (parameterValue==null || "".equals(parameterValue)) {
            if (requestDate.required()) {
                throw new InvalidParameterException("时间日期参数必填。");
            }
            return null;
        }

        if (methodParameter.getParameterType().equals(LocalDate.class)) {
            return LocalDate.parse(parameterValue, DateTimeFormatter.ofPattern(requestDate.pattern()));
        }
        if (methodParameter.getParameterType().equals(LocalDateTime.class)) {
            return LocalDateTime.parse(parameterName,DateTimeFormatter.ofPattern(requestDate.pattern()));
        }

        return null;
    }
}
