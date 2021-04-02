package com.atguigu.gmall.auth;

import com.atguigu.gmall.common.utils.JwtUtils;
import com.atguigu.gmall.common.utils.RsaUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    // 别忘了创建D:\\project\rsa目录
	private static final String pubKeyPath = "D:\\ShangGuigu\\IdeaFiles\\DianShang\\rsa\\rsa.pub";
    private static final String priKeyPath = "D:\\ShangGuigu\\IdeaFiles\\DianShang\\rsa\\rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "234");
    }

    @BeforeEach     // 生成 rsa 公钥后解开注解
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    @Test
    public void testGenerateToken() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("id", "11");
        map.put("username", "jason");
        // 生成token
        String token = JwtUtils.generateToken(map, privateKey, 2);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6IjExIiwidXNlcm5hbWUiOiJqYXNvbiIsImV4cCI6MTYxNjgxMjQzNH0.S4RLXqbKHWa02ORFgxP9iygegfxhEHP4BTWJYRKXZbt_FwcZi297PqOJc7XqmebKtzX8ntMM_FP2b4SZjS9ylV4Limt9tZFyxb0QyvFchBX1GiokfPL8bysImzpkjLDjzqK8I4UVQbTMoI6jl5gR-jWS02m5uK46-ghUZ61io2pBkvAiw_FHE-FcGeMHoMKq-ISVRsOcXubKT6HJ0kvrb_OkBTSmZQ5dXwYGDJoJ6SCLIkRtimd_dE7HtB-JOm8hEbBpBFpeWE8gumuYs_3vDcJrBEidXZkkGhQDchdaVCLXWhv56hG5GsBaEnwx14bqbR3N5RfXAEpUYBxu_YuPSQ";

        // 解析token
        Map<String, Object> map = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + map.get("id"));
        System.out.println("userName: " + map.get("username"));
    }
}