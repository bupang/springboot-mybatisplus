package com.pine.moudles.sys.service.auth.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.pine.core.util.PineConst;
import com.pine.moudles.sys.mapper.auth.DeptMapper;
import com.pine.moudles.sys.model.entity.auth.Dept;
import com.pine.moudles.sys.service.auth.IDeptService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Dept 表数据服务层接口实现类
 *
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {


	public List<Dept> getChildDepts(String parentId, boolean filter) {
		List<Dept> depts = new ArrayList<>();
		//组装查询条件
		EntityWrapper<Dept> ew = new EntityWrapper<>();
		ew.where("parent_id={0}",parentId);
		if(filter){//启用的才读取
			ew.eq("status",PineConst.STATUS_IS_ONUSE);
		}
		ew.orderBy("disorder", PineConst.DIRECTION_ASC)
				.orderBy("create_time",PineConst.DIRECTION_DESC);

		depts = baseMapper.selectList(ew);
		return depts;
	}

}