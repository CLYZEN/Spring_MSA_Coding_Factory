package com.optimagrowth.license;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication // Spring Bootstrap Class
public class LicenseServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LicenseServiceApplication.class, args);
    }


    /*
    * 라이선싱 서비스에 국제화 추가하기
    * 이를 위해 /src/main/resource 에
    * messages_en.properties
    * messages_es.properties
    * messages.properties 추가
    * */

    @Bean
    public LocaleResolver localResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US); // US를 기본 스케일로 설정
        return localeResolver;
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setUseCodeAsDefaultMessage(true); // 메시지가 발견되지 않아도 에러를 던지지 않고 메시지 코드를 반환
        messageSource.setBasename("messages"); // 언어 프로퍼티 파일의 기본 이름을 설정
        return messageSource;
    }
}
