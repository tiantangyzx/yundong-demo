package com.yundong.usercenter.service;

import com.yundong.base.context.Context;
import com.yundong.usercenter.facade.dto.TenantDTO;
import com.yundong.usercenter.facade.request.AddTenantRequest;
import com.yundong.usercenter.facade.request.DeleteTenantRequest;
import com.yundong.usercenter.facade.request.QueryTenantRequest;
import com.yundong.usercenter.facade.request.UpdateTenantRequest;


/**
 * SysTenantService
 *
 * @author mengmeng
 * @date 2023/5/31
 * @time 15:39
 */
public interface SysTenantService {

    TenantDTO queryTenant(Context context, QueryTenantRequest request);

    void addTenant(Context context,AddTenantRequest request);

    void updateTenant(Context context, UpdateTenantRequest request);

    void deleteTenant(Context context, DeleteTenantRequest request);

}
