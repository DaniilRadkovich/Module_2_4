package com.radkovich.module_2_4.config;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class AppInitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Flyway migration started...");
        FlywayConfig.migrate();
        System.out.println("Flyway migration finished!");
    }
}
