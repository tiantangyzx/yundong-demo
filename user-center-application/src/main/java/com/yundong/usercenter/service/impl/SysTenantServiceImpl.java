package com.yundong.usercenter.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.yundong.base.context.Context;
import com.yundong.base.exception.BizException;
import com.yundong.usercenter.entity.SysTenantDO;
import com.yundong.usercenter.enums.LoggerEnums;
import com.yundong.usercenter.facade.dto.TenantDTO;
import com.yundong.usercenter.facade.enums.ResultCodes;
import com.yundong.usercenter.facade.request.AddTenantRequest;
import com.yundong.usercenter.facade.request.DeleteTenantRequest;
import com.yundong.usercenter.facade.request.QueryTenantRequest;
import com.yundong.usercenter.facade.request.UpdateTenantRequest;
import com.yundong.usercenter.mapper.SysTenantMapper;
import com.yundong.usercenter.service.SysTenantService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


/**
 * SysTenantServiceImpl
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysTenantServiceImpl implements SysTenantService {

    private static final Logger dalLog = LoggerEnums.DAL_LOG.getLogger();

    @Autowired
    private SysTenantMapper sysTenantMapper;

    @Override
    public TenantDTO queryTenant(Context context, QueryTenantRequest request) {
        // 操作数据库获取数据
        SysTenantDO sysTenantDO = this.sysTenantMapper.selectById(request.getId());
        TenantDTO tenantDTO = BeanUtil.copyProperties(sysTenantDO, TenantDTO.class);
        dalLog.info("查询到租户信息为：" + JSON.toJSONString(tenantDTO));
        return tenantDTO;
    }

    @Override
    public void addTenant(Context context, AddTenantRequest request) {
        SysTenantDO sysTenantDO = BeanUtil.copyProperties(request,SysTenantDO.class);
        // 可采用枚举
        sysTenantDO.setStatus(0);
        sysTenantDO.setCreateBy(context.getIdentityId());
        this.sysTenantMapper.insert(sysTenantDO);
    }

    @Override
    public void updateTenant(Context context, UpdateTenantRequest request) {
        SysTenantDO sysTenantDO = this.sysTenantMapper.selectById(request.getId());
        if(Objects.isNull(sysTenantDO)){
            throw new BizException(ResultCodes.ID_NOT_FOUND);
        }
        BeanUtil.copyProperties(request,sysTenantDO);
        this.sysTenantMapper.updateById(sysTenantDO);
    }

    @Override
    public void deleteTenant(Context context, DeleteTenantRequest request) {
        SysTenantDO sysTenantDO = this.sysTenantMapper.selectById(request.getId());
        if(Objects.isNull(sysTenantDO)){
            throw new BizException(ResultCodes.ID_NOT_FOUND);
        }
        this.sysTenantMapper.deleteById(request.getId());
    }
}
