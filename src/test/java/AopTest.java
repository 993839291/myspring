import advice.AroundAdvice;
import aop.advisor.impl.RegexMatchAdvisor;
import aop.creator.impl.AopProxyCreator;
import aop.pointcut.impl.RegexExpressionPointCutResolver;
import aop.proxy.impl.JDKDynamicProxy;
import bean.User;
import bean.UserService;
import bean.UserServiceImpl;
import bean.beandefinition.impl.DefaultBeanDefinition;
import bean.factory.impl.DefaultBeanFactory;
import org.junit.Test;

/**
 * @Auther: Administrator
 * @Date: 2018-12-12 11:00
 * @Description:
 */
public class AopTest {
    static DefaultBeanFactory factory = new DefaultBeanFactory();

    @Test
    public void test() throws Exception {
        DefaultBeanDefinition bd = new DefaultBeanDefinition();
        bd.setClazz(UserServiceImpl.class);
        bd.setSingleton(true);
        bd.setBeanFactoryName("TestFactory");
        bd.setCreateBeanMethodName("createMethod");
        bd.setStaticCreateBeanMethodName("staticCreateMethod");
        factory.register(bd, "userService");
       /* UserService userService = (UserService) factory.getBean("userService");
        User user = userService.getById("1");
        System.out.println("name:"+user.getName());
        System.out.println("age:"+user.getAge());*/
        bd = new DefaultBeanDefinition();
        bd.setClazz(AroundAdvice.class);
        factory.register(bd, "AroundAdvice");

        AopProxyCreator aapc = new AopProxyCreator();
        aapc.setBeanFactory(factory);
        //aop处理器加入到aop处理器list
        factory.registerBeanPostProcessor(aapc);
        // 向AdvisorAutoProxyCreator注册Advisor
        //添加增强器
        //对那些位置进行增强，以及增强方式
        aapc.register(new RegexMatchAdvisor("AroundAdvice", "execution(* bean.UserServiceImpl.getById())", new RegexExpressionPointCutResolver()));

        UserService userService = (UserService) factory.doGetBean("userService");
        User user = userService.getById2("2");
        System.out.println("name:"+user.getName());
        System.out.println("age:"+user.getAge());
        //JDKDynamicProxy jdkDynamicProxy = new JDKDynamicProxy(null);


    }
}
