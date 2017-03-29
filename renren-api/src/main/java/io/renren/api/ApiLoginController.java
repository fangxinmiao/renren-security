package io.renren.api;

import io.renren.annotation.IgnoreAuth;
import io.renren.service.TokenService;
import io.renren.service.UserService;
import io.renren.utils.R;
import io.renren.validator.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * API登录授权
 *
 * @author chenshun
 */
@RestController
@RequestMapping("/api")
public class ApiLoginController {
    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    public ApiLoginController(UserService userService,
                              TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    /**
     * 登录
     */
    @IgnoreAuth
    @PostMapping("login")
    public R login(String mobile, String password) {
        Assert.isBlank(mobile, "手机号不能为空");
        Assert.isBlank(password, "密码不能为空");
        long userId = userService.login(mobile, password);
        Map<String, Object> map = tokenService.createToken(userId);
        return R.ok(map);
    }
}
