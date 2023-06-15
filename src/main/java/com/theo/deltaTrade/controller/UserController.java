package com.theo.deltaTrade.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.theo.deltaTrade.common.ApiMsg;
import com.theo.deltaTrade.common.ApiResult;
import com.theo.deltaTrade.entity.User;
import com.theo.deltaTrade.service.UserService;
import com.theo.deltaTrade.util.Token;
import com.theo.deltaTrade.vo.WxCode;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 发送验证码
     *
     * @param email 邮箱
     */
    @PostMapping("/email")
    public ApiResult sendCode(String email, HttpSession httpSession) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getEmail, email);
        long count = userService.count(wrapper);
        if (count != 0) {
            return ApiResult.fail(ApiMsg.EMAIL_USED);
        }
        if (userService.sendCode(email, httpSession)) {
            return ApiResult.success();
        } else {
            return ApiResult.fail();
        }
    }

    /**
     * 用户注册
     *
     * @param user 用户实体
     */
    @PostMapping("/signUp")
    public ApiResult signUp(@RequestBody User user, HttpSession httpSession) {
        return userService.signUp(user, httpSession);
    }

    /**
     * 用户登录
     *
     * @param user 用户实体
     */
    @PostMapping("/login")
    public ApiResult login(@RequestBody User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getEmail, user.getEmail());
        User realUser = userService.getOne(wrapper);
        if (realUser == null) {
            return ApiResult.fail(ApiMsg.USER_NOT_EXIST);
        }
        if (BCrypt.checkpw(user.getPassword(), realUser.getPassword())) {
            Map<String, Object> map = new HashMap<>();
            map.put("token", Token.create());
            map.put("userId", realUser.getId());
            return ApiResult.success(map);
        } else {
            return ApiResult.fail(ApiMsg.PASSWORD_WRONG);
        }
    }

    /**
     * 返回小程序密钥
     */
    @GetMapping("/code")
    public ApiResult codeToSession() {
        String appid = "wxdcfb088ecd7cc6a6";
        String secret = "b655dbb41469be19b09575d1e568dd23";
        return ApiResult.success(new WxCode(appid, secret));
    }

    /**
     * 用户通过微信注册
     */
    @PostMapping("/wx/signUp")
    public ApiResult signUpByWx(@RequestBody User user) {
        if (user.getOpenId() == null) {
            return ApiResult.fail(ApiMsg.CODE_WRONG);
        }
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getOpenId, user.getOpenId());
        if (userService.saveOrUpdate(user, wrapper)) {
            return ApiResult.success();
        } else {
            return ApiResult.fail(ApiMsg.USER_NOT_EXIST);
        }
    }

    /**
     * 用户通过微信登录
     */
    @PostMapping("/wx/login")
    public ApiResult loginByWx(String openId) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getOpenId, openId);
        User existUser = userService.getOne(wrapper);
        Map<String, Object> map = new HashMap<>();
        if (existUser != null) {
            map.put("userId", existUser.getId());
            map.put("token", Token.create());
            return ApiResult.success(map);
        } else {
            return ApiResult.fail(ApiMsg.USER_NOT_EXIST);
        }
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     */
    @DeleteMapping("/{id}")
    public ApiResult deleteById(@PathVariable Long id) {
        if (userService.removeById(id)) {
            return ApiResult.success();
        } else {
            return ApiResult.fail();
        }
    }

    /**
     * 更新用户
     *
     * @param user 用户实体
     */
    @PutMapping
    public ApiResult update(@RequestBody User user) {
        if (user.getPassword() != null) {
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        }
        if (userService.updateById(user)) {
            return ApiResult.success();
        } else {
            return ApiResult.fail();
        }
    }

    /**
     * 根据id获取用户
     *
     * @param id 用户id
     */
    @GetMapping("/{id}")
    public ApiResult getById(@PathVariable Long id) {
        User user = userService.getById(id);
        if (user == null) {
            return ApiResult.fail(ApiMsg.USER_NOT_EXIST);
        } else {
            return ApiResult.success(user);
        }
    }
}
