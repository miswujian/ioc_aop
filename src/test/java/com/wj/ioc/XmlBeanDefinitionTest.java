package com.wj.ioc;

import com.wj.aop.GraduationService;
import com.wj.ioc.factory.XmlBeanFactory;
import org.junit.Test;

/**
 * @Author wujian
 * @Date 2020/2/17 0:38
 * @Version 1.0
 */
public class XmlBeanDefinitionTest {
    @Test
    public void loadBeanDefinition() throws Exception{
        System.out.println("IOC TEST");
        String location = getClass().getClassLoader().getResource("ioc.xml").getFile();
        XmlBeanFactory bf = new XmlBeanFactory(location);
        People people = (People) bf.getBean("people");
        System.out.println(people);
        Life life = (Life)bf.getBean("life");
        System.out.println(life);

        System.out.println("\nAOP TEST");
        GraduationService graduationService = (GraduationService) bf.getBean("graduationService");
        graduationService.doGraduationProject();
        System.err.println(graduationService.speed("0%"));
    }
}
