package mvc.handler;

import bean.aware.ApplicationContextAware;
import context.app.ApplicationContext;
import mvc.annotation.Controller;
import mvc.annotation.RequestMapping;
import mvc.controller.RequestMappingInfo;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ly
 * @create 2019-01-12 15:55
 **/
public class RequestMappingHandlerMapping implements HandlerMapping,InitializingBean, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private List<RequestMappingInfo> requestMappingInfos;

    private Map<String, List<RequestMappingInfo>> urlMaps2;

    private Map<String, HandlerMethod> urlMaps = new ConcurrentHashMap<>();

    @Override
    public Object getHandler(HttpServletRequest request) {
        String requestUrl = request.getRequestURI();
        return urlMaps.get(requestUrl);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() {
        String[] beanNameForType = applicationContext.getBeanNameForType(Object.class);
        for(String beanName:beanNameForType){
            Class type = applicationContext.getType(beanName);
            //判断是否是控制器类型
            if (type!=null && isHandler(type)) {
                //注册控制器的类型
                detectHandlerMethod(beanName,type);
            }

        }
    }

    private void detectHandlerMethod(String beanName,Class type) {
        Method[] methods = type.getMethods();
        for(Method method :methods){
            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
            if(requestMapping!=null){
                String[] valueUrl = requestMapping.value();
                urlMaps.put(valueUrl[0],new HandlerMethod(beanName,type,method));
            }
        }
    }

    public boolean isHandler(Class beanType){
        return beanType.isAnnotationPresent(Controller.class) || beanType.isAnnotationPresent(RequestMapping.class);
    }
}
