package com.atguigu.gmall.order.feign;

import com.atguigu.gmall.oms.api.GmallOmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Hasee  2021/3/15 16:15 周一
 * @version JDK 8.017
 * @description:
 */
@FeignClient("oms-service")
public interface GmallOmsClient extends GmallOmsApi {
}
