package com.web.action.xtgl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.web.bean.result.MenuTreeResult;
import com.web.core.action.BaseController;
import com.web.core.util.page.Page;
import com.web.entity.Menu;
import com.web.entity.OperLog;
import com.web.example.MenuExample;
import com.web.service.MenuService;
import com.web.util.AllResult;
import com.web.util.UUIDGenerator;
import com.web.util.WebUtils;
import com.web.util.fastjson.FastjsonUtils;
import com.web.util.validation.GroupBuilder;
import com.web.util.validation.ValidationHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.web.util.AllResult.buildJSON;

/**
 * 菜单接口
 *
 * @author 杜延雷
 * @date 2016-06-20
 */
@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);

	@Autowired
	MenuService menuService;

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public Object add(Menu menu) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("request param: [menu: {}]", JSON.toJSONString(menu));
		}

		//验证参数
		String errorTip = ValidationHelper.build()
				// 必输条件验证
				.addGroup(GroupBuilder.build(menu.getName()).notEmpty().maxLength(50), "菜单名称提供且最大长度50位")
				// 非必输条件验证
				.addGroup(GroupBuilder.buildOr(menu.getUrl()).empty().maxLength(255), "链接最大长度255位")
				.validate();

		if (StringUtils.isNotEmpty(errorTip)) {
			return buildJSON(HttpStatus.BAD_REQUEST.value(), errorTip);
		}
		// 处理外键关联数据传空值问题
		if(null != menu.getParentId()){
			if("".equals(menu.getParentId().trim())){
				menu.setParentId(null);
				menu.setLevel((short)1);
			}else{
				Menu m = menuService.getById(menu.getParentId());
				if(null == m){
					return buildJSON(HttpStatus.BAD_REQUEST.value(), "父菜单不存在");
				}else{
					menu.setLevel((short)(m.getLevel()+1));
				}
			}
		}else{
			menu.setLevel((short)1);
		}

		try {
			menu.setId(UUIDGenerator.generatorRandomUUID());
			menuService.save(menu);

			// 增加日志
			operLogService.addSystemLog(OperLog.operTypeEnum.insert, OperLog.actionSystemEnum.menu,
					JSON.toJSONString(menu),OperLog.logLevelEnum.success);

			//去除不需要的字段
			String jsonStr = JSON.toJSONString(menu,FastjsonUtils.newIgnorePropertyFilter("level","updateName","updateDate","createName","createDate"), SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty);

			return AllResult.okJSON(JSON.parse(jsonStr));
		} catch (Exception e) {
			LOGGER.error("save menu object error. : {}", JSON.toJSONString(menu), e);
			// 增加日志
			operLogService.addSystemLog(OperLog.operTypeEnum.insert, OperLog.actionSystemEnum.menu,
					JSON.toJSONString(menu),OperLog.logLevelEnum.error);
		}

		return AllResult.buildJSON(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统内部错误,添加菜单失败");
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public Object delete(String key) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("request param: [key: {}]", key);
		}

		if (StringUtils.isEmpty(key)) {
			return AllResult.buildJSON(HttpStatus.BAD_REQUEST.value(), "请求参数异常");
		}

		try {
			Menu menu = menuService.getById(key);
			if(null == menu){
				return AllResult.build(1, "菜单不存在");
			}

			List<Menu> menuList = menuService.getByParentId(key);
			if (null != menuList && !menuList.isEmpty()) {
				return AllResult.build(0, "存在子菜单不允许删除");
			}

			int result = menuService.deleteCascadeById(key);

			if(result > 0){
				// 增加日志
				operLogService.addSystemLog(OperLog.operTypeEnum.delete, OperLog.actionSystemEnum.menu,
						JSON.toJSONString(menu),OperLog.logLevelEnum.success);
			}

			return AllResult.ok();
		} catch (Exception e) {
			LOGGER.error("delete menu object error. : {}", key, e);
			operLogService.addSystemLog(OperLog.operTypeEnum.delete, OperLog.actionSystemEnum.menu, null,
					OperLog.logLevelEnum.error);
		}

		return AllResult.buildJSON(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统内部错误,删除菜单失败");
	}

	/**
	 * 修改
	 */
	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	public Object update(Menu menu) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("params[menu: {}]", JSON.toJSONString(menu));
		}

		//验证参数
		String errorTip = ValidationHelper.build()
				// 必输条件验证
				.addGroup(GroupBuilder.build(menu.getId()).notEmpty().maxLength(32), "菜单ID提供且最大长度32位")
				.addGroup(GroupBuilder.build(menu.getName()).notEmpty().maxLength(50), "菜单名称提供且最大长度50位")
				// 非必输条件验证
				.addGroup(GroupBuilder.buildOr(menu.getUrl()).empty().maxLength(255), "链接最大长度255位")
				.validate();

		if (StringUtils.isNotEmpty(errorTip)) {
			return buildJSON(HttpStatus.BAD_REQUEST.value(), errorTip);
		}

		Menu mm = menuService.getById(menu.getId());
		if(null == mm){
			return buildJSON(HttpStatus.BAD_REQUEST.value(), "菜单不存在");
		}

		// 处理外键关联数据传空值问题
//		if(null != menu.getParentId()){
//			if("".equals(menu.getParentId().trim())){
//				menu.setParentId(null);
//			}else{
//				if(null == menuService.getById(menu.getParentId())){
//					return buildJSON(HttpStatus.BAD_REQUEST.value(), "父菜单不存在");
//				}
//			}
//		}

		try {
			menu.setParentId(mm.getParentId());
			menu.setLevel(mm.getLevel());
			menuService.updateById(menu);

			operLogService.addSystemLog(OperLog.operTypeEnum.update, OperLog.actionSystemEnum.menu,
					JSON.toJSONString(menu),OperLog.logLevelEnum.success);

			//去除不需要的字段
			String jsonStr = JSON.toJSONString(menu,FastjsonUtils.newIgnorePropertyFilter("level","updateName","updateDate","createName","createDate"), SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty);

			return AllResult.okJSON(JSON.parse(jsonStr));
		} catch (Exception e) {
			LOGGER.error("update menu object error. : menu: {}", JSON.toJSONString(menu), e);
			operLogService.addSystemLog(OperLog.operTypeEnum.update, OperLog.actionSystemEnum.menu, null,
					OperLog.logLevelEnum.error);
		}
		return AllResult.buildJSON(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统内部错误,更新菜单信息失败");
	}

	/**
	 * 获取菜单详情
	 */
	@RequestMapping(value = "/get", method = { RequestMethod.GET, RequestMethod.POST })
	public Object get(String key) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("request param: [key: {}]", key);
		}

		if (StringUtils.isEmpty(key)) {
			return AllResult.buildJSON(HttpStatus.BAD_REQUEST.value(), "请求参数异常");
		}

		try {
			Menu menu = menuService.getById(key);

			if(null == menu){
				return AllResult.build(1, "菜单不存在");
			}

			operLogService.addSystemLog(OperLog.operTypeEnum.select, OperLog.actionSystemEnum.menu, "查询条件key:"+key,OperLog.logLevelEnum.success);

			//去除不需要的字段
			String jsonStr = JSON.toJSONString(menu,FastjsonUtils.newIgnorePropertyFilter("level","updateName","updateDate","createName","createDate"));
			return AllResult.okJSON(JSON.parse(jsonStr));
		} catch (Exception e) {
			LOGGER.error("menu object error. id:{}", key, e);
			operLogService.addSystemLog(OperLog.operTypeEnum.select, OperLog.actionSystemEnum.menu, null,
					OperLog.logLevelEnum.error);
		}

		return AllResult.buildJSON(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统内部错误,获取菜单信息失败");
	}

	/**
	 * 获取根据父ID获取所有子ID
	 */
	@RequestMapping(value = "/getParentId", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getParentId(String key) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("request param: [key: {}]", key);
		}

		try {
			List<Menu> menuList = menuService.getByParentId(key);

			if(null == menuList || menuList.size() == 0){
				return AllResult.build(1, "未查询到菜单信息");
			}

			//去除不需要的字段
			String jsonStr = JSON.toJSONString(menuList,FastjsonUtils.newIgnorePropertyFilter("level","updateName","updateDate","createName","createDate"));

			// 增加日志
//			operLogService.addSystemLog(OperLog.operTypeEnum.select, OperLog.actionSystemEnum.menu,jsonStr,OperLog.logLevelEnum.success);

			return AllResult.okJSON(JSON.parse(jsonStr));
		} catch (Exception e) {
			LOGGER.error("menus object error. id:{}", key, e);
			operLogService.addSystemLog(OperLog.operTypeEnum.select, OperLog.actionSystemEnum.menu, null,
					OperLog.logLevelEnum.error);
		}

		return AllResult.buildJSON(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统内部错误,获取菜单信息失败");
	}

	/**
	 * 获取所有菜单信息
	 */
	@RequestMapping(value = "/getAll", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getAll() {
		try {
			List<Menu> menuList = menuService.getAll();

			if(null == menuList || menuList.size() == 0){
				return AllResult.build(1, "未获取到菜单");
			}

			//去除不需要的字段
			String jsonStr = JSON.toJSONString(menuList,FastjsonUtils.newIgnorePropertyFilter("level","updateName","updateDate","createName","createDate"));

			// 增加日志
//			operLogService.addSystemLog(OperLog.operTypeEnum.select, OperLog.actionSystemEnum.menu,jsonStr,OperLog.logLevelEnum.success);

			return AllResult.okJSON(JSON.parse(jsonStr));
		} catch (Exception e) {
			LOGGER.error("menus object error. getAll ", e);
			operLogService.addSystemLog(OperLog.operTypeEnum.select, OperLog.actionSystemEnum.menu, null,
					OperLog.logLevelEnum.error);
		}

		return AllResult.buildJSON(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统内部错误,获取所有菜单时失败");
	}

	/**
	 * 获取Tree菜单信息
	 */
	@RequestMapping(value = "/tree", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getTree(String key, HttpServletRequest request) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("request param: [key: {}]", key);
		}

		try {
			// 根据条件查询某一级菜单（空时查询一级菜单；不等与空时查询该菜单下的所有子菜单）
			Map<String,String> params = new LinkedHashMap<String,String>();
			params.put("username", WebUtils.getUser(request).getUsername());
			if(StringUtils.isEmpty(key)){
				params.put("parentId"," is null ");
			}else{
				params.put("id",key);
			}

			List<Menu> menus = menuService.getTree(params);

			// 增加日志
//			operLogService.addSystemLog(OperLog.operTypeEnum.select, OperLog.actionSystemEnum.menu,jsonStr);

			return AllResult.okJSON(MenuTreeResult.convert(menus));
		} catch (Exception e) {
			LOGGER.error("menus object error. id:{}", key, e);
			operLogService.addSystemLog(OperLog.operTypeEnum.select, OperLog.actionSystemEnum.menu, null,
					OperLog.logLevelEnum.error);
		}

		return AllResult.buildJSON(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统内部错误,获取菜单信息失败");
	}

	/**
	 * 分页获取菜单信息
	 *
	 * @param pageNum 当前页
	 * @param pageSize 显示多少行
	 */
	@RequestMapping(value = "/datagrid", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getScroll(int pageNum, int pageSize) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("request param: [page: {}, count: {}]", pageNum, pageSize);
		}

		//1.验证参数
		String errorTip = ValidationHelper.build()
				//必输条件验证
				.addGroup(GroupBuilder.build(pageNum).notNull().minValue(1), "页码必须从1开始")
				.addGroup(GroupBuilder.build(pageSize).notNull().minValue(1), "每页记录数量最少1条")
				.validate();

		if (StringUtils.isNotEmpty(errorTip)) {
			return buildJSON(HttpStatus.BAD_REQUEST.value(), errorTip);
		}

		try {
			MenuExample example = new MenuExample();
			MenuExample.Criteria criteria = example.createCriteria();
			criteria.andParentIdIsNull();

			example.setOrderByClause("create_date desc,id asc");

			Page<Menu> queryResult = menuService.getScrollData(pageNum, pageSize, example);

			if(null == queryResult.getRecords() || queryResult.getRecords().size() == 0){
				return AllResult.build(1, "未获取到菜单");
			}

//			List<MenuTreeResult> menuTreeList = treeMenu(request,queryResult.getRecords());
			Page<MenuTreeResult> treePage = new Page<>();
			BeanUtils.copyProperties(queryResult,treePage);
//			treePage.setRecords(menuTreeList);

			//去除不需要的字段
			String jsonStr = JSON.toJSONString(treePage,FastjsonUtils.newIgnorePropertyFilter("level","updateName","updateDate","createName","createDate"));

			// 增加日志
//			operLogService.addSystemLog(OperLog.operTypeEnum.select, OperLog.actionSystemEnum.menu,jsonStr,OperLog.logLevelEnum.success);

			return AllResult.okJSON(JSON.parse(jsonStr));

		} catch (Exception e) {
			LOGGER.error("get scroll data error. page: {}, count: {}", pageNum, pageSize, e);
			operLogService.addSystemLog(OperLog.operTypeEnum.select, OperLog.actionSystemEnum.menu, null,
					OperLog.logLevelEnum.error);
		}

		return AllResult.buildJSON(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统内部错误");
	}

	/**
	 * 查询所有菜单子子菜单 TODO... 后面优化
	 */
	private List<MenuTreeResult> treeMenu(HttpServletRequest request,List<Menu> menus) {
		if (null == menus) {
			return null;
		}

		List<MenuTreeResult> menuTrees = new ArrayList<MenuTreeResult>(menus.size());

		for (Menu menu : menus) {
			if(!WebUtils.getMenuIds(request).contains(menu.getId())){
				continue;
			}
			MenuTreeResult menuTree = MenuTreeResult.convert(menu);
			List<Menu> menuList = menuService.getByParentId(menu.getId());

			if (null != menuList && menuList.size() > 0) {
				menuTree.setChildMenu(treeMenu(request,menuList));
			}
			menuTrees.add(menuTree);
		}

		return menuTrees;
	}
}
