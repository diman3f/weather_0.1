package config;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.Location;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlyWayConfig {

    @Bean
public Flyway flyway(DataSource dataSource){
        final FluentConfiguration configure = Flyway.configure();
        configure.dataSource(dataSource).load();
        configure.schemas("public");
        return new Flyway(configure);
    }
}
