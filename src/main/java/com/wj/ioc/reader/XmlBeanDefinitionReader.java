package com.wj.ioc.reader;

import com.wj.ioc.BeanDefinition;
import com.wj.ioc.BeanReference;
import com.wj.ioc.PropertyValue;
import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author wujian
 * @Date 2020/2/15 16:50
 * @Version 1.0
 * xml类读取流
 */
public class XmlBeanDefinitionReader implements BeanDefinitionReader{

    /**
     * 存储bean定义
     */
    private Map<String, BeanDefinition> beanDefinitionMap;

    public XmlBeanDefinitionReader() {
        beanDefinitionMap = new HashMap<>();
    }
    
    public Map<String, BeanDefinition> getBeanDefinitionMap(){
        return beanDefinitionMap;
    }

    /**
     * 根据location获取元素
     * @param location
     * @throws Exception
     */
    public void loadBeanDefinitions(String location)throws Exception{
        InputStream inputStream = new FileInputStream(location);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream);
        Element element = document.getDocumentElement();
        loadBeanDefinitions(element);
    }

    /**
     * 通过根元素 一步步解析出有效元素
     * @param element 根元素
     */
    private void loadBeanDefinitions(Element element){
        //element的属性为beans里面的 如xmlns 在纯bean里面 nodeList一般为2*bean+1个node 其中有n+1个为null
        NodeList nodeList = element.getChildNodes();
        for(int i = 0; i < nodeList.getLength(); i++){
            //node的属性为bean的 如id,class
            Node node = nodeList.item(i);
            //是否为有效元素 即不为空
            if(node instanceof  Element){
                parseBeanDefinition((Element) node);
            }
        }
    }

    /**
     * 解析出beanDefinition 为了简便未使用BeanDefinitionHolder
     * @param element
     */
    private void parseBeanDefinition(Element element){
        String name = element.getAttribute("id");
        String className = element.getAttribute("class");
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName(className);
        processProperty(element, beanDefinition);
        beanDefinitionMap.put(name, beanDefinition);
    }

    /**
     * property属性配置
     * @param element
     * @param beanDefinition
     */
    private void processProperty(Element element, BeanDefinition beanDefinition){
        NodeList nodeList = element.getElementsByTagName("property");
        for(int i = 0; i<nodeList.getLength(); i++){
            Node propertyNode = nodeList.item(i);
            //属于属性结点
            if(propertyNode instanceof  Element){
                Element property = (Element)propertyNode;
                String name = property.getAttribute("name");
                String value = property.getAttribute("value");
                if(!StringUtils.isBlank(value)){
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name,value));
                }else{
                    String ref = property.getAttribute("ref");
                    if(StringUtils.isBlank(ref)){
                        throw new IllegalArgumentException("ref配置不正确");
                    }
                    BeanReference beanReference = new BeanReference(ref);
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, beanReference));
                }
            }
        }
    }
    
}
