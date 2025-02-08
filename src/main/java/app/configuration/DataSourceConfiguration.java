package app.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {
    private final Environment env;

    @Autowired
    public DataSourceConfiguration(Environment env){
        this.env = env;
    }

    @Bean
    @Primary
    public DataSource getDataSource(){
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(env.getRequiredProperty("spring.datasource.driver-class-name"));
        dataSourceBuilder.url(env.getRequiredProperty("spring.datasource.url"));
        dataSourceBuilder.username(env.getRequiredProperty("spring.datasource.username"));
        dataSourceBuilder.password(env.getRequiredProperty("spring.datasource.password"));

        return dataSourceBuilder.build();
    }
}
