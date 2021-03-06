package com.web.service;

import com.web.core.util.page.Page;
import com.web.entity.Role;
import com.web.example.RoleExample;

import java.util.List;
import java.util.Map;

public interface RoleSerivce extends IService<Role, String>{

	/**
	 * 根据查询条件获取查询数量
	 */
	int count(RoleExample example);

	/**
	 * 根据查询条件查找角色
	 */
	 Role getByExample(RoleExample example);

	/**
	 * 分页处理 根据查询条件进行分页
	 * @param pageNum
	 * @param pageSize
	 * @param example
	 * @return
	 */
	Page<Role> getScrollData(int pageNum, int pageSize, RoleExample example);

	/**
	 * 根据用户ID查询角色列表
     */
	List<Role> getByUserId(Map<String, String> params);
}
