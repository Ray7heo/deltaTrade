package com.theo.deltaTrade.service;

import com.theo.deltaTrade.entity.Chat;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Theo
* @description 针对表【chat_history】的数据库操作Service
* @createDate 2023-06-01 19:07:43
*/
public interface ChatService extends IService<Chat> {
    List<Chat> groupByCommodity(Long userId);
}
