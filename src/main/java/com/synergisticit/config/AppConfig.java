package com.synergisticit.config;

import com.synergisticit.domain.AuditorAwareImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.AuditorAware;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Properties;

//@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
//@EnableTransactionManagement
//@PropertySource("database.properties")
@Configuration
public class AppConfig {

    @Autowired
    Environment env;


//    @Bean
//    DriverManagerDataSource dataSource(){
//        DriverManagerDataSource  dataSource = new DriverManagerDataSource();
//        dataSource.setUrl(env.getProperty("url"));
//        dataSource.setUsername(env.getProperty("username"));
//        dataSource.setPassword(env.getProperty("password"));
//        dataSource.setDriverClassName(env.getProperty("driver"));
//
//        return dataSource;
//    }

    @Bean
    public AuditorAware<String> auditAware(){
        return new AuditorAwareImpl();
    }

    @Bean
    InternalResourceViewResolver internalResourceViewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

//    @Bean
//    public LocalSessionFactoryBean sessionFactory(){
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource());
//        sessionFactory.setPackagesToScan("com.synergisticit");
//        return sessionFactory;
//    }

//    @Bean
//    LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
//        emf.setDataSource(dataSource());
//        emf.setPackagesToScan("com.synergisticit.domain");
//        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        Properties jpaProperties = new  Properties();
//        jpaProperties.setProperty("hibernate.hbm2ddl.auto", "update");
//        jpaProperties.setProperty("hibernate.show_SQL", "true");
//        jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//        emf.setJpaProperties(jpaProperties);
//        return emf;
//
//    }

//    @Bean
//    public PlatformTransactionManager transactionManager(){
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
//        return transactionManager;
//    }

}
