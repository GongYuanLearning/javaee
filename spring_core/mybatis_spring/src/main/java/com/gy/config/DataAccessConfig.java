package com.gy.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

//@EnableTransactionManagement
@Configuration
//@ComponentScan(basePackages = {"com.gy.dao"})
@PropertySource("classpath:db.properties")
public class DataAccessConfig {
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

    // 注入类路径下的资源文件mybatis-config.xml
    @Value("classpath:mybatis-config.xml")
    private Resource mybatisConfigResource;

    @Bean
    SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
//        sqlSessionFactoryBean.setConfigLocation(mybatisConfigResource);
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        return sqlSessionFactoryBean;
    }

    // 申明事务管理器
    @Bean
    PlatformTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager tm = new DataSourceTransactionManager();
        tm.setDataSource(dataSource);
        return tm;
    }
}
