import bean.User;
import bean.beandefinition.impl.DefaultBeanDefinition;
import bean.factory.impl.DefaultBeanFactory;
import factory.TestFactory;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author ly
 * @create 2018-12-02 11:41
 **/
public class MySpringTest {

    static DefaultBeanFactory factory = new DefaultBeanFactory();

    @Test
    public void test() throws Exception {
        //
        DefaultBeanDefinition bd = new DefaultBeanDefinition();
        bd.setBeanName("TestFactory");
        bd.setClazz(TestFactory.class);
        factory.register(bd, "TestFactory");
        bd = new DefaultBeanDefinition();
        bd.setClazz(User.class);
        bd.setSingleton(true);
        bd.setBeanFactoryName("TestFactory");
        bd.setCreateBeanMethodName("createMethod");
        bd.setStaticCreateBeanMethodName("staticCreateMethod");

        List<Object> args = new LinkedList<>();
        args.add("liyu");
        args.add(22);
        bd.setConstructorArg(args);
        bd.setBeanInitMethodName("init");
        bd.setBeanDestoryMethodName("destory");
        Map<String, Object> values = new HashMap<>();
        values.put("name", "JAY");
        values.put("age", 30);
        bd.setPropertyKeyValue(values);
        factory.register(bd, "user");
        User user = (User) factory.doGetBean("user");
        System.out.println(user.getName()+":"+user.getAge());
        factory.close();
    }
}
