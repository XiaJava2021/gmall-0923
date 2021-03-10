package com.atguigu.gmall.pms.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.common.bean.PageResultVo;
import com.atguigu.gmall.common.bean.PageParamVo;

import com.atguigu.gmall.pms.mapper.SpuMapper;
import com.atguigu.gmall.pms.entity.SpuEntity;
import com.atguigu.gmall.pms.service.SpuService;



@Service("spuService")
public class SpuServiceImpl extends ServiceImpl<SpuMapper, SpuEntity> implements SpuService {

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<SpuEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<SpuEntity>()
        );

        return new PageResultVo(page);
    }

    @Override
    public PageResultVo querySpuByCidOrKeyPage(PageParamVo paramVo, Long cid) {

        QueryWrapper<SpuEntity> wrapper = new QueryWrapper<>();

        // 判断cid是否为 0 或 null 如果不是,则根据分类进行查询
        if (cid != 0){
            wrapper.eq("category_id",cid);
        }

        // 获取查询关键字
        String key = paramVo.getKey();
        // 如果关键字不为空,拼接查询条件
        if (StringUtils.isNotBlank(key)){
            // and 后需要小括号,所以此处使用了 and (Consumer)
            wrapper.and(t -> t.eq("id",key).or().like("name",key));
        }

        IPage<SpuEntity> page = this.page(
                paramVo.getPage(),
                wrapper
        );

        return new PageResultVo(page);
    }

}