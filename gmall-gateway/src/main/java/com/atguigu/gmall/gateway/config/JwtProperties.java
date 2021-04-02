package com.atguigu.gmall.gateway.config;

import com.atguigu.gmall.common.utils.RsaUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.io.File;
import java.security.PublicKey;

/**
 * @author Hasee  2021/3/27 13:01 周六
 * @version JDK 8.017
 * @description:
 */
@ConfigurationProperties(prefix = "auth.jwt")
@Data
public class JwtProperties {

    private String pubKeyPath;
    private String cookieName;

    private PublicKey publicKey;

    @PostConstruct
    public void init(){
        try {
            File pubFile = new File(pubKeyPath);

            // 读取密钥对象
            this.publicKey = RsaUtils.getPublicKey(pubKeyPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
