package com.atguigu.gmall.ums.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.common.bean.PageResultVo;
import com.atguigu.gmall.common.bean.PageParamVo;

import com.atguigu.gmall.ums.mapper.UserMapper;
import com.atguigu.gmall.ums.entity.UserEntity;
import com.atguigu.gmall.ums.service.UserService;
import org.springframework.util.CollectionUtils;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<UserEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<UserEntity>()
        );

        return new PageResultVo(page);
    }

    @Override
    public Boolean checkData(String data, Integer type) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        switch (type) {
            case 1:
                wrapper.eq("username", data);
                break;
            case 2:
                wrapper.eq("phone", data);
                break;
            case 3:
                wrapper.eq("email", data);
                break;
            default:
                return null;
        }

        return this.count(wrapper) == 0;
    }

    @Override
    public void register(UserEntity userEntity, String code) {
        // TODO: 1.校验验证码,查询 redis 中的验证码和用户输入的 code 验证码比较

        // 2.生成加密盐
        String salt = UUID.randomUUID().toString().substring(0, 6);
        userEntity.setSalt(salt);
        // 加盐加密
        userEntity.setPassword(DigestUtils.md5Hex(userEntity.getPassword() + salt));

        // 3.新增用户
        userEntity.setLevelId(1L);
        userEntity.setNickname(userEntity.getUsername());
        userEntity.setSourceType(1);
        userEntity.setIntegration(1000);
        userEntity.setGrowth(1000);
        userEntity.setStatus(1);
        userEntity.setCreateTime(new Date());
        this.save(userEntity);

        // TODO: 4.删除 redis 中的验证码

    }

    @Override
    public UserEntity queryUser(String loginName, String password) {
        // 1.根据登录名查询用户
        List<UserEntity> userEntities = this.list(new QueryWrapper<UserEntity>().eq("username", loginName)
                .or().eq("phone", loginName)
                .or().eq("email", loginName));

        // 2.判断用户列表是否为空
        if (CollectionUtils.isEmpty(userEntities)) {
            return null;
        }

        // 3.获取用户中的盐,对用户输入的密码加盐加密
        for (UserEntity userEntity : userEntities) {
            // 数据库的密码
            String pwd = userEntity.getPassword();
            // 用户输入的密码加盐加密
            String curPwd = DigestUtils.md5Hex(password + userEntity.getSalt());

            // 4.判断加盐加密后的密码,和数据库中的密码是否相同
            if (StringUtils.equals(pwd, curPwd)) {
                // 5.返回用户信息
                return userEntity;
            }

        }
        return null;

    }

}

