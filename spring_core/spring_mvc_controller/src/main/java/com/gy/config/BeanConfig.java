package com.gy.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jasypt.util.password.PasswordEncryptor;
import org.jasypt.util.password.rfc2307.RFC2307SMD5PasswordEncryptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@EnableTransactionManagement // 开启事务管理
@MapperScan(value = "com.gy.dao")
@ComponentScan({"com.gy.service"})
@Configuration
@PropertySource("classpath:application.properties")
public class BeanConfig {
    @Value("${datasource.driverClassName}")
    private String driverClassName;
    @Value("${datasource.url}")
    private String url;
    @Value("${datasource.username}")
    private String username;
    @Value("${datasource.password}")
    private String password;

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

    /**
     * 配置MyBatis的SqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(poolDataSource());
        // factoryBean.setConfigLocation(mybatisConfigResource);
        return factoryBean.getObject();
    }

    // 申明事务管理器
    @Bean
    PlatformTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager tm = new DataSourceTransactionManager();
        tm.setDataSource(dataSource);
        return tm;
    }

    @Bean
    PasswordEncryptor passwordEncryptor() {
       return new RFC2307SMD5PasswordEncryptor();
    }
}
