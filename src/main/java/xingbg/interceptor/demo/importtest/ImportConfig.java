package xingbg.interceptor.demo.importtest;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({TestClassA.class, TestBImportSelector.class, TestCImportBeanDefinitionRegistrar.class})
@Configuration
public class ImportConfig {
}
