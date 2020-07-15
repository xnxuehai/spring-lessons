
package com.park.creation;

import com.park.domain.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

/**
 * 使用 {@link BeanNameGenerator} 构建 Bean 的名称
 *
 * @author Aaron
 * @date 2020/6/8 21:56
 */
public class BeanNameGeneratorDemo {

    public static void main(String[] args) {

        defaultBeanNameGenerator();

        annotationBeanNameGenerator();
    }

    /**
     * DefaultBeanNameGenerator 方式实现 BeanName 的生成
     */
    private static void defaultBeanNameGenerator() {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = getBeanDefinition();

        DefaultBeanNameGenerator defaultBeanNameGenerator = new DefaultBeanNameGenerator();
        String beanName = defaultBeanNameGenerator.generateBeanName(beanDefinition, defaultListableBeanFactory);

        System.out.println(beanName);
    }

    /**
     * AnnotationBeanNameGenerator 方式实现 BeanName 的生成
     */
    private static void annotationBeanNameGenerator() {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = getBeanDefinition();

        AnnotationBeanNameGenerator annotationBeanNameGenerator = new AnnotationBeanNameGenerator();
        String beanName = annotationBeanNameGenerator.generateBeanName(beanDefinition, defaultListableBeanFactory);

        System.out.println(beanName);
    }


    private static BeanDefinition getBeanDefinition() {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder
                .addPropertyValue("name", "Aaron")
                .addPropertyValue("age", "20");
        return beanDefinitionBuilder.getBeanDefinition();
    }

}