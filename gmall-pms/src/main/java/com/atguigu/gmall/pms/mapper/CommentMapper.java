package com.atguigu.gmall.pms.mapper;

import com.atguigu.gmall.pms.entity.CommentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品评价
 * 
 * @author xiatian
 * @email xiatian@atguigu.com
 * @date 2021-03-08 19:48:28
 */
@Mapper
public interface CommentMapper extends BaseMapper<CommentEntity> {
	
}
