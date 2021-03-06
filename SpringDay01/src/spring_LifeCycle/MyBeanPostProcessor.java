package spring_LifeCycle;



import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
/**
 * 后处理bean的操作
 * 	spring提供工厂勾子，用于修改实例对象，可以生成代理对象，是AOP底层
 * @author dell
 *
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("前方法 ： " + beanName);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(final Object bean, String beanName) throws BeansException {
		System.out.println("后方法 ： " + beanName);
		// bean 目标对象
		// 生成 jdk 代理
		return Proxy.newProxyInstance(
					MyBeanPostProcessor.class.getClassLoader(), 
					bean.getClass().getInterfaces(), 
					new InvocationHandler(){
						@Override
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							
							System.out.println("------开启事务");
							
							//执行目标方法
							Object obj = method.invoke(bean, args);
							
							System.out.println("------提交事务");
							return obj;
						}

						});
	}
}

