package io.renren.dao;

import io.renren.entity.UserEntity;

/**
 * 用户
 *
 * @author chenshun
 */
public interface UserDao extends BaseDao<UserEntity> {

    UserEntity queryByMobile(String mobile);
}
