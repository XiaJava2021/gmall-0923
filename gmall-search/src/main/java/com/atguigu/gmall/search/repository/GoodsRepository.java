package com.atguigu.gmall.search.repository;

import com.atguigu.gmall.search.pojo.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Hasee  2021/3/15 16:19 周一
 * @version JDK 8.017
 * @description:
 */
public interface GoodsRepository extends ElasticsearchRepository<Goods,Long> {
}
