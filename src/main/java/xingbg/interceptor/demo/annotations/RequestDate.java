package xingbg.interceptor.demo.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface RequestDate {
    /**
     * 日期格式
     * @return
     */
    String pattern() default "yyyy-MM-dd";

    /**
     * 是否必须
     * @return
     */
    boolean required() default false;
}
