package io.renren.entity;

import lombok.Data;

import java.util.Date;

/**
 * 文件上传
 *
 * @author chenshun
 */
@Data
public class SysOssEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 记录ID
     */
    private Long id;

    /**
     * URL地址
     */
    private String url;

    /**
     * 创建时间
     */
    private Date createDate;
}
