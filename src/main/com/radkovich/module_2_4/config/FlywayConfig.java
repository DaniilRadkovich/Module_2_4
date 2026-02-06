package com.radkovich.module_2_4.config;


import jakarta.servlet.ServletContextListener;
import org.flywaydb.core.Flyway;

public class FlywayConfig implements ServletContextListener {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/crud_files_events?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "admin";

    private FlywayConfig() {
    }

    public static void migrate() {
        Flyway flyway = Flyway.configure()
                .dataSource(DB_URL, DB_USER, DB_PASSWORD)
                .locations("classpath:db/migration")
                .baselineOnMigrate(true)
                .load();

        flyway.migrate();
    }
}

