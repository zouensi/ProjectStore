package com.zouensi.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 
 * @author zouensi
 * @date 2017年7月8日
 * 描述:dao，service对象创建工厂
 */
@SuppressWarnings("all")
public class BeanFactory {
	private BeanFactory() {
		
	}
	/**
	 * 通过配置文件获取对应全类限定名，创建对象并且返回
	 * @param id
	 * @return
	 */
	public static<T> T getBean(Class<T> temp) {
		SAXReader saxReader = new SAXReader();
		//获取配置文件对应的数据流
		InputStream resourceAsStream = BeanFactory.class.getClassLoader().getResourceAsStream("/applicationContext.xml");
		Object obj = null;
		try {
			//获取document对象
			Document document = saxReader.read(resourceAsStream);
			//获取跟元素
			Element rootElement = document.getRootElement();
			//获取根元素下面的子元素
			List<Element> elements = rootElement.elements();
			for (Element element : elements) {
//				System.out.println(element.attributeValue("class"));
				//如果元素id和输入的id匹配成功则获取全类限定名，通过反射进行对象创建
				if(temp.getName().contains(element.attributeValue("id"))) {
					Class<?> clazz = Class.forName(element.attributeValue("class"));
					obj = clazz.newInstance();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(resourceAsStream!=null) {
					resourceAsStream.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return (T)obj;
	}
}
