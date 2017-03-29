package io.renren.dao;

import io.renren.entity.TokenEntity;

/**
 * 用户Token
 *
 * @author chenshun
 */
public interface TokenDao extends BaseDao<TokenEntity> {

    TokenEntity queryByUserId(Long userId);

    TokenEntity queryByToken(String token);
}
