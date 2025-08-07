package com.qian.aicodemother.aop;

import com.qian.aicodemother.annotation.AuthCheck;
import com.qian.aicodemother.exception.BusinessException;
import com.qian.aicodemother.exception.ErrorCode;
import com.qian.aicodemother.model.entity.User;
import com.qian.aicodemother.model.enums.UserRoleEnum;
import com.qian.aicodemother.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class AuthInterceptor {

    @Resource
    private UserService userService;

    /**
     * 执行拦截
     * @param joinPoint 切入点
     * @param authCheck 权限校验注解
     */
    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
    String mustRole = authCheck.mustRole();
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        // 获取当前登录用户
        User loginUser = userService.getLoginUser(request);
        UserRoleEnum mustRoleEnum = UserRoleEnum.getEnumByValue(mustRole);
        //不需要权限直接放行
        if (mustRoleEnum == null) {
            return joinPoint.proceed();
        }
        //以下代码必须有这个权限才能通过
        UserRoleEnum userRoleEnum = UserRoleEnum.getEnumByValue(loginUser.getUserRole());
        //没有权限，直接拒绝
        if(userRoleEnum == null) {
            throw new BusinessException(ErrorCode.NOT_AUTH_ERROR);
        }
        //要求必须有管理员权限，但是当前用户没有
        if(UserRoleEnum.ADMIN.equals(mustRoleEnum) && !UserRoleEnum.ADMIN.equals(userRoleEnum)) {
            throw new BusinessException(ErrorCode.NOT_AUTH_ERROR);
        }
        //要求有管理员权限，当前用户有管理员权限或者普通用户直接放行
        return joinPoint.proceed();
    }
}
