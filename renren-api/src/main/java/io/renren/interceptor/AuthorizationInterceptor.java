package io.renren.interceptor;

import io.renren.annotation.IgnoreAuth;
import io.renren.entity.TokenEntity;
import io.renren.service.TokenService;
import io.renren.utils.RRException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限(Token)验证
 *
 * @author chenshun
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    private final TokenService tokenService;

    public static final String LOGIN_USER_KEY = "LOGIN_USER_KEY";

    @Autowired
    public AuthorizationInterceptor(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        IgnoreAuth annotation;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(IgnoreAuth.class);
        } else {
            return true;
        }

        // 如果有@IgnoreAuth注解，则不验证token
        if (annotation != null) {
            return true;
        }

        // 获取token
        String token = request.getParameter("token");

        // token为空
        if (StringUtils.isBlank(token)) {
            throw new RRException("token不能为空");
        }

        // 查询token信息
        TokenEntity tokenEntity = tokenService.queryByToken(token);
        if (tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()) {
            throw new RRException("token失效，请重新登录");
        }

        // 设置userId到request里，后续根据userId，获取用户信息
        request.setAttribute(LOGIN_USER_KEY, tokenEntity.getUserId());

        return true;
    }
}