package io.renren.entity;

import lombok.Data;

import java.util.Date;

/**
 * 系统日志
 *
 * @author chenshun
 */
@Data
public class SysLogEntity {

    /**
     * 记录id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户操作
     */
    private String operation;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求参数
     */
    private String params;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 创建时间
     */
    private Date createDate;
}
