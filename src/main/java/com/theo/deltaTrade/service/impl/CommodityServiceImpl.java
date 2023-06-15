package com.theo.deltaTrade.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.theo.deltaTrade.entity.Commodity;
import com.theo.deltaTrade.service.CommodityService;
import com.theo.deltaTrade.mapper.CommodityMapper;
import org.springframework.stereotype.Service;

/**
* @author Theo
* @description 针对表【commodity】的数据库操作Service实现
* @createDate 2023-05-22 18:02:41
*/
@Service
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, Commodity>
    implements CommodityService{

}




