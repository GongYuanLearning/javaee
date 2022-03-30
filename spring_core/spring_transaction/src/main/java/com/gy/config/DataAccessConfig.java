package com.gy.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

//@EnableTransactionManagement
//@Configuration
//@ComponentScan(basePackages = {"com.gy.dao"})
//@PropertySource("classpath:db.properties")
public class DataAccessConfig {
    @Value("${datasource.driverClassName}")
    private String driverClassName;
    @Value("${datasource.url}")
    private String url;
    @Value("${datasource.username}")
    private String username;
    @Value("${datasource.password}")
    private String password;
    @Value("test")
    private String test;

    // 内嵌数据源
//    @Bean
//    @Profile("development")
//    public DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder()
//                .setType(EmbeddedDatabaseType.H2)
//                .addScript("classpath:schema.sql")
//                .addScript("classpath:test-data.sql").build();
//    }

    /**
     * DriverManagerDataSource: 每次请求都返回新连接
     * SimpleDriverDataSource：
     * SingleConnectionDataSource：每次请求都返回同一个连接，相当于只有一个连接的连接池
     *
     * @return
     */
//    @Bean
//    public DataSource jdbcDataSource() {
//        DriverManagerDataSource ds = new DriverManagerDataSource();
//        ds.setDriverClassName(driverClassName);
//        ds.setUrl(url);
//        ds.setUsername(username);
//        ds.setPassword(password);
//        return ds;
//    }

    /**
     * 基于连接池的数据源：DBCP, c3p0,
     *
     * @return
     */
    @Bean
    public DataSource poolDataSource() {
        BasicDataSource ds = new BasicDataSource();
        // 下面4个setter是必须的
        ds.setDriverClassName(driverClassName);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        // 以下的setter是连接池相关的，可选
        ds.setInitialSize(5);
        ds.setMaxTotal(10);
        ds.setMaxIdle(6);
        return ds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        return namedParameterJdbcTemplate;
    }

}
