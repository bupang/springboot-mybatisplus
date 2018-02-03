package com.pine.moudles.sys.service.auth;


import com.baomidou.mybatisplus.service.IService;
import com.pine.moudles.sys.model.entity.auth.User;
import com.pine.moudles.sys.model.entity.auth.UserLogin;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * Dept 表数据服务层接口
 *
 */
public interface IUserLoginService extends IService<UserLogin> {
	void doUserAudit(User user, HttpServletRequest request);
	UserLogin getUerLogin(String userId);
	boolean isLocked(String userId);
}