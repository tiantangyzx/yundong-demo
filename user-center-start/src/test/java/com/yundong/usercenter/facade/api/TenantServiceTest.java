package com.yundong.usercenter.facade.api;


import com.alibaba.fastjson.JSON;
import com.yundong.base.context.Context;
import com.yundong.base.result.Result;
import com.yundong.metadata.facade.dto.request.InvokeApiRequest;
import com.yundong.usercenter.dubbo.consumer.MetadataApiServiceClient;
import com.yundong.usercenter.facade.dto.TenantDTO;
import com.yundong.usercenter.facade.request.AddTenantRequest;
import com.yundong.usercenter.facade.request.DeleteTenantRequest;
import com.yundong.usercenter.facade.request.QueryTenantRequest;
import com.yundong.usercenter.facade.request.UpdateTenantRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;


@SpringBootTest
class TenantServiceTest {

    @Autowired
    private TenantService tenantService;
    @Autowired
    private MetadataApiServiceClient metadataApiServiceClient;

    /**
     * web请求通过网关时，网关会校验token并生成Context对象送给下游系统
     */
    private static Context getContext(){
        Context context = new Context();
        // 租户id
        context.setTenantId("");
        // 身份id
        context.setIdentityId("");
        // 应用id
        context.setAppId("");
        return context;
    }

    @Test
    void invokeApi(){
        InvokeApiRequest request = new InvokeApiRequest();
        // api接口名称
        request.setName("");
        // 应用id
        request.setAppId("");
        //应用版本
        request.setVersion("");
        // api接口参数
        request.setParams(new HashMap<>());
        // 调用接口
        Object o = this.metadataApiServiceClient.invokeApi(getContext(), request);
    }

    @Test
    void addTenant(){
        AddTenantRequest request = new AddTenantRequest();
        request.setName("云动企业");
        request.setBeginDate(new Date());
        final Date date = Date.from(LocalDateTime.now().plusYears(100).atZone(ZoneId.systemDefault()).toInstant());
        request.setEndDate(date);
        Result<Void> result = this.tenantService.addTenant(getContext(), request);
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    void queryTenant() {
        QueryTenantRequest request = new QueryTenantRequest();
        request.setId(1);
        final Result<TenantDTO> result = this.tenantService.queryTenant(getContext(), request);
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    void updateTenant(){
        UpdateTenantRequest request = new UpdateTenantRequest();
        request.setId(2);
        request.setStatus(1);
        final Result<Void> result = this.tenantService.updateTenant(getContext(), request);
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    void deleteTenant(){
        DeleteTenantRequest request = new DeleteTenantRequest();
        request.setId(2);
        final Result<Void> result = this.tenantService.deleteTenant(getContext(), request);
        System.out.println(JSON.toJSONString(result));
    }


}