package com.xtlh.sbdemo.service.serviceimpl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.xtlh.sbdemo.entity.SysRole;
import com.xtlh.sbdemo.repository.RoleRepository;
import com.xtlh.sbdemo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @作者 陈坤
 * @创建日期 2018/5/25
 * @功能描述 角色业务层实现类
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Qualifier("roleRepository")
    @Autowired
    private RoleRepository roleRepository;

    /**
     *
     * @作者		陈坤
     * @创建日期	2018/5/25 10:32
     * @功能描述	查询所有角色
     * @参数
     * @返回值
     *
     */
    public String findAll()
    {
        List<SysRole> roles = roleRepository.findAll();
        JSONObject result = new JSONObject();

        return result.toJSONString(roles);
    }
}
