package com.test.utility;

import java.io.File;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class ApplicationListener {
@EventListener(ApplicationReadyEvent.class)
    public void initializeLogging() {
        try {
            LoggerContext context = (LoggerContext) LogManager.getContext(false);
            File file = new File(Constants.LOG4J_CONFIG_PATH);
            context.setConfigLocation(file.toURI());
            LogManager.getLogger(getClass()).info("Context created on {}", new Date());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}