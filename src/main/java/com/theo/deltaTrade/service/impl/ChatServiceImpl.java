package com.theo.deltaTrade.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.theo.deltaTrade.entity.Chat;
import com.theo.deltaTrade.service.ChatService;
import com.theo.deltaTrade.mapper.ChatMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Theo
* @description 针对表【chat_history】的数据库操作Service实现
* @createDate 2023-06-01 19:07:43
*/
@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat>
    implements ChatService {

    @Override
    public List<Chat> groupByCommodity(Long userId) {
        return baseMapper.selectGroupByCommodityId(userId);
    }
}




