package xingbg.interceptor.demo.annotations.importtest;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import xingbg.interceptor.demo.annotations.TestClassA;

@Import({TestClassA.class,TestBImportSelector.class,TestCImportBeanDefinitionRegistrar.class})
@Configuration
public class ImportConfig {
}
