package de.quastenflossler.snail.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"de.quastenflossler.snail"})
public class SpringConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringConfig.class);

    private static ApplicationContext APPLICATION_CONTEXT;

    public static ApplicationContext getApplicationContext() {
        return APPLICATION_CONTEXT;
    }

    @Autowired
    public void setApplicationContext(final ApplicationContext applicationContext) {

        SpringConfig.APPLICATION_CONTEXT = applicationContext;
        LOGGER.debug("==== Application context is autowired to SpringConfig ====");
    }

    public static <T> T getBean(final Class<T> clazz, final Object... args) {

        return (T) APPLICATION_CONTEXT.getBean(clazz, args);
    }

    public static Object getBean(final String beanName) {

        return APPLICATION_CONTEXT.getBean(beanName);
    }
}