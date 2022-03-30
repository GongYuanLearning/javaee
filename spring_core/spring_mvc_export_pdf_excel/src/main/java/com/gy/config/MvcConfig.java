package com.gy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;

@Configuration
// 开启web mvc
@EnableWebMvc
// 扫描哪些注解，扫描控制器注解Controller
@ComponentScan({"com.gy.controller", "com.gy.view"})
public class MvcConfig implements WebMvcConfigurer {
/*    *//**
     * 配置视图解析器。
     * @param registry
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
        viewResolver.setOrder(0);
        return viewResolver;
    }
/*
    @Bean
    ResourceBundleViewResolver resourceBundleViewResolver() {
        ResourceBundleViewResolver viewResolver = new ResourceBundleViewResolver();
        viewResolver.setBasename("views"); // 默认就是views
        viewResolver.setOrder(1);
        return viewResolver;
    }*/

    // 默认的视图解析器: BeanNameViewResolver
    /*@Bean
    InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/classes/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setOrder(2);
        return viewResolver;
    }*/

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
}
