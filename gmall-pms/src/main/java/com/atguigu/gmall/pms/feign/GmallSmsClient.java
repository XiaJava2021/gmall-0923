package com.atguigu.gmall.pms.feign;

import com.atguigu.gmall.common.bean.ResponseVo;
import com.atguigu.gmall.sms.api.GmallSmsApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Hasee  2021/3/10 19:45 周三
 * @version JDK 8.017
 * @description:
 */
@FeignClient("sms-service")
public interface GmallSmsClient extends GmallSmsApi {


}
