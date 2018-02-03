package com.pine.moudles.sys.mapper.auth;

import com.pine.core.common.SuperMapper;
import com.pine.moudles.sys.model.entity.auth.Dept;

/**
 * Dept 表数据库控制层接口
 */
public interface DeptMapper extends SuperMapper<Dept> {

    /**
     * 自定义注入方法
     */
    int deleteAll();

}