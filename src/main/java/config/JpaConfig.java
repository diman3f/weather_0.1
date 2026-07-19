package config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
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

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        return new HikariDataSource(config);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean localEntityManagerFactoryBean() {
        final LocalContainerEntityManagerFactoryBean localFactoryManager = new LocalContainerEntityManagerFactoryBean();
        localFactoryManager.setDataSource(hikariDataSource());
        localFactoryManager.setPackagesToScan("entity");

        JpaVendorAdapter hibernateAdapter = new HibernateJpaVendorAdapter();
        localFactoryManager.setJpaVendorAdapter(hibernateAdapter);
        localFactoryManager.setJpaProperties(addantionalProperties());
        return localFactoryManager;
    }

    public Properties addantionalProperties() {
        final Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "none");
        return properties;
        //  hibernate.show_sql=true
        // hibernate.format_sql=true
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
}
