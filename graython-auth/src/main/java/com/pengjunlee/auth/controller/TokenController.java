package com.pengjunlee.auth.controller;

import com.pengjunlee.common.core.context.SecurityContextHolder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.pengjunlee.auth.form.LoginBody;
import com.pengjunlee.auth.form.RegisterBody;
import com.pengjunlee.auth.service.SysLoginService;
import com.pengjunlee.common.core.domain.R;
import com.pengjunlee.common.core.utils.JwtUtils;
import com.pengjunlee.common.core.utils.StringUtils;
import com.pengjunlee.common.security.auth.AuthUtil;
import com.pengjunlee.common.security.service.TokenService;
import com.pengjunlee.common.security.utils.SecurityUtils;
import com.pengjunlee.system.api.model.LoginUser;

/**
 * token 控制
 * 
 * @author graython
 */
@RestController
public class TokenController
{
    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysLoginService sysLoginService;

    @PostMapping("login")
    public R<?> login(@RequestBody LoginBody form)
    {
        String tenantId = form.getTenantId();
        if (StringUtils.isBlank(tenantId)) R.fail("租户为空");
        SecurityContextHolder.setTenantId(tenantId);
        // 用户登录
        LoginUser userInfo = sysLoginService.login(tenantId,form.getUsername(), form.getPassword());
        // 获取登录token
        return R.ok(tokenService.createToken(tenantId, userInfo));
    }

    @DeleteMapping("logout")
    public R<?> logout(HttpServletRequest request)
    {
        String token = SecurityUtils.getToken(request);
        if (StringUtils.isNotEmpty(token))
        {
            String username = JwtUtils.getUserName(token);
            // 删除用户缓存记录
            AuthUtil.logoutByToken(token);
            // 记录用户退出日志
            sysLoginService.logout(username);
        }
        return R.ok();
    }

    @PostMapping("refresh")
    public R<?> refresh(HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser))
        {
            // 刷新令牌有效期
            tokenService.refreshToken(loginUser);
            return R.ok();
        }
        return R.ok();
    }

    @PostMapping("register")
    public R<?> register(@RequestBody RegisterBody registerBody)
    {
        // 用户注册
        sysLoginService.register(registerBody.getUsername(), registerBody.getPassword());
        return R.ok();
    }
}
