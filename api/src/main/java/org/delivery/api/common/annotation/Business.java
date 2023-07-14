package org.delivery.api.common.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 직렬화와 역직렬화에 대한 Converter 과
// Member 도메인에 대한 MemberSession 을   분리적용하는 Business 로 분리
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Service
public @interface Business {
    @AliasFor(annotation = Service.class)
    String value() default "";
}