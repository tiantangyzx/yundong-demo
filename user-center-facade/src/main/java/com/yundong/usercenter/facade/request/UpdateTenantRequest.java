package com.yundong.usercenter.facade.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class UpdateTenantRequest {

    @NotNull(message = "ID不能为空")
    private Integer id;

    private String name;

    private Date beginDate;

    private Date endDate;

    private Integer status;


}
