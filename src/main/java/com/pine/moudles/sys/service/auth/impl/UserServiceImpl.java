package com.pine.moudles.sys.service.auth.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.pine.moudles.sys.mapper.auth.UserMapper;
import com.pine.moudles.sys.model.entity.auth.User;
import com.pine.moudles.sys.service.auth.IUserService;
import org.springframework.stereotype.Service;

/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

	@Override
	public boolean deleteAll() {
		return retBool(baseMapper.deleteAll());
	}


}