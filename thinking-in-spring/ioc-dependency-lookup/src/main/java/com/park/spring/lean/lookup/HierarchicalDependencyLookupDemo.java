package com.park.spring.lean.lookup;

import com.park.spring.learn.bean.factory.impl.DefaultUserFactory;
import com.park.spring.learn.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 层次性依赖查找 示例
 *
 * @author Aaron
 * @since
 */
public class HierarchicalDependencyLookupDemo {
    public static void main(String[] args) {
        // 创建一个 BeanFactory 容器
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        // 设置一个配置类
        ctx.register(HierarchicalDependencyLookupDemo.class);

        // 获取带有层次的容器
        ConfigurableListableBeanFactory beanFactory = ctx.getBeanFactory();
        System.out.println("当前 BeanFactory 的 Parent beanFactory：" + beanFactory.getParentBeanFactory());

        // 设置一个父工厂
        DefaultListableBeanFactory defaultListableBeanFactory = createBeanFactory();
        beanFactory.setParentBeanFactory(defaultListableBeanFactory);
        System.out.println("当前 BeanFactory 的 Parent BeanFactory ： " + beanFactory.getParentBeanFactory());

        displayLocalBean(beanFactory, "user");
        displayLocalBean(defaultListableBeanFactory, "user");

        displayContainsBean(beanFactory, "user");
        displayContainsBean(defaultListableBeanFactory, "user");

        // 启动应用上下文
        ctx.refresh();
        // 关闭容器
        ctx.close();
    }

    public static void displayContainsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前 BeanFactory[%s] 是否包含 Bean[name : %s] : %s\n", beanFactory, beanName,
                recursiveLookupBean(beanFactory, beanName));
    }

    public static boolean recursiveLookupBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if (parentBeanFactory instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory hierarchicalBeanFactory = HierarchicalBeanFactory.class.cast(parentBeanFactory);
            if (recursiveLookupBean(hierarchicalBeanFactory, beanName)) {
                return true;
            }
        }
        return beanFactory.containsLocalBean(beanName);
    }

    public static void displayLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前 BeanFactory[%s] 是否包含 bean[name : %s] : %s\n", beanFactory, beanName, beanFactory.containsLocalBean(beanName));
    }

    public static DefaultListableBeanFactory createBeanFactory() {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        String path = "classpath:/META-INF/bean-definition-context.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(path);
        return defaultListableBeanFactory;
    }
}
