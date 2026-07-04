package config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.orm.jpa.hibernate.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class HibernateConfig {

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
    public LocalSessionFactoryBean localSessionFactoryBean(DataSource dataSource) {

        final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setAnnotatedClasses(User.class);
        //todo 01.07 усложнение лишнее по возможности перевести на аннотацию
        try {
            ClassPathResource resource = new ClassPathResource("hibernate.properties");
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            sessionFactory.setHibernateProperties(properties);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sessionFactory;
    }
}
