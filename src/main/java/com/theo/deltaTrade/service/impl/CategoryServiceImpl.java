package com.theo.deltaTrade.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.theo.deltaTrade.entity.Category;
import com.theo.deltaTrade.service.CategoryService;
import com.theo.deltaTrade.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author Theo
* @description 针对表【category】的数据库操作Service实现
* @createDate 2023-05-22 18:02:41
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

}




