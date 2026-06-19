package com.denisar5.perfumehub.config;

import com.denisar5.perfumehub.model.enums.UserRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler) throws Exception {

        Object role = request.getSession().getAttribute("user_role");

        if (role == null || role != UserRole.ADMIN) {
            response.sendRedirect("/");
            return false;
        }

        return true;
    }
}
