package com.gy.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
// 导入其他配置
@Import(DataAccessConfig.class)
public class AppConfig {
    /**
     * MapperScannerConfigurer不能跟DataSource配置在同一个类。
     * @return
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        // 指定要扫描@Mapper组件的基础包
        mapperScannerConfigurer.setBasePackage("com.gy.dao");
        mapperScannerConfigurer
                .setSqlSessionFactoryBeanName("sqlSessionFactory");
        return mapperScannerConfigurer;
    }
}
