package com.pine.moudles.sys.service.auth;

import com.baomidou.mybatisplus.service.IService;
import com.pine.moudles.sys.model.entity.auth.Dept;

import java.util.List;

/**
 *
 * Dept 表数据服务层接口
 *
 */
public interface IDeptService extends IService<Dept> {

	List<Dept> getChildDepts(String parentId, boolean filter);
}