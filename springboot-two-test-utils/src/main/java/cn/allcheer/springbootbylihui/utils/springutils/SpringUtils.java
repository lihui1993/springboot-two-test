package cn.allcheer.springbootbylihui.utils.springutils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author lihui
 */
@Component
public class SpringUtils implements ApplicationContextAware{

    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringUtils.applicationContext==null){
            SpringUtils.applicationContext=applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBeanByName(String beanName){
        return applicationContext.getBean(beanName);
    }

    public static <T> T getBeanByClass(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }

    public static <T> T getBeanByClassAndBeanName(Class<T> clazz,String beanName){
        return applicationContext.getBean(beanName,clazz);
    }
}
