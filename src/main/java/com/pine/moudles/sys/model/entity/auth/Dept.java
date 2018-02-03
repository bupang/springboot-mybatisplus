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
import java.util.List;

/**
 * 部门
 * Created by chenbupang on 2018-1-28 16:23
 */
@Data
@TableName("t_dept")
@EqualsAndHashCode(callSuper = false)
public class Dept extends SuperEntity<Dept> implements Serializable {
    /**
     * 有子部门
     */
    @TableField(exist = false)
    public final static String DEPT_HAS_CHILD = "1";
    /**
     * 没有子部门
     */
    @TableField(exist = false)
    public final static String DEPT_HAS_NO_CHILD = "0";

    @TableField(strategy= FieldStrategy.NOT_NULL)
    private String name;
    @TableField(value= "parent_id")
    private String parentId;
    @TableField(value= "has_child")
    private String hasChild;
    private String status;
    private Integer disorder;

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

    @TableField(exist = false)
    private List<Dept> children;
}
