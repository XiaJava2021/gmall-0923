package com.atguigu.gmall.index.feign;

import com.atguigu.gmall.pms.api.GmallPmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Hasee  2021/3/19 19:57 周五
 * @version JDK 8.017
 * @description:
 */
@FeignClient("pms-service")
public interface GmallPmsClient extends GmallPmsApi {
}
