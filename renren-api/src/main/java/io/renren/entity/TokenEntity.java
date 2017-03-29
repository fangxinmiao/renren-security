package io.renren.entity;

import lombok.Data;

import java.util.Date;

/**
 * 用户Token
 *
 * @author chenshun
 */
@Data
public class TokenEntity {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * token
     */
    private String token;

    /**
     * 过期时间
     */
    private Date expireTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
