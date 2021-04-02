package com.atguigu.gmall.oms.feign;

import com.atguigu.gmall.ums.api.GmallUmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Hasee  2021/3/15 16:15 周一
 * @version JDK 8.017
 * @description:
 */
@FeignClient("ums-service")
public interface GmallUmsClient extends GmallUmsApi {
}
