package com.gy.config;

import com.gy.converter.StringToUserConverter;
import com.gy.formatter.UserFormatter;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.number.CurrencyStyleFormatter;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;

import java.nio.charset.StandardCharsets;

@Configuration
// 开启web mvc
@EnableWebMvc
// 扫描哪些注解，扫描控制器注解Controller
@ComponentScan({"com.gy.controller", "com.gy.validator"})
public class MvcConfig implements WebMvcConfigurer {
/*    *//**
     * 配置视图解析器。
     *//*
    @Override
    public void configureViewResolvers(
            ViewResolverRegistry registry) {
        //registry.viewResolver(viewResolver());
        // 在/WEB-INF/classes/view/路径下，找对应以`.jsp`为后缀的文件。
        registry.jsp("/WEB-INF/classes/views/", ".jsp");
    }*/

    @Bean
    BeanNameViewResolver beanNameViewResolver() {
        BeanNameViewResolver viewResolver = new BeanNameViewResolver();
        viewResolver.setOrder(0); // 值越小，优先级越高
        return viewResolver;
    }

    /**
     * 定义视图解析器。这里特指配置jsp解析器。
     *
     * @return
     */
    @Bean
    InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver =
                new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/classes/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setOrder(2);
        return viewResolver;
    }

    /**
     * 我们配置dispatcher servlet处理'/'，所有的请求都会通过它处理。
     * 对于静态资源的请求，不需要dispatcher servlet；
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // `classpath:`表示在类路径下查找
        // **代表下面的子路径或者文件
        // /img/1.jpg
        registry.addResourceHandler("/js/**", "/css/**", "/images/**")
                .addResourceLocations("classpath:/static/js/",
                        "classpath:/static/css/", "classpath:/static/images/",
                        "classpath:/public/images/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // /login -> login
        // 如果有控制器对/login进行处理，该设置无效
        registry.addViewController("/login").setViewName("login");
    }

    @Bean
    public UserFormatter userFormatter() {
        return new UserFormatter();
    }

    /**
     * 添加类型转换器
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 添加String->User的类型转换器
        // registry.addConverter(new StringToUserConverter());
        // CurrencyStyleFormatter
        registry.addFormatter(new CurrencyStyleFormatter());
        registry.addFormatter(userFormatter());
    }
    /**
     * 不配置MessageSource和LocalValidatorFactoryBean，
     * 默认错误消息文件位置：classpath:ValidationMessages.properties
     * 一定要用messageSource这个名字？
     * An application context delegates the message resolution to a bean with the exact name messageSource.
     *
     * @return
     */
    @Bean
    MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("classpath:errorMessages");
        ms.setDefaultEncoding(StandardCharsets.UTF_8.name()); // utf-8
        ms.setCacheSeconds(20);
        return ms;
    }

    //if we had already extended the WebMvcConfigurerAdapter, to avoid having the custom validator ignored,
    // we'd have to set the validator by overriding the getValidator() method from the parent class.
    // 必须使用override来覆盖getValidator，要不然自定义消息不起作用
    //custom name messages in a properties
    @Bean
    @Override
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        // 配置验证消息源
        //bean.setValidationMessageSource(messageSource());
        //bean.setProviderClass(HibernateValidator.class);
        return bean;
    }
}
