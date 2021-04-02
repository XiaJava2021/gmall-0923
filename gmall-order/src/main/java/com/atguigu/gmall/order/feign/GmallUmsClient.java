package com.atguigu.gmall.order.feign;

import com.atguigu.gmall.pms.api.GmallPmsApi;
import com.atguigu.gmall.ums.api.GmallUmsApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @author Hasee  2021/3/15 16:15 周一
 * @version JDK 8.017
 * @description:
 */
@FeignClient("ums-service")
public interface GmallUmsClient extends GmallUmsApi {
}
