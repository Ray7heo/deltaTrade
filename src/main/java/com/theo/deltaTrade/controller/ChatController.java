package com.theo.deltaTrade.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.theo.deltaTrade.common.ApiMsg;
import com.theo.deltaTrade.common.ApiResult;
import com.theo.deltaTrade.entity.Chat;
import com.theo.deltaTrade.service.ChatService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Resource
    private ChatService chatService;

    /**
     * 根据用户id和商品id获取聊天记录
     *
     * @param userId      用户id
     * @param commodityId 卖家id
     */
    @GetMapping("/{userId}/{commodityId}")
    public ApiResult getByUserIdAndCommodityId(@PathVariable String userId, @PathVariable String commodityId) {
        LambdaQueryWrapper<Chat> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Chat::getFromId, userId)
                .and(w -> w.eq(Chat::getCommodityId, commodityId))
                .or().eq(Chat::getToId, userId)
                .and(w -> w.eq(Chat::getCommodityId, commodityId))
                .orderByAsc(Chat::getCreateTime);
        List<Chat> list = chatService.list(wrapper);
        if (list == null) {
            return ApiResult.fail(ApiMsg.USER_NOT_EXIST);
        } else {
            return ApiResult.success(list);
        }
    }

    /**
     * 根据用户id获取所有聊天记录并按商品id分组
     *
     * @param userId 用户id
     */
    @GetMapping("/{userId}")
    public ApiResult getByUserId(@PathVariable Long userId) {
        List<Chat> list = chatService.groupByCommodity(userId);
        if (list == null) {
            return ApiResult.fail(ApiMsg.USER_NOT_EXIST);
        } else {
            return ApiResult.success(list);
        }
    }
}
