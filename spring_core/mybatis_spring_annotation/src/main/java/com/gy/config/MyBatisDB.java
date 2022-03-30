package com.gy.config;

import com.gy.dao.BookDao;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;

// Mapper扫描器, 替代MapperScannerConfigurer
@MapperScan(value = "com.gy.dao")
@Configuration
@PropertySource("classpath:application.properties")
public class MyBatisDB {
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

    /*@Bean
    public BookDao bookDao() throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
        return sqlSessionTemplate.getMapper(BookDao.class);
    }*/
}
