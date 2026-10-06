package com.example.weather.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class JpaConfig {

    @Bean
    public DataSource hikariDataSource() { //todo 01.07
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        config.setUsername("postgres");
        config.setPassword("admin"); //todo 04.07 эти данные необходимо вынести во внешний источник
        config.setDriverClassName("org.postgresql.Driver");
        config.setSchema("test_schema");

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        return new HikariDataSource(config);
    }


    @Bean
    public Flyway flyway() {
        final FluentConfiguration configure = Flyway.configure();
        configure.dataSource(hikariDataSource()).load();
        configure.schemas("public");
        return new Flyway(configure);
    }

//
    @Bean
    public LocalContainerEntityManagerFactoryBean localEntityManagerFactoryBean() {
        final LocalContainerEntityManagerFactoryBean localFactoryManager = new LocalContainerEntityManagerFactoryBean();
//        flyway().migrate();
        localFactoryManager.setDataSource(hikariDataSource());
        localFactoryManager.setPackagesToScan("com/example/weather/entity");
        JpaVendorAdapter hibernateAdapter = new HibernateJpaVendorAdapter();
        localFactoryManager.setJpaVendorAdapter(hibernateAdapter);
        localFactoryManager.setJpaProperties(addantionalProperties());
        return localFactoryManager;
    }


    public Properties addantionalProperties() {
        final Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        return properties;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor postProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public PlatformTransactionManager jpaTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(localEntityManagerFactoryBean().getObject());
        return transactionManager;
    }
//TEST
//todo пока не знаю нужно ли копировать код в тест, тоже дублирование. Так копировать и помечать тест тоже плохо нужно разбираться

//    @Bean
//    public Flyway flywayTest() {
//        final FluentConfiguration configure = Flyway.configure();
//        configure.dataSource(hikariDataSource()).load();
//        configure.schemas("test_schema");
//        return new Flyway(configure);
//    }

//    @Bean
//    public LocalContainerEntityManagerFactoryBean localEntityManagerFactoryBeanTest() {
//        final LocalContainerEntityManagerFactoryBean localFactoryManager = new LocalContainerEntityManagerFactoryBean();
//        flywayTest().migrate();
//        localFactoryManager.setDataSource(hikariDataSource());
//        localFactoryManager.setPackagesToScan("entity");
//        JpaVendorAdapter hibernateAdapter = new HibernateJpaVendorAdapter();
//        localFactoryManager.setJpaVendorAdapter(hibernateAdapter);
//        localFactoryManager.setJpaProperties(addantionalProperties());
//        return localFactoryManager;
//    }


}
