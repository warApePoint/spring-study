package com.warape.dependency.injection;

import com.warape.dependency.injection.domain.User;
import com.warape.dependency.injection.domain.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 基于Annotation+xml资源的依赖  Field方法注入
 *
 * @author wanmingyu
 * @create 2020/7/23 1:46 下午
 */
public class AnnotationDependencyFieldInjection {

    @Autowired
    private UserHolder userHolder;

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyFieldInjection.class);


        XmlBeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xml = "classpath:/META-INF/dependency-lookup.xml";
        definitionReader.loadBeanDefinitions(xml);

        applicationContext.refresh();


        AnnotationDependencyFieldInjection bean = applicationContext.getBean(AnnotationDependencyFieldInjection.class);

        UserHolder userHolder = bean.userHolder;
        System.out.println(userHolder);

        applicationContext.close();
    }


    @Bean
    public UserHolder userHolder(User user){
        UserHolder userHolder = new UserHolder();
        userHolder.setUser(user);
        return userHolder;
    }

}
