package com.grsu.teacherassistant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@SpringBootApplication
public class TeacherAssistantBootApplication {

    public static void main(String[] args) {
        // TODO: добавить шедулер и джобу для изменения статуса группы
        SpringApplication.run(TeacherAssistantBootApplication.class, args);
    }
}
