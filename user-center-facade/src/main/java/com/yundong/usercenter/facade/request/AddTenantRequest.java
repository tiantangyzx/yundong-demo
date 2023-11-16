package com.yundong.usercenter.facade.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class AddTenantRequest implements Serializable {

    @NotBlank(message = "name不能为空")
    private String name;

    @NotNull(message = "beginDate不能为空")
    private Date beginDate;

    @NotNull(message = "endDate不能为空")
    private Date endDate;


}
