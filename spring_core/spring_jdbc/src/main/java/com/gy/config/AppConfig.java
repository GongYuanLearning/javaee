package com.gy.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@ComponentScan("com.gy.dao")
@PropertySource("classpath:db.properties")
public class AppConfig {
    @Value("${datasource.driverClassName}")
    private String driverClassName; // 数据库驱动类名
    @Value("${datasource.url}")
    private String url;  // 数据库连接地址
    @Value("${datasource.username}")
    private String username; // 数据库连接用户名
    @Value("${datasource.password}")
    private String password; // 数据库连接密码


    /**
     * DriverManagerDataSource: 每次请求都返回新连接
     * SimpleDriverDataSource：
     * SingleConnectionDataSource：每次请求都返回同一个连接，相当于只有一个连接的连接池
     *
     * @return
     */
    @Bean
    public DataSource dataSource() throws IOException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        /*Properties properties = new Properties();
        properties.load((new ClassPathResource("db.properties")).getInputStream());

        String driverClassName = properties.getProperty("datasource.driverClassName");
        String url = properties.getProperty("datasource.url");
        String username = properties.getProperty("datasource.username");
        String password = properties.getProperty("datasource.password");*/

        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }


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
     * 配置JDBCTemplate组件。
     * @param dataSource
     * @return
     */
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }

    /**
     * 配置NamedParameterJdbcTemplate，这个的好处在于可以给sql的参数给名称。
     * @param dataSource
     * @return
     */
    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        return namedParameterJdbcTemplate;
    }
}
