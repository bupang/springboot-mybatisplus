package com.pine.moudles.sys.model.entity.auth;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.pine.core.common.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录审计表
 * Created by chenbupang on 2017-11-20 15:25
 */
@Data
@TableName("t_user_login")
@EqualsAndHashCode(callSuper = false)
public class UserLogin extends SuperEntity<UserLogin> implements Serializable{
    /**
     * 用户解锁
     */
    @TableField(exist = false)
    public final static int USER_IS_UNLOCKED = 1;
    /**
     * 用户未解锁
     */
    @TableField(exist = false)
    public final static int USER_IS_LOCKED = 2;

    @TableField(value= "user_id")
    private String userId;
    @TableField(value= "user_name")
    private String userName;
    @TableField(value= "last_login_time")
    private Date lastLoginTime;
    @TableField(value= "login_time")
    private Date loginTime;
    /**
     * 用户登录失败尝试次数
     */
    private Integer count;
    /**
     * 用户状态 1：已解锁 2：未解锁
     */
    private Integer flag;
    @TableField(value= "session_id")
    private String sessionId;

}
