package io.renren.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时执行日志
 *
 * @author chenshun
 */
@Data
public class ScheduleJobLogEntity {

    /**
     * 日志id
     */
    private Long logId;

    /**
     * 任务id
     */
    private Long jobId;

    /**
     * spring bean名称
     */
    private String beanName;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 参数
     */
    private String params;

    /**
     * 任务状态(0：成功 1：失败)
     */
    private Integer status;

    /**
     * 失败信息
     */
    private String error;

    /**
     * 耗时(单位：毫秒)
     */
    private Integer times;

    /**
     * 创建时间
     */
    private Date createTime;
}