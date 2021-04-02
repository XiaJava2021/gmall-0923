package com.atguigu.gmall.payment.feign;

import com.atguigu.gmall.oms.api.GmallOmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Hasee  2021/4/1 19:50 周四
 * @version JDK 8.017
 * @description:
 */
@FeignClient("oms-service")
public interface GmallOmsClient extends GmallOmsApi {
}
