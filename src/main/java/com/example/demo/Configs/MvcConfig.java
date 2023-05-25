package com.example.demo.Configs;

import org.springframework.context.annotation.Configuration; // Конфигурационный класс обьединяет все классы в одно springboot-приложение
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry; // Помогает в управлении основным контроллерами
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer; // Позволяет настраивать конфигурациионный класс с целью управления контроллерами

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    }

}
