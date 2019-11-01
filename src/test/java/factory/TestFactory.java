package factory;

import bean.User;

/**
 * @author ly
 * @create 2018-12-02 13:46
 **/
public class TestFactory {
    public Object createMethod(){
        return new User("TestFactory",33);
    }

    public static Object staticCreateMethod(){
        return new User("staticCreateMethod",33);
    }
}
