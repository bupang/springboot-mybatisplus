package com.pine.moudles.sys.model.entity.auth;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldStrategy;
import com.pine.core.common.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 * Created by chenbupang on 2017-11-23 15:12
 */
@Data
@TableName("t_user")
@EqualsAndHashCode(callSuper = false)
public class User extends SuperEntity<User> implements Serializable{
    /**
     * 用户性别：未知
     */
    @TableField(exist = false)
    public final static String USER_SEX_UNKNOW = "0";
    /**
     * 用户性别：女
     */
    @TableField(exist = false)
    public final static String USER_SEX_FEMALE = "1";
    /**
     * 用户性别：男
     */
    @TableField(exist = false)
    public final static String USER_SEX_MALE = "2";

    @TableField(strategy= FieldStrategy.NOT_NULL)
    private String username;
    @TableField(strategy= FieldStrategy.NOT_NULL)
    private String nickname;
    private String password;

    @TableField(value= "dept_id")
    private String deptId;
    @TableField(value= "dept_name")
    private String deptName;
    private String status;
    private String sex;

    @TableField(value= "create_time")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @TableField(value= "create_user")
    private String createUser;
    @TableField(value= "update_time")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    @TableField(value= "update_user")
    private String updateUser;

    @TableField(value= "git_hub")
    private String gitHub;
    private String email;
    private String qq;
    private String headimg;
}
