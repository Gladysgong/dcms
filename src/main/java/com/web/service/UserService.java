package com.web.service;


import com.web.core.util.page.Page;
import com.web.entity.User;
import com.web.example.UserExample;

import java.util.List;

public interface UserService extends IService<User,String> {

	/**
	 * 保存用户及用户角色关系
	 */
	void saveUser(User user, String[] roleIds, String[] domainIds);

	/**
	 * 修改用户及用户角色关系
	 */
	void updateUser(User user, String[] roleIds, String[] domainIds);

	/**
	 * 根据查询条件获取查询数量
     */
	int count(UserExample example);

	/**
	 * 根据用户登录名称查询用户
	 *
	 * @param username
	 * @return
     */
	User getUserByName(String username);

	/**
	 * 分页处理 根据查询条件进行分页
	 * @param pageNum
	 * @param pageSize
	 * @param example
	 * @return
	 */
	Page<User> getScrollData(int pageNum, int pageSize, UserExample example);

	/**
	 * 根据传递条件查询 用户集合
	 *
	 * @param example
	 * @return
     */
	List<User> getExample(UserExample example) ;

	/**
	 * 扩展批量删除用户
	 */
	int deleteBatch(String... id);
}
