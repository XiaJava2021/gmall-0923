package com.atguigu.gmall.cart.feign;

import com.atguigu.gmall.pms.api.GmallPmsApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @author Hasee  2021/3/15 16:15 周一
 * @version JDK 8.017
 * @description:
 */
@FeignClient("pms-service")
@Component
public interface GmallPmsClient extends GmallPmsApi {
}
