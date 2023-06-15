package com.theo.deltaTrade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.theo.deltaTrade.common.ApiMsg;
import com.theo.deltaTrade.common.ApiResult;
import com.theo.deltaTrade.entity.User;
import com.theo.deltaTrade.service.UserService;
import com.theo.deltaTrade.mapper.UserMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Theo
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2023-05-22 18:06:22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Resource
    private MailSender mailSender;

    @Override
    public String randCode() {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }

    @Override
    public Boolean sendCode(String email, HttpSession httpSession) {
        if (email == null) {
            return false;
        }
        String check = "^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(email);
        boolean isMatched = matcher.matches();
        if (!isMatched) {
            return false;
        } else {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            String code = randCode();
            httpSession.setAttribute("email", email);
            httpSession.setAttribute("code", code);
            simpleMailMessage.setSubject("三角贸易验证码");
            simpleMailMessage.setText("验证码: " + code);
            simpleMailMessage.setFrom("Ray7heo@163.com");
            simpleMailMessage.setTo(email);
            mailSender.send(simpleMailMessage);
            return true;
        }
    }

    @Override
    public ApiResult signUp(User user, HttpSession httpSession) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getEmail, user.getEmail());
        long count = count(wrapper);
        if (count != 0) {
            return ApiResult.fail(ApiMsg.EMAIL_USED);
        }
        String currentEmail = (String) httpSession.getAttribute("email");
        String currentCode = (String) httpSession.getAttribute("code");
        if (currentEmail == null || currentCode == null) {
            return ApiResult.fail(ApiMsg.CODE_WRONG);
        }
        if (currentEmail.equals(user.getEmail()) && currentCode.equals(user.getCode())) {
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            if (save(user)) {
                return ApiResult.success();
            } else {
                return ApiResult.fail();
            }
        } else {
            return ApiResult.fail(ApiMsg.CODE_WRONG);
        }
    }

}




