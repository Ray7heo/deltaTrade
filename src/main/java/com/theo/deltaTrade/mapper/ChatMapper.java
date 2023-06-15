package com.theo.deltaTrade.mapper;

import com.theo.deltaTrade.entity.Chat;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Theo
* @description 针对表【chat】的数据库操作Mapper
* @createDate 2023-06-01 19:07:43
* @Entity com.theo.deltaTrade.entity.ChatHistory
*/
public interface ChatMapper extends BaseMapper<Chat> {

    List<Chat> selectGroupByCommodityId(@Param("userId")Long userId);

}




