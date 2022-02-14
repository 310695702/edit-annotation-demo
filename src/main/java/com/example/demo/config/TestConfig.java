package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

/**
 * @author: HanYuXing
 * @date: 2022-01-24 16:50
 **/
@Configuration
public class TestConfig {

    @javax.annotation.Resource
    private DataSource testDataSource;

    @Value("classpath:test.sql")
    private Resource schemaScript;

    @Bean
    public DataSourceInitializer initDataSourceInitializer() {
        System.err.println("--------------testDataSource-----------:" + testDataSource);
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(testDataSource);
        initializer.setDatabasePopulator(databasePopulator());
        return initializer;
    }

    private DatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(schemaScript);
        return populator;
    }
}
