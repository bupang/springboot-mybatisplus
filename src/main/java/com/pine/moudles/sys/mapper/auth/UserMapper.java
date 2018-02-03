package com.pine.moudles.sys.mapper.auth;

import com.pine.core.common.SuperMapper;
import com.pine.moudles.sys.model.entity.auth.User;

/**
 * User 表数据库控制层接口
 */
public interface UserMapper extends SuperMapper<User> {

    /**
     * 自定义注入方法
     */
    int deleteAll();

}