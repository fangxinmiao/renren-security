package io.renren.entity;

import lombok.Data;

import java.util.Date;

/**
 * 用户
 *
 * @author chenshun
 */
@Data
public class UserEntity {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 密码
     */
    transient private String password;

    /**
     * 创建时间
     */
    private Date createTime;
}
