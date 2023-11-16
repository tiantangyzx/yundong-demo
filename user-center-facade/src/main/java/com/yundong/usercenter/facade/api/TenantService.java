package com.yundong.usercenter.facade.api;

import com.yundong.base.context.Context;
import com.yundong.base.result.Result;
import com.yundong.usercenter.facade.dto.TenantDTO;
import com.yundong.usercenter.facade.request.AddTenantRequest;
import com.yundong.usercenter.facade.request.DeleteTenantRequest;
import com.yundong.usercenter.facade.request.QueryTenantRequest;
import com.yundong.usercenter.facade.request.UpdateTenantRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface TenantService {

    Result<TenantDTO> queryTenant(Context context, @Valid @NotNull QueryTenantRequest request);

    Result<Void> addTenant(Context context, @Valid @NotNull AddTenantRequest request);

    Result<Void> updateTenant(Context context, @Valid @NotNull UpdateTenantRequest request);

    Result<Void> deleteTenant(Context context, @Valid @NotNull DeleteTenantRequest request);

}
