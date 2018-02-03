package com.pine.core.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户登录成功后，存储在session中的用户登录信息
 * Created by chenbupang on 2017-11-20 16:33
 */
@Data
public class UserLoginSession implements Serializable{
    /**
     * 用户锁屏
     */
    public static final String USER_SCREEN_LOCKED = "1";
    public static final String USER_SCREEN_UNLOCKED = "0";

    protected String userId;
    protected String userName;
    protected String nickName;
    protected String deptId;
    protected String deptName;
    protected String sex;
    protected String islock;//是否锁屏
    protected Date loginTime;
    protected String gitHub;
    protected String email;
    protected String qq;
    protected String headimg;


}
