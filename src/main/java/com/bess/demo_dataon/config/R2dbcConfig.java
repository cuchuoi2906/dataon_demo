package com.bess.demo_dataon.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;

public class R2dbcConfig {
    @Bean
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get(ConnectionFactoryOptions.builder()
                .option(ConnectionFactoryOptions.DRIVER, "mariadb")
                .option(ConnectionFactoryOptions.PROTOCOL, "tcp")
                .option(ConnectionFactoryOptions.HOST, "192.168.1.201")
                .option(ConnectionFactoryOptions.PORT, 3389)
                .option(ConnectionFactoryOptions.DATABASE, "demo_dataon")
                .option(ConnectionFactoryOptions.USER, "root")
                .option(ConnectionFactoryOptions.PASSWORD, "root")
                .build());
    }
    @Bean
    public R2dbcTransactionManager transactionManager(ConnectionFactory connectionFactory){
        return new R2dbcTransactionManager(connectionFactory);
    }
}
