package com.yundong.usercenter.facade.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TenantDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 编码
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    private Date beginDate;

    /**
     * 结束时间
     */
    private Date endDate;

    /**
     * 状态 1正常 0冻结
     */
    private Integer status;


    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date gmtModified;



}
