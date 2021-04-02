package com.atguigu.gmall.auth.feign;

import com.atguigu.gmall.ums.api.GmallUmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Hasee  2021/3/27 13:24 周六
 * @version JDK 8.017
 * @description:
 */
@FeignClient("ums-service")
public interface GmallUmsClient extends GmallUmsApi {
}
