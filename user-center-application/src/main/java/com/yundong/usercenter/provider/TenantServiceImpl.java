package com.yundong.usercenter.provider;

import com.yundong.base.context.Context;
import com.yundong.base.result.Result;
import com.yundong.gateway.facade.route.enums.GatewayApiTypeEnum;
import com.yundong.gateway.register.annotation.YdcGateway;
import com.yundong.usercenter.facade.api.TenantService;
import com.yundong.usercenter.facade.dto.TenantDTO;
import com.yundong.usercenter.facade.request.AddTenantRequest;
import com.yundong.usercenter.facade.request.DeleteTenantRequest;
import com.yundong.usercenter.facade.request.QueryTenantRequest;
import com.yundong.usercenter.facade.request.UpdateTenantRequest;
import com.yundong.usercenter.service.SysTenantService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@Component
@Service(version = "${dubbo.provider.version}", group = "${dubbo.provider.group}")
public class TenantServiceImpl implements TenantService {

    @Autowired
    private SysTenantService sysTenantService;

    @Override
    @YdcGateway(url = "user/tenant/queryTenant.json", name = "查询租户信息" ,apiType = GatewayApiTypeEnum.GLOBAL)
    public Result<TenantDTO> queryTenant(Context context, @Valid @NotNull QueryTenantRequest request) {
        return Result.success(this.sysTenantService.queryTenant(context,request));
    }

    @Override
    @YdcGateway(url = "user/tenant/addTenant.json", name = "添加租户信息" ,apiType = GatewayApiTypeEnum.GLOBAL)
    public Result<Void> addTenant(Context context, @Valid @NotNull AddTenantRequest request) {
        this.sysTenantService.addTenant(context,request);
        return Result.success();
    }

    @Override
    @YdcGateway(url = "user/tenant/updateTenant.json", name = "更新租户信息" ,apiType = GatewayApiTypeEnum.GLOBAL)
    public Result<Void> updateTenant(Context context, @Valid @NotNull UpdateTenantRequest request) {
        this.sysTenantService.updateTenant(context,request);
        return Result.success();
    }

    @Override
    @YdcGateway(url = "user/tenant/deleteTenant.json", name = "删除租户信息" ,apiType = GatewayApiTypeEnum.GLOBAL)
    public Result<Void> deleteTenant(Context context, @Valid @NotNull DeleteTenantRequest request) {
        this.sysTenantService.deleteTenant(context,request);
        return Result.success();
    }
}
