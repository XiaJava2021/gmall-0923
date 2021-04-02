package com.atguigu.gmall.oms.feign;

import com.atguigu.gmall.sms.api.GmallSmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Hasee  2021/3/23 21:26 周二
 * @version JDK 8.017
 * @description:
 */
@FeignClient("sms-service")
public interface GmallSmsClient extends GmallSmsApi {
}
