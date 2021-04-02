package com.atguigu.gmall.wms.vo;

import lombok.Data;

/**
 * @author Hasee  2021/3/31 18:46 周三
 * @version JDK 8.017
 * @description:
 */
@Data
public class SkuLockVo {

    private Long skuId;
    private Integer count;


    private Boolean lock;       // 锁库存的状态
    private Long wareSkuId;     // 在锁定成功的情况下,记录锁定成功的仓库 id, 以方便将来解锁

}
