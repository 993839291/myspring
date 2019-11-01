package mvc.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ly
 * @create 2019-01-12 15:57
 **/
public class RequestMappingHandlerAdapter implements HandlerAdapter {
    @Override
    public Object handler(HttpServletRequest request, HttpServletResponse response,Object handler) {
        return null;
    }

    @Override
    public boolean isMatch(HttpServletRequest request) {
        return false;
    }

    @Override
    public boolean isSupport(Object handler) {
        return handler instanceof HandlerMethod ;
    }
}
