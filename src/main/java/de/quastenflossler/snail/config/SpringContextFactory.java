package de.quastenflossler.snail.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringContextFactory {

	private static final Object semaphore = new Object();
	private static ConfigurableApplicationContext applicationContext;

	public static ApplicationContext getApplicationContext() {
		
		if (null == applicationContext) {
			
			synchronized (semaphore) {

                if (null == applicationContext) {

                    applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
                }
            }
		}
		
		return applicationContext;
	}

	public static void close() {
		
		if (null != applicationContext) {
		
			applicationContext.close();
		}
	}

}
