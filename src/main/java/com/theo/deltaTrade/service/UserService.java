package com.theo.deltaTrade.service;

import com.theo.deltaTrade.common.ApiResult;
import com.theo.deltaTrade.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpSession;

/**
* @author Theo
* @description 针对表【user】的数据库操作Service
* @createDate 2023-05-22 18:06:22
*/
public interface UserService extends IService<User> {
    String randCode();
    Boolean sendCode(String email, HttpSession httpSession);
    ApiResult signUp(User user, HttpSession httpSession);

}
