package com.pine.moudles.sys.service.auth;

import com.baomidou.mybatisplus.service.IService;
import com.pine.moudles.sys.model.entity.auth.User;

/**
 *
 * User 表数据服务层接口
 *
 */
public interface IUserService extends IService<User> {

	boolean deleteAll();

}