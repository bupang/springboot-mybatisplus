package com.pine.core.common;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * 实体父类
 * 注解说明：http://baomidou.oschina.io/mybatis-plus-doc/#/generic-crud
 */
public class SuperEntity<T extends Model> extends Model<T> {

    /**
     * 主键ID , 这里故意演示注解可以无
     */
    @TableId(value = "id",type = IdType.UUID)
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
