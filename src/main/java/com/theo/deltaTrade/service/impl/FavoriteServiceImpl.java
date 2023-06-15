package com.theo.deltaTrade.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.theo.deltaTrade.entity.Favorite;
import com.theo.deltaTrade.service.FavoriteService;
import com.theo.deltaTrade.mapper.FavoriteMapper;
import org.springframework.stereotype.Service;

/**
* @author Theo
* @description 针对表【favorite】的数据库操作Service实现
* @createDate 2023-05-23 16:16:23
*/
@Service
public class FavoriteServiceImpl extends ServiceImpl<FavoriteMapper, Favorite>
    implements FavoriteService{

}




