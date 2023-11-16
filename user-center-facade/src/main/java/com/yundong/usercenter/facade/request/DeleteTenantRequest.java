package com.yundong.usercenter.facade.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class DeleteTenantRequest implements Serializable {

    @NotNull(message = "ID不能为空")
    private Integer id;



}
