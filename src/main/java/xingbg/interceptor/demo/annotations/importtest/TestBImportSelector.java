package xingbg.interceptor.demo.annotations.importtest;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class TestBImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{"xingbg.interceptor.demo.annotations.TestClassB"};
    }
}
