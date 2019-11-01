package mvc.handler;

import java.lang.reflect.Method;

public class HandlerMethod {

    private String beanName;

    private Object beanType;

    private Method method;

    public HandlerMethod(String beanName, Object beanType, Method method) {
        this.beanName = beanName;
        this.beanType = beanType;
        this.method = method;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public Object getBeanType() {
        return beanType;
    }

    public void setBeanType(Object beanType) {
        this.beanType = beanType;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
