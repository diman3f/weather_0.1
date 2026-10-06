package com.example.weather.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateConfig {

//    @Bean
//    public DataSource hikariDataSource() { //todo 01.07
//        HikariConfig config = new HikariConfig();
//        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
//        config.setUsername("postgres");
//        config.setPassword("admin"); //todo 04.07 эти данные необходимо вынести во внешний источник
//        config.setDriverClassName("org.postgresql.Driver");
//
//        config.addDataSourceProperty("cachePrepStmts", "true");
//        config.addDataSourceProperty("prepStmtCacheSize", "250");
//        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
//
//        return new HikariDataSource(config);
//
//    }

//    @Bean
//    public LocalSessionFactoryBean localSessionFactoryBean(DataSource dataSource) {
//
//        final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource);
//        sessionFactory.setAnnotatedClasses(User.class);
//        //todo 01.07 усложнение лишнее по возможности перевести на аннотацию
//        try {
//            ClassPathResource resource = new ClassPathResource("hibernate.properties");
//            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
//            sessionFactory.setHibernateProperties(properties);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return sessionFactory;
//    }
}
