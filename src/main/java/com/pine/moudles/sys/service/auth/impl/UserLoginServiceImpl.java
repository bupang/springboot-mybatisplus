package com.pine.moudles.sys.service.auth.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.pine.core.dto.Types;
import com.pine.core.util.DateUtils;
import com.pine.core.util.GUIDGenerator;
import com.pine.core.util.PineConst;
import com.pine.moudles.sys.mapper.auth.UserLoginMapper;
import com.pine.moudles.sys.model.entity.auth.User;
import com.pine.moudles.sys.model.entity.auth.UserLogin;
import com.pine.moudles.sys.service.auth.IUserLoginService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Service
public class UserLoginServiceImpl extends ServiceImpl<UserLoginMapper, UserLogin> implements IUserLoginService {
	/**
	 * 登录审计
	 * @param user
	 * @param request
	 */
	@Transactional
	public void doUserAudit(User user, HttpServletRequest request){
		if(user == null){
			return;
		}
		//登录失败多次锁定用
		UserLogin temp = new UserLogin();
		temp.setUserId(user.getId());
		temp.setUserName(user.getUsername());
		temp.setSessionId(request.getSession().getId());

		String LOGIN_NUMBER = PineConst.OPTIONS.get("LOGIN_NUMBER","3");//失败几次
		String LOGIN_JG_TIME = PineConst.OPTIONS.get("LOGIN_JG_TIME","60");//时间间隔，30分钟
		UserLogin userLogin = getUerLogin(user.getId());

		if (userLogin != null) {
			//计算当前时间和第一次登录失败的时间差，如果时间差在LOGIN_JG_TIME范围内，则失败次数+1，否则重新计数，并将本轮计数的第一次登录失败时间设置为当前时间
			if(userLogin.getLoginTime()==null){
				userLogin.setLoginTime(new Date());
			}
			long  fz = ( DateUtils.getCurrentDateTime()-userLogin.getLoginTime().getTime()) / (60*1000) ;
			if(LOGIN_JG_TIME!=null&&fz<=Integer.parseInt(LOGIN_JG_TIME)){
				userLogin.setCount(userLogin.getCount()+1);
			}else{
				userLogin.setLoginTime(new Date());
				userLogin.setFlag(UserLogin.USER_IS_UNLOCKED);
				userLogin.setCount(1);
			}
			if (userLogin.getFlag() == UserLogin.USER_IS_UNLOCKED) {
				//超过设置的登录失败次数，锁定
				if (Integer.parseInt(LOGIN_NUMBER) <= (userLogin.getCount())) {
					userLogin.setFlag(UserLogin.USER_IS_LOCKED);
				}
			}
			userLogin.setLoginTime(new Date());
		} else {
			userLogin = temp;
			userLogin.setCount(1);
			userLogin.setFlag(1);
			userLogin.setLoginTime(new Date());
			userLogin.setLastLoginTime(new Date());
			userLogin.setId(GUIDGenerator.getGUID(true));
		}
		baseMapper.insertAllColumn(userLogin);

	}

	/**
	 * 根据用户id获取记录
	 * @param userId
	 * @return
	 */
	public UserLogin getUerLogin(String userId){
		EntityWrapper<UserLogin> ew = new EntityWrapper<>();
		ew.where("user_id={0}",userId);
		List<UserLogin> userLogins = baseMapper.selectList(ew);
		if(userLogins != null && userLogins.size()>0){
			return userLogins.get(0);
		}
		return null;
	}

	/**
	 * 用户是否被锁定
	 * @param userId
	 * @return
	 */
	@Transactional
	public boolean isLocked(String userId){
		boolean locked = false;
		UserLogin userLogin = getUerLogin(userId);
		if(userLogin != null){
			if(userLogin.getFlag() == UserLogin.USER_IS_LOCKED && userLogin.getLoginTime()!=null){
				String unlock_time = PineConst.OPTIONS.get(Types.USER_UNLOCKED_TIME,"30");
				//计算当前时间和最后一次登录失败锁定的时间差，若时间差大于30分钟，则自动解锁
				long  fz = ( DateUtils.getCurrentDateTime()-userLogin.getLoginTime().getTime()) / (60*1000);
				if(fz>Integer.parseInt(unlock_time)){
					//解锁
					userLogin.setFlag(UserLogin.USER_IS_UNLOCKED);
					userLogin.setCount(0);
					baseMapper.updateAllColumnById(userLogin);
				} else {
					locked = true;
				}
			}else if(userLogin.getFlag() == UserLogin.USER_IS_UNLOCKED && userLogin.getCount()>1){//之前未锁定但是有失败过，需要把登录次数置0
                userLogin.setCount(0);
				baseMapper.updateAllColumnById(userLogin);
			}
		}
		return locked;
	}
}